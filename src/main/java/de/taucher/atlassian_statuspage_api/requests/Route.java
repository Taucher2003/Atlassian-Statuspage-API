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

import de.taucher.atlassian_statuspage_api.Methods;

import static de.taucher.atlassian_statuspage_api.requests.Route.Method.*;

public class Route {

	public static final String API_BASE_URL = "https://api.statuspage.io/v1";

	public enum Method {
		GET,
		POST,
		PUT,
		PATCH,
		DELETE
	}

	public static class Pages {
		public static final Route GET_PAGE_LIST = new Route("/pages", GET);
		public static final Route GET_PAGE = new Route("/pages/{page_id}", GET);
		public static final Route UPDATE_PAGE = new Route("/pages/{page_id}", PATCH);
		public static final Route UPDATE_FULL_PAGE = new Route("/pages/{page_id}", PUT);

	}
	public static class Components {
		public static final Route CREATE_COMPONENT = new Route("/pages/{page_id}/components", POST);
		public static final Route GET_COMPONENT_LIST = new Route("/pages/{page_id}/components", GET);
		public static final Route GET_COMPONENT = new Route("/pages/{page_id}/components/{component_id}", GET);
		public static final Route UPDATE_COMPONENT = new Route("/pages/{page_id}/components/{component_id}", PATCH);
		public static final Route UPDATE_FULL_COMPONENT = new Route("/pages/{page_id}/components/{component_id}", PUT);
		public static final Route DELETE_COMPONENT = new Route("/pages/{page_id}/components/{component_id}", DELETE);

	}

	private final String url;
	private final Method method;

	private Route(String url, Method method) {
		if (Methods.countChar(url, '{') != Methods.countChar(url, '}')) {
			throw new IllegalArgumentException("There are unmatching parameter braces for route " + method.name() + "~" + url);
		}
		this.url = url;
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public Method getMethod() {
		return method;
	}

	public CompiledRoute compile(String... args) {
		String compiledUrl = API_BASE_URL + url;
		int param = 0;
		while(compiledUrl.contains("{") && compiledUrl.contains("}")) {
			int paramStart = compiledUrl.indexOf("{");
            int paramEnd = compiledUrl.indexOf("}");
            compiledUrl = compiledUrl.substring(0, paramStart) + args[param++] + compiledUrl.substring(paramEnd+1);
		}
		return new CompiledRoute(this, compiledUrl);
	}

	public static class CompiledRoute {
		private final Route route;
		private final String comiledUrl;

		private CompiledRoute(Route route, String compiledUrl) {
			this.route = route;
			this.comiledUrl = compiledUrl;
		}

		public Route getRoute() {
			return route;
		}
		
		public String getComiledUrl() {
			return comiledUrl;
		}
	}
}
