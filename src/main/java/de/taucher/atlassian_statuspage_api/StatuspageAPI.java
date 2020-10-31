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

package de.taucher.atlassian_statuspage_api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.Response;

import de.taucher.atlassian_statuspage_api.entities.Page;
import de.taucher.atlassian_statuspage_api.requests.Request;
import de.taucher.atlassian_statuspage_api.requests.Requester;
import de.taucher.atlassian_statuspage_api.requests.Route;

/**
 * Root class of the Library. Use the constructor {@link #StatuspageAPI(String)} to create an instance.
 * From this class you can get a {@link de.taucher.atlassian_statuspage_api.entities.Page Page} and
 * perform actions on these Pages.
 * 
 * @since 1.0.0
 *
 */
public class StatuspageAPI {
	
	private String apiKey;
	private Requester requester;
	
	/**
	 * Constructor for the API.
	 * @param apiKey your api key for your organization
	 * @since 1.0.0
	 */
	public StatuspageAPI(String apiKey) {
		this.apiKey = apiKey;
		this.requester = new Requester(this);
	}
	
	/**
	 * Get a list of all {@link de.taucher.atlassian_statuspage_api.entities.Page Pages} you have access to
	 * @return a list with the Page instances
	 * @since 1.0.0
	 */
	public List<Page> getPages(){
		List<Page> result = new ArrayList<>();
		Route.CompiledRoute route = Route.Pages.GET_PAGE_LIST.compile();
		Request request = new Request(route, Request.EMPTY_BODY);
		try {
			Response response = getRequester().queue(request);
			JSONArray jsonList = new JSONArray(response.body().string());
			for(Object jsonObj : jsonList) {
				if(jsonObj instanceof JSONObject) {
					JSONObject json = (JSONObject) jsonObj;
					Page page = Page.fromJson(this, json);
					result.add(page);
				}else {
					System.err.println(jsonObj+" was not an JSONObject");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Get a {@link de.taucher.atlassian_statuspage_api.entities.Page Page} with the given id
	 * 
	 * <br><br>This method is equivalent to
	 * <pre>{@code
	 * getPages().stream().filter(
	 * 	p -> p.getId().equals(pageId)
	 * ).findFirst().orElse(null);
	 * }</pre>
	 * @param pageId the id of the page
	 * @return the Page instance
	 * @since 1.0.0
	 */
	public Page getPage(String pageId) {
		Page page = null;
		Route.CompiledRoute route = Route.Pages.GET_PAGE.compile(pageId);
		Request request = new Request(route, Request.EMPTY_BODY);
		try {
			Response response = getRequester().queue(request);
			JSONObject json = new JSONObject(response.body().string());
			page = Page.fromJson(this, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * Gets the api key of this instance
	 * @return the api key
	 * @since 1.0.0
	 */
	public String getApiKey() {
		return apiKey;
	}
	
	/**
	 * Gets the {@link de.taucher.atlassian_statuspage_api.requests.Requester Requester}.
	 * 
	 * <p><b>It is not recommended to create your own {@link de.taucher.atlassian_statuspage_api.requests.Request Requests}.</b>
	 * <br>You should use the already existing methods to perform your actions
	 * @return the Requester
	 * @since 1.0.0
	 */
	public Requester getRequester() {
		return requester;
	}
}
