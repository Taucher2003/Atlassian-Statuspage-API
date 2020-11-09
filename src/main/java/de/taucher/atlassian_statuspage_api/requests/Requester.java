/*
 * Copyright 2020 Niklas van Schrick and the contributors of this project
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package de.taucher.atlassian_statuspage_api.requests;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import de.taucher.atlassian_statuspage_api.StatuspageAPI;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Requester {

	private final OkHttpClient okHttpClient = new OkHttpClient();
	private long nextRequestAt = System.currentTimeMillis();
	
	private final StatuspageAPI statuspageAPI;
	private final HashMap<Request, Consumer<Response>> queue;
	private Request nextExecution;
	
	private boolean shutdown = false;
	
	public Requester(StatuspageAPI api) {
		statuspageAPI = api;
		queue = new HashMap<>();
		executeQueue();
	}
	
	public Response queue(Request request) {
		queue.put(request, null);
		while(nextExecution != request) {
			if(!queue.containsKey(request)) {
				throw new InternalError("Requester has been shutdown and forced a clear of queue");
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return execute(request);
	}
	
	public void queueAsync(Request request) {
		queueAsync(request, r -> {});
	}
	
	public void queueAsync(Request request, Consumer<Response> responseHandling) {
		new Thread(() -> {
			queue.put(request, responseHandling);
			while(nextExecution != request) {
				if(!queue.containsKey(request)) {
					throw new InternalError("Requester has been shutdown and forced a clear of queue");
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Response response = execute(request);
			if(response != null && responseHandling != null) {
				responseHandling.accept(response);
			}
		}).start();
	}
	
	private void executeQueue() {
		Thread thread = new Thread(() -> {
			synchronized (queue) {
				while (!shutdown || queue.size() != 0) {
					if (queue.size() == 0) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}
					Request request = Arrays.asList(queue.keySet().toArray(new Request[0])).get(0);
					while (System.currentTimeMillis() <= nextRequestAt && nextExecution != null) {
						try {
							System.out.println("Waiting with request");
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					nextExecution = request;
					queue.remove(request);
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	private Response execute(Request request) {
		System.out.println(request);
		com.squareup.okhttp.Request okRequest = new com.squareup.okhttp.Request.Builder().url(request.getCompiledRoute().getComiledUrl())
				.header("Authorization", "OAuth "+statuspageAPI.getApiKey())
				.method(request.getCompiledRoute().getRoute().getMethod().name(), request.getRequestBody()).build();
		try {
			Response response = okHttpClient.newCall(okRequest).execute();
			nextRequestAt = System.currentTimeMillis()+TimeUnit.SECONDS.toMillis(1);
			nextExecution = null;
			switch(response.code()) {
				case 401:
					throw new IllegalAccessError("Could not authenticate");
				case 404:
					throw new NoSuchElementException("The requested resource could not be found");
				case 422:
					throw new IllegalAccessError("Unprocessable entity");
				default:
					break;
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		nextExecution = null;
		return null;
	}
	
	public void shutdown() {
		shutdown = true;
	}
	
	public void shutdownNow() {
		shutdown();
		queue.clear();
	}
}
