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

package com.github.taucher2003.atlassian_statuspage_api.requests;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.internal.http.HttpMethod;

public class Request {

	public static final RequestBody EMPTY_BODY = RequestBody.create(null, new byte[0]);
	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	private final Route.CompiledRoute compiledRoute;
	private final RequestBody requestBody;

	public Request(Route.CompiledRoute compiledRoute, RequestBody requestBody) {
		this.compiledRoute = compiledRoute;
		this.requestBody = HttpMethod.permitsRequestBody(compiledRoute.getRoute().getMethod().name()) ? requestBody : null;
	}

	public Route.CompiledRoute getCompiledRoute() {
		return compiledRoute;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}
	
	@Override
	public String toString() {
		return "Request(route="+compiledRoute.getRoute().getMethod().name()+" "+compiledRoute.getComiledUrl()+")";
	}
}
