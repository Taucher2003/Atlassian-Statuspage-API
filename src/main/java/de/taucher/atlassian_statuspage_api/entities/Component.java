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

package de.taucher.atlassian_statuspage_api.entities;

import java.time.OffsetDateTime;

import org.json.JSONObject;

import com.squareup.okhttp.RequestBody;

import de.taucher.atlassian_statuspage_api.StatuspageAPI;
import de.taucher.atlassian_statuspage_api.requests.Request;
import de.taucher.atlassian_statuspage_api.requests.Route;

public class Component {

	private StatuspageAPI api;
	
	private String id;
	private String pageId;
	private String groupId;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
	private boolean group;
	private String name;
	private String description;
	private int position;
	private Status status;
	private boolean showcase;
	private boolean onlyShowIfDegraded;
	private String automationEmail;
	
	private Component(StatuspageAPI api,
			String id, String pageId, String groupId, OffsetDateTime createdAt, OffsetDateTime updatedAt, boolean group,
			String name, String description, int position, Status status, boolean showcase, boolean onlyShowIfDegraded, String automationEmail) {
		this.api = api;
		this.id = id;
		this.pageId = pageId;
		this.groupId = groupId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.group = group;
		this.name = name;
		this.description = description;
		this.position = position;
		this.status = status;
		this.showcase = showcase;
		this.onlyShowIfDegraded = onlyShowIfDegraded;
		this.automationEmail = automationEmail;
	}
	
	public static Component fromJson(StatuspageAPI api, JSONObject json) {
		String id = json.get("id") instanceof String ? json.getString("id") : null;
		String pageId = json.get("page_id") instanceof String ? json.getString("page_id") : null;
		String groupId = json.get("group_id") instanceof String ? json.getString("group_id") : null;
		OffsetDateTime createdAt = json.get("created_at") instanceof String ? OffsetDateTime.parse(json.getString("created_at")) : null;
		OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
		String name = json.get("name") instanceof String ? json.getString("name") : null;
		String description = json.get("description") instanceof String ? json.getString("description") : null;
		Status status = json.get("status") instanceof String ? Status.valueOf(json.getString("status").toUpperCase()) : null;
		String automation_email = json.get("automation_email") instanceof String ? json.getString("automation_email") : null;
		return new Component(api, id, pageId, groupId, createdAt, 
				updatedAt, json.getBoolean("group"), name, description, 
				json.getInt("position"), status, json.getBoolean("showcase"), json.getBoolean("only_show_if_degraded"), automation_email);
	}
	
	public void setDescription(String description) {
		Route.CompiledRoute route = Route.Components.UPDATE_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.description = description;
			}
		});
	}
	
	public void setStatus(Status status) {
		Route.CompiledRoute route = Route.Components.UPDATE_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.status = status;
			}
		});
	}
	
	public void setName(String name) {
		Route.CompiledRoute route = Route.Components.UPDATE_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.name = name;
			}
		});
	}
	
	public void setOnlyShowIfDegraded(boolean onlyShowIfDegraded) {
		Route.CompiledRoute route = Route.Components.UPDATE_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.onlyShowIfDegraded = onlyShowIfDegraded;
			}
		});
	}
	
	public void setGroupId(String groupId) {
		Route.CompiledRoute route = Route.Components.UPDATE_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.groupId = groupId;
			}
		});
	}
	
	public void setShowcase(boolean showcase) {
		Route.CompiledRoute route = Route.Components.UPDATE_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.showcase = showcase;
			}
		});
	}
	
	public void set(String description, Status status, String name, boolean onlyShowIfDegraded, String groupId, boolean showcase) {
		Route.CompiledRoute route = Route.Components.UPDATE_FULL_COMPONENT.compile(pageId, id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description).put("status", status.name().toLowerCase()).put("name", name)
				.put("only_show_if_degraded", onlyShowIfDegraded).put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.description = description;
				this.status = status;
				this.name = name;
				this.onlyShowIfDegraded = onlyShowIfDegraded;
				this.groupId = groupId;
				this.showcase = showcase;
			}
		});
	}
	
	//Getters
	
	public StatuspageAPI getApi() {
		return api;
	}
	
	public String getAutomationEmail() {
		return automationEmail;
	}
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Page getPage() {
		return api.getPage(pageId);
	}
	
	public String getPageId() {
		return pageId;
	}
	
	public int getPosition() {
		return position;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public boolean isGroup() {
		return group;
	}
	
	public boolean isOnlyShowIfDegraded() {
		return onlyShowIfDegraded;
	}
	
	public boolean isShowcase() {
		return showcase;
	}
	
	@Override
	public String toString() {
		return (isGroup() ? "ComponentGroup" : "Component")+"(id="+id+", page_id="+pageId+", name="+name+", status="+status.name()+")";
	}
}
