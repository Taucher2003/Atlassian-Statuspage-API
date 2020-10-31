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

/**
 * This class wraps a Component item. Components are the individual pieces of 
 * infrastructure that are listed on your status page. This endpoint is used 
 * primarily to update status on individual components in the event of an outage or for degraded performance.
 * 
 * @since 1.0.0
 *
 */
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
	
	/**
	 * Creates an {@link Component} instance. This method <b>should not be used</b> in your code.
	 * @param api the api instance, that requested this resource
	 * @param json the json response from the REST api
	 * @return a Component instance
	 * @since 1.0.0
	 * 
	 * @see Page#createComponent(String, Status, String, boolean, String, boolean)
	 * @see Page#getComponent(String)
	 * @see Page#getComponents()
	 */
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
	
	/**
	 * Change the description of this component
	 * @param description the new description
	 * @since 1.0.0
	 * @see #set(String, Status, String, boolean, String, boolean)
	 */
	public void setDescription(String description) {
		set(description, status, name, onlyShowIfDegraded, groupId, showcase);
	}
	
	/**
	 * Change the {@link Status} of this component. The status is the main property, that is changed
	 * as it is the reason for the statuspage to exist
	 * @param status the new Status
	 * @since 1.0.0
	 * @see #set(String, Status, String, boolean, String, boolean)
	 */
	public void setStatus(Status status) {
		set(description, status, name, onlyShowIfDegraded, groupId, showcase);
	}
	
	/**
	 * Change the name of this component
	 * @param name the new name
	 * @since 1.0.0
	 * @see #set(String, Status, String, boolean, String, boolean)
	 */
	public void setName(String name) {
		set(description, status, name, onlyShowIfDegraded, groupId, showcase);
	}
	
	/**
	 * Change if the component should only be shown, it its status is degraded.
	 * <p>That means, the component is only shown, if its status is one of the following;
	 * <ul>
	 * 	<li>{@link Status#DEGRADED_PERFORMANCE}</li>
	 * 	<li>{@link Status#PARTIAL_OUTAGE}</li>
	 *  <li>{@link Status#MAJOR_OUTAGE}</li>
	 *  <li>{@link Status#UNDER_MAINTENANCE}</li>
	 * </ul>
	 * @param onlyShowIfDegraded a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, Status, String, boolean, String, boolean) 
	 */
	public void setOnlyShowIfDegraded(boolean onlyShowIfDegraded) {
		set(description, status, name, onlyShowIfDegraded, groupId, showcase);
	}
	
	/**
	 * Change the group of this component. Groups are used to group the components in logical parts.
	 * If every component of one group is {@link Status#OPERATIONAL} the group is collapsed.
	 * @param groupId the id of the new group
	 * @since 1.0.0
	 * @see #set(String, Status, String, boolean, String, boolean)
	 */
	public void setGroupId(String groupId) {
		set(description, status, name, onlyShowIfDegraded, groupId, showcase);
	}
	
	/**
	 * Change if the component should be shown on the statuspage
	 * @param showcase a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, Status, String, boolean, String, boolean)
	 */
	public void setShowcase(boolean showcase) {
		set(description, status, name, onlyShowIfDegraded, groupId, showcase);
	}
	
	/**
	 * Modify the full component. If you need to change multiple values at the same time, it
	 * is more efficient, to use this method instead of calling the methods for each property to change.
	 * @param description the new description
	 * @param status the new status
	 * @param name the new name
	 * @param onlyShowIfDegraded a boolean that indicates the new value
	 * @param groupId the id of the new group
	 * @param showcase a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #setDescription(String)
	 * @see #setStatus(Status)
	 * @see #setName(String)
	 * @see #setOnlyShowIfDegraded(boolean)
	 * @see #setGroupId(String)
	 * @see #setShowcase(boolean)
	 */
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
	
	/**
	 * Gets the api instance that requested this resource
	 * @return the instance
	 * @since 1.0.0
	 */
	public StatuspageAPI getApi() {
		return api;
	}
	
	/**
	 * Gets the email adress that is used for automation.
	 * @return the email adress
	 * @since 1.0.0
	 * @see <a href="https://support.atlassian.com/statuspage/docs/get-started-with-email-automation/">Atlassian Documentation</a>
	 */
	public String getAutomationEmail() {
		return automationEmail;
	}
	
	/**
	 * Get the time when this component was created
	 * @return an OffsetDateTime representing the timestamp, when this component was created
	 * @since 1.0.0
	 * @see #getUpdatedAt()
	 */
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * Gets the description of this component
	 * @return the description
	 * @since 1.0.0
	 * @see #setDescription(String)
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the group id of this component
	 * @return the group id
	 * @since 1.0.0
	 * @see #setGroupId(String)
	 */
	public String getGroupId() {
		return groupId;
	}
	
	/**
	 * Gets the id of this component
	 * @return the id
	 * @since 1.0.0
	 * @see Page#getComponent(String)
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the name of this component
	 * @return the name
	 * @since 1.0.0
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the {@link Page} where this Component is located.
	 * This is a shortcut for 
	 * <pre>{@code getApi().getPage(getPageId())}</pre>
	 * @return the Page
	 * @since 1.0.0
	 * @see #getPageId()
	 */
	public Page getPage() {
		return api.getPage(pageId);
	}
	
	/**
	 * Gets the id of the {@link Page} where this Component is located
	 * @return the id of the Page
	 * @since 1.0.0
	 * @see #getPage()
	 */
	public String getPageId() {
		return pageId;
	}
	
	/**
	 * Gets the position of this Component.
	 * The position is used for the ordering on the Page
	 * @return the position
	 * @since 1.0.0
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Gets the current {@link Status} of this Component.
	 * The {@link Status} represents if the Component is Operational or has issues.
	 * @return the status
	 * @since 1.0.0
	 * @see #setStatus(Status)
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Get the time when this component was updated
	 * @return an OffsetDateTime representing the timestamp, when this component was updated
	 * @since 1.0.0
	 * @see #getCreatedAt()
	 */
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * Checks if this instance represents a Component Group
	 * @return a boolean representing the group state
	 * @since 1.0.0
	 */
	public boolean isGroup() {
		return group;
	}
	
	/**
	 * Checks if this instance is only shown if it has issues
	 * @return a boolean representing this setting
	 * @since 1.0.0
	 * @see #setOnlyShowIfDegraded(boolean)
	 */
	public boolean isOnlyShowIfDegraded() {
		return onlyShowIfDegraded;
	}
	
	/**
	 * Checks if this instance is shown
	 * @return a boolean representing this setting
	 * @since 1.0.0
	 * @see #setShowcase(boolean)
	 */
	public boolean isShowcase() {
		return showcase;
	}
	
	@Override
	public String toString() {
		return (isGroup() ? "ComponentGroup" : "Component")+"(id="+id+", page_id="+pageId+", name="+name+", status="+status.name()+")";
	}
}
