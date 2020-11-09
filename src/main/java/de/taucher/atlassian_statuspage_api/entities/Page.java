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

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import de.taucher.atlassian_statuspage_api.StatuspageAPI;
import de.taucher.atlassian_statuspage_api.requests.Request;
import de.taucher.atlassian_statuspage_api.requests.Route;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Page {

	private final StatuspageAPI api;

	private final String id;
	private final OffsetDateTime createdAt;
	private final OffsetDateTime updatedAt;
	private final String pageDescription;
	private final String headline;
	private final String supportUrl;
	private final int activityScore;
	private final String twitterUsername;
	private final String ipRestrictions;
	private final String city;
	private final String state;
	private final String country;
	private final FaviconLogo faviconLogo;
	private final TransactionalLogo transactionalLogo;
	private final HeroCover heroCover;
	private boolean viewersMustBeTeamMembers;
	private final EmailLogo emailLogo;
	private final TwitterLogo twitterLogo;
	private boolean hiddenFromSearch,
			allowPageSubscribers,
			allowIncidentSubscribers,
			allowEmailSubscribers,
			allowSmsSubscribers,
			allowRssSubscribers,
			allowWebhookSubscribers;
	private String notificationsFromEmail,
			notificationsEmailFooter;
	private String name;
	private String branding;
	private String subdomain;
	private String domain;
	private String url;
	private String timeZone;
	private String cssBodyBackgroundColor;
	private String cssFontColor;
	private String cssLightFontColor;
	private String cssGreens;
	private String cssYellows;
	private String cssOranges;
	private String cssBlues;
	private String cssReds;
	private String cssBorderColor;
	private String cssGraphColor;
	private String cssLinkColor;
	private String cssNoData;

	private Page(StatuspageAPI api,
				 String id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String name, String pageDescription, String headline, String branding, String subdomain, String domain, String url, String supportUrl,
				 boolean hiddenFromSearch, boolean allowPageSubscribers, boolean allowIncidentSubscribers, boolean allowEmailSubscribers, boolean allowSmsSubscribers, boolean allowRssSubscribers,
				 boolean allowWebhookSubscribers, String notificationsFromEmail, String notificationsEmailFooter, int activityScore, String twitterUsername, boolean viewersMustBeTeamMembers,
				 String ipRestrictions, String city, String state, String country, String timeZone, String cssBodyBackgroundColor, String cssFontColor, String cssLightFontColor, String cssGreens,
				 String cssYellows, String cssOranges, String cssBlues, String cssReds, String cssBorderColor, String cssGraphColor, String cssLinkColor, String cssNoData, FaviconLogo faviconLogo,
				 TransactionalLogo transactionalLogo, HeroCover heroCover, EmailLogo emailLogo, TwitterLogo twitterLogo) {
		this.api = api;
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.pageDescription = pageDescription;
		this.headline = headline;
		this.branding = branding;
		this.subdomain = subdomain;
		this.domain = domain;
		this.url = url;
		this.supportUrl = supportUrl;
		this.hiddenFromSearch = hiddenFromSearch;
		this.allowPageSubscribers = allowPageSubscribers;
		this.allowIncidentSubscribers = allowIncidentSubscribers;
		this.allowEmailSubscribers = allowEmailSubscribers;
		this.allowSmsSubscribers = allowSmsSubscribers;
		this.allowRssSubscribers = allowRssSubscribers;
		this.allowWebhookSubscribers = allowWebhookSubscribers;
		this.notificationsFromEmail = notificationsFromEmail;
		this.notificationsEmailFooter = notificationsEmailFooter;
		this.activityScore = activityScore;
		this.twitterUsername = twitterUsername;
		this.viewersMustBeTeamMembers = viewersMustBeTeamMembers;
		this.ipRestrictions = ipRestrictions;
		this.city = city;
		this.state = state;
		this.country = country;
		this.timeZone = timeZone;
		this.cssBodyBackgroundColor = cssBodyBackgroundColor;
		this.cssFontColor = cssFontColor;
		this.cssLightFontColor = cssLightFontColor;
		this.cssGreens = cssGreens;
		this.cssYellows = cssYellows;
		this.cssOranges = cssOranges;
		this.cssBlues = cssBlues;
		this.cssReds = cssReds;
		this.cssBorderColor = cssBorderColor;
		this.cssGraphColor = cssGraphColor;
		this.cssLinkColor = cssLinkColor;
		this.cssNoData = cssNoData;
		this.faviconLogo = faviconLogo;
		this.transactionalLogo = transactionalLogo;
		this.heroCover = heroCover;
		this.emailLogo = emailLogo;
		this.twitterLogo = twitterLogo;
	}
	
	/**
	 * Creates an {@link Page} instance. This method <b>should not be used</b> in your code.
	 * @param api the api instance, that requested this resource
	 * @param json the json response from the REST api
	 * @return a Page instance
	 * @since 1.0.0
	 * 
	 * @see StatuspageAPI#getPage(String)
	 * @see StatuspageAPI#getPages()
	 */
	public static Page fromJson(StatuspageAPI api, JSONObject json) {
		String id = json.get("id") instanceof String ? json.getString("id") : null;
		OffsetDateTime createdAt = json.get("created_at") instanceof String ? OffsetDateTime.parse(json.getString("created_at")) : null;
		OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
		String name = json.get("name") instanceof String ? json.getString("name") : null;
		String pageDescription = json.get("page_description") instanceof String ? json.getString("page_description") : null;
		String headline = json.get("headline") instanceof String ? json.getString("headline") : null;
		String branding = json.get("branding") instanceof String ? json.getString("branding") : null;
		String subdomain = json.get("subdomain") instanceof String ? json.getString("subdomain") : null;
		String domain = json.get("domain") instanceof String ? json.getString("domain") : null;
		String url = json.get("url") instanceof String ? json.getString("url") : null;
		String supportUrl = json.get("support_url") instanceof String ? json.getString("support_url") : null;
		String notificationsFromEmail = json.get("notifications_from_email") instanceof String ? json.getString("notifications_from_email") : null;
		String notificationsEmailFooter = json.get("notifications_email_footer") instanceof String ? json.getString("notifications_email_footer") : null;
		String twitterUsername = json.get("twitter_username") instanceof String ? json.getString("twitter_username") : null;
		String ipRestrictions = json.get("ip_restrictions") instanceof String ? json.getString("ip_restrictions") : null;
		String city = json.get("city") instanceof String ? json.getString("city") : null;
		String state = json.get("state") instanceof String ? json.getString("state") : null;
		String country = json.get("country") instanceof String ? json.getString("country") : null;
		String timeZone = json.get("time_zone") instanceof String ? json.getString("time_zone") : null;
		String cssBodyBackgroundColor = json.get("css_body_background_color") instanceof String ? json.getString("css_body_background_color") : null;
		String cssFontColor = json.get("css_font_color") instanceof String ? json.getString("css_font_color") : null;
		String cssLightFontColor = json.get("css_light_font_color") instanceof String ? json.getString("css_light_font_color") : null;
		String cssGreens = json.get("css_greens") instanceof String ? json.getString("css_greens") : null;
		String cssYellows = json.get("css_yellows") instanceof String ? json.getString("css_yellows") : null;
		String cssOranges = json.get("css_oranges") instanceof String ? json.getString("css_oranges") : null;
		String cssBlues = json.get("css_blues") instanceof String ? json.getString("css_blues") : null;
		String cssReds = json.get("css_reds") instanceof String ? json.getString("css_reds") : null;
		String cssBorderColor = json.get("css_border_color") instanceof String ? json.getString("css_border_color") : null;
		String cssGraphColor = json.get("css_graph_color") instanceof String ? json.getString("css_graph_color") : null;
		String cssLinkColor = json.get("css_link_color") instanceof String ? json.getString("css_link_color") : null;
		String cssNoData = json.get("css_no_data") instanceof String ? json.getString("css_no_data") : null;
		return new Page(api, id, createdAt, updatedAt, 
				name, pageDescription, headline, branding, subdomain, domain, url,
				supportUrl, json.getBoolean("hidden_from_search"), json.getBoolean("allow_page_subscribers"), json.getBoolean("allow_incident_subscribers"), 
				json.getBoolean("allow_email_subscribers"), json.getBoolean("allow_sms_subscribers"), json.getBoolean("allow_rss_atom_feeds"), json.getBoolean("allow_webhook_subscribers"), 
				notificationsFromEmail, notificationsEmailFooter, json.getInt("activity_score"), twitterUsername, 
				json.getBoolean("viewers_must_be_team_members"), ipRestrictions, city, state, country, 
				timeZone, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, 
				cssYellows, cssOranges, cssBlues, cssReds, cssBorderColor, 
				cssGraphColor, cssLinkColor, cssNoData, FaviconLogo.fromJson(json.getJSONObject("twitter_logo")), 
				TransactionalLogo.fromJson(json.getJSONObject("transactional_logo")), HeroCover.fromJson(json.getJSONObject("hero_cover")), 
				EmailLogo.fromJson(json.getJSONObject("email_logo")), TwitterLogo.fromJson(json.getJSONObject("twitter_logo")));
	}
	
	// Components
	
	/**
	 * Creates a {@link Component} in this {@link Page}
	 * @param description the description of the Component
	 * @param status the Status of the Component
	 * @param name the Name of the Component
	 * @param onlyShowIfDegraded the component will only be shown, if its status is degraded.
	 * @param groupId the id of the group this component belongs to
	 * @param showcase true, if this component should be shown
	 * @return the created component
	 */
	public Component createComponent(String description, Status status, String name, boolean onlyShowIfDegraded, String groupId, boolean showcase) {
		Route.CompiledRoute route = Route.Components.CREATE_COMPONENT.compile(id);
		JSONObject payload = new JSONObject().put("component", new JSONObject().put("description", description)
				.put("status", status.name().toLowerCase()).put("name", name).put("only_show_if_degraded", onlyShowIfDegraded)
				.put("group_id", groupId).put("showcase", showcase));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		Response response = api.getRequester().queue(request);
		try {
			JSONObject json = new JSONObject(response.body().string());
			return Component.fromJson(api, json);
		}catch(JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get a list of all {@link Component} you have access to
	 * @return a list with the Component instances
	 * @since 1.0.0
	 */
	public List<Component> getComponents(){
		List<Component> result = new ArrayList<>();
		Route.CompiledRoute route = Route.Components.GET_COMPONENT_LIST.compile(id);
		Request request = new Request(route, Request.EMPTY_BODY);
		try {
			Response response = api.getRequester().queue(request);
			JSONArray jsonList = new JSONArray(response.body().string());
			for(Object jsonObj : jsonList) {
				if(jsonObj instanceof JSONObject) {
					JSONObject json = (JSONObject) jsonObj;
					Component page = Component.fromJson(api, json);
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
	 * Get a {@link Component} with the given id
	 * 
	 * <br><br>This method is equivalent to
	 * <pre>{@code
	 * getComponents().stream().filter(
	 * 	c -> c.getId().equals(pageId)
	 * ).findFirst().orElse(null);
	 * }</pre>
	 * @param componentId the id of the component
	 * @return the Component instance
	 * @since 1.0.0
	 */
	public Component getComponent(String componentId) {
		Route.CompiledRoute route = Route.Components.GET_COMPONENT.compile(id, componentId);
		Request request = new Request(route, Request.EMPTY_BODY);
		Response response = api.getRequester().queue(request);
		try {
			JSONObject json = new JSONObject(response.body().string());
			return Component.fromJson(api, json);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Deletes a {@link Component} from this {@link Page}
	 * <br>Be careful, this <b>cannot be undone</b>
	 * @param component the component to delete
	 */
	public void delete(Component component) {
		delete(component.getId());
	}
	
	/**
	 * Deletes a {@link Component} from this {@link Page}
	 * <br>Be careful, this <b>cannot be undone</b>
	 * @param componentId the id of the component to delete
	 */
	public void delete(String componentId) {
		Route.CompiledRoute route = Route.Components.DELETE_COMPONENT.compile(id, componentId);
		Request request = new Request(route, Request.EMPTY_BODY);
		api.getRequester().queueAsync(request);
	}
	
	// Modify

	/**
	 * Change the name of this page
	 *
	 * @param name the new description
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String,
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 * @since 1.0.0
	 */
	public void setName(String name) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change to domain of this page
	 * @param domain the new domain
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setDomain(String domain) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the subdomain of this page
	 * @param subdomain the new subdomain
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setSubdomain(String subdomain) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the url of this page
	 * @param url the new url
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setUrl(String url) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the branding of this page
	 * @param branding the new branding
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setBranding(String branding) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Body Background Color
	 * @param cssBodyBackgroundColor the new background color
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSBodyBackgroundColor(String cssBodyBackgroundColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Font Color
	 * @param cssFontColor the new font color
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSFontColor(String cssFontColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Light Font Color
	 * @param cssLightFontColor the new light font color
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSLightFontColor(String cssLightFontColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Greens
	 * @param cssGreens the new greens
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSGreens(String cssGreens) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Yellows
	 * @param cssYellows the new yellows
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSYellows(String cssYellows) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Oranges
	 * @param cssOranges the new oranges
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSOranges(String cssOranges) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Reds
	 * @param cssReds the new reds
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSReds(String cssReds) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Blues
	 * @param cssBlues the new blues
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSBlues(String cssBlues) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Border Color
	 * @param cssBorderColor the new border color
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSBorderColor(String cssBorderColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Graph Color
	 * @param cssGraphColor the new graph color
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSGraphColor(String cssGraphColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS Link Color
	 * @param cssLinkColor the new link color
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSLinkColor(String cssLinkColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the CSS No Data
	 * @param cssNoData the new no data
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setCSSNoData(String cssNoData) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should be hidden from a search
	 * @param hiddenFromSearch a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setHiddenFromSearch(boolean hiddenFromSearch) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should only be visible to team members
	 * @param viewersMustBeTeamMembers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setViewersMustBeTeamMembers(boolean viewersMustBeTeamMembers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should allow subscriptions
	 * @param allowPageSubscribers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String) 
	 */
	public void setAllowPageSubscribers(boolean allowPageSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should allow subscriptions to specific incidents
	 * @param allowIncidentSubscribers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setAllowIncidentSubscribers(boolean allowIncidentSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should allow subscriptions via email
	 * @param allowEmailSubscribers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setAllowEmailSubscribers(boolean allowEmailSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should allow subscriptions via sms
	 * @param allowSmsSubscribers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setAllowSmsSubscribers(boolean allowSmsSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the RSS Feed should be enabled for this Page
	 * @param allowRssSubscribers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setAllowRssSubscribers(boolean allowRssSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change if the Page should allow subscriptions via webhook
	 * @param allowWebhookSubscribers a boolean that indicates the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setAllowWebhookSubscribers(boolean allowWebhookSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * I'm not quite sure what this property is, but it is documented in the statuspage api and so I put it here
	 * @param notificationsFromEmail the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setNotificationsFromEmail(String notificationsFromEmail) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Change the timezone of this Page
	 * @param timeZone the new timezone
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setTimeZone(String timeZone) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * I'm not quite sure what this property is, but it is documented in the statuspage api and so I put it here
	 * @param notificationsEmailFooter the new value
	 * @since 1.0.0
	 * @see #set(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, 
	 * boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, String)
	 */
	public void setNotificationsEmailFooter(String notificationsEmailFooter) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	/**
	 * Modify the full page. If you need to change multiple values at the same time, it
	 * is more efficient, to use this method instead of calling the methods for each property to change.
	 * @param name the new name
	 * @param domain the new domain
	 * @param subdomain the new subdomain
	 * @param url the new url
	 * @param branding the new branding
	 * @param cssBodyBackgroundColor the new body background color
	 * @param cssFontColor the new font color
	 * @param cssLightFontColor the new light font color
	 * @param cssGreens the new greens color
	 * @param cssYellows the new yellows color
	 * @param cssOranges the new oranges color
	 * @param cssReds the new reds color
	 * @param cssBlues the new blues color
	 * @param cssBorderColor the new border color
	 * @param cssGraphColor the new graph color
	 * @param cssLinkColor the new link color
	 * @param cssNoData the new no data
	 * @param hiddenFromSearch a boolean that indicates the new value
	 * @param viewersMustBeTeamMembers a boolean that indicates the new value
	 * @param allowPageSubscribers a boolean that indicates the new value
	 * @param allowIncidentSubscribers a boolean that indicates the new value
	 * @param allowEmailSubscribers a boolean that indicates the new value
	 * @param allowSmsSubscribers a boolean that indicates the new value
	 * @param allowRssSubscribers a boolean that indicates the new value
	 * @param allowWebhookSubscribers a boolean that indicates the new value
	 * @param notificationsFromEmail a value I don't know what it does. If you know it, help is appreciated
	 * @param timeZone the new time zone
	 * @param notificationsEmailFooter a value I don't know what it does. If you know it, help is appreciated
	 * @since 1.0.0
	 * @see #setName(String)
	 * @see #setDomain(String)
	 * @see #setSubdomain(String)
	 * @see #setUrl(String)
	 * @see #setBranding(String)
	 * @see #setCSSBodyBackgroundColor(String)
	 * @see #setCSSFontColor(String)
	 * @see #setCSSLightFontColor(String)
	 * @see #setCSSGreens(String)
	 * @see #setCSSYellows(String)
	 * @see #setCSSOranges(String)
	 * @see #setCSSReds(String)
	 * @see #setCSSBlues(String)
	 * @see #setCSSBorderColor(String)
	 * @see #setCSSGraphColor(String)
	 * @see #setCSSLinkColor(String)
	 * @see #setCSSNoData(String)
	 * @see #setHiddenFromSearch(boolean)
	 * @see #setViewersMustBeTeamMembers(boolean)
	 * @see #setAllowPageSubscribers(boolean)
	 * @see #setAllowIncidentSubscribers(boolean)
	 * @see #setAllowEmailSubscribers(boolean)
	 * @see #setAllowSmsSubscribers(boolean)
	 * @see #setAllowRssSubscribers(boolean)
	 * @see #setAllowWebhookSubscribers(boolean)
	 * @see #setNotificationsFromEmail(String)
	 * @see #setTimeZone(String)
	 * @see #setNotificationsEmailFooter(String)
	 */
	public void set(String name, String domain, String subdomain, String url, String branding, String cssBodyBackgroundColor, String cssFontColor, String cssLightFontColor, String cssGreens,
			String cssYellows, String cssOranges, String cssReds, String cssBlues, String cssBorderColor, String cssGraphColor, String cssLinkColor, String cssNoData, boolean hiddenFromSearch,
			boolean viewersMustBeTeamMembers, boolean allowPageSubscribers, boolean allowIncidentSubscribers, boolean allowEmailSubscribers, boolean allowSmsSubscribers, boolean allowRssSubscribers,
			boolean allowWebhookSubscribers, String notificationsFromEmail, String timeZone, String notificationsEmailFooter) {
		Route.CompiledRoute route = Route.Pages.UPDATE_FULL_PAGE.compile(id);
		JSONObject payload = new JSONObject().put("page", new JSONObject().put("name", name).put("domain", domain).put("subdomain", subdomain)
				.put("url", url).put("branding", branding).put("css_body_background_color", cssBodyBackgroundColor).put("css_font_color", cssFontColor)
				.put("css_light_font_color", cssLightFontColor).put("css_greens", cssGreens).put("css_yellows", cssYellows).put("css_oranges", cssOranges)
				.put("css_reds", cssReds).put("css_blues", cssBlues).put("css_border_color", cssBorderColor).put("css_graph_color", cssGraphColor)
				.put("css_link_color", cssLinkColor).put("css_no_data", cssNoData).put("hidden_from_search", hiddenFromSearch).put("viewers_must_be_team_members", viewersMustBeTeamMembers)
				.put("allow_page_subscribers", allowPageSubscribers).put("allow_incident_subscribers", allowIncidentSubscribers).put("allow_email_subscribers", allowEmailSubscribers)
				.put("allow_sms_subscribers", allowSmsSubscribers).put("allow_rss_atom_feeds", allowRssSubscribers).put("allow_webhook_subscribers", allowWebhookSubscribers)
				.put("notifications_from_email", notificationsFromEmail).put("time_zone", timeZone).put("notifications_email_footer", notificationsEmailFooter));
		Request request = new Request(route, RequestBody.create(Request.MEDIA_TYPE_JSON, payload.toString()));
		api.getRequester().queueAsync(request, r -> {
			if(r.isSuccessful()) {
				this.name = name;
				this.branding = branding;
				this.subdomain = subdomain;
				this.domain = domain;
				this.url = url;
				this.hiddenFromSearch = hiddenFromSearch;
				this.allowPageSubscribers = allowPageSubscribers;
				this.allowIncidentSubscribers = allowIncidentSubscribers;
				this.allowEmailSubscribers = allowEmailSubscribers;
				this.allowSmsSubscribers = allowSmsSubscribers;
				this.allowRssSubscribers = allowRssSubscribers;
				this.allowWebhookSubscribers = allowWebhookSubscribers;
				this.notificationsFromEmail = notificationsFromEmail;
				this.notificationsEmailFooter = notificationsEmailFooter;
				this.viewersMustBeTeamMembers = viewersMustBeTeamMembers;
				this.timeZone = timeZone;
				this.cssBodyBackgroundColor = cssBodyBackgroundColor;
				this.cssFontColor = cssFontColor;
				this.cssLightFontColor = cssLightFontColor;
				this.cssGreens = cssGreens;
				this.cssYellows = cssYellows;
				this.cssOranges = cssOranges;
				this.cssBlues = cssBlues;
				this.cssReds = cssReds;
				this.cssBorderColor = cssBorderColor;
				this.cssGraphColor = cssGraphColor;
				this.cssLinkColor = cssLinkColor;
				this.cssNoData = cssNoData;
			}
		});
	}
	
	// Getters
	
	/**
	 * Gets the api instance that requested this resource
	 * @return the instance
	 * @since 1.0.0
	 */
	public StatuspageAPI getApi() {
		return api;
	}
	
	/**
	 * Get the activity score of this {@link Page}
	 * @return the activity score as int
	 */
	public int getActivityScore() {
		return activityScore;
	}
	
	/**
	 * Get the branding level of this {@link Page}
	 * @return the branding level
	 */
	public String getBranding() {
		return branding;
	}
	
	/**
	 * Get the city of this {@link Page}
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Get the country of his {@link Page}
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Get the time when this page was created
	 * @return an OffsetDateTime representing the timestamp, when this page was created
	 * @since 1.0.0
	 * @see #getUpdatedAt()
	 */
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * Get the css for the blues
	 * @return the color code
	 */
	public String getCssBlues() {
		return cssBlues;
	}
	
	/**
	 * Get the css for the body background color
	 * @return the color code
	 */
	public String getCssBodyBackgroundColor() {
		return cssBodyBackgroundColor;
	}
	
	/**
	 * Get the css for the border color
	 * @return the color code
	 */
	public String getCssBorderColor() {
		return cssBorderColor;
	}
	
	/**
	 * Get the css for the font color
	 * @return the color code
	 */
	public String getCssFontColor() {
		return cssFontColor;
	}
	
	/**
	 * Get the css for the graph color
	 * @return the color code
	 */
	public String getCssGraphColor() {
		return cssGraphColor;
	}
	
	/**
	 * Get the css for the greens
	 * @return the color code
	 */
	public String getCssGreens() {
		return cssGreens;
	}
	
	/**
	 * Get the css for the light font color
	 * @return the color code
	 */
	public String getCssLightFontColor() {
		return cssLightFontColor;
	}
	
	/**
	 * Get the css for the link color
	 * @return the color code
	 */
	public String getCssLinkColor() {
		return cssLinkColor;
	}
	
	/**
	 * Get the css for the no data
	 * @return the color code
	 */
	public String getCssNoData() {
		return cssNoData;
	}
	
	/**
	 * Get the css for the oranges
	 * @return the color code
	 */
	public String getCssOranges() {
		return cssOranges;
	}
	
	/**
	 * Get the css for the reds
	 * @return the color code
	 */
	public String getCssReds() {
		return cssReds;
	}
	
	/**
	 * Get the css for the yellows
	 * @return the color code
	 */
	public String getCssYellows() {
		return cssYellows;
	}
	
	/**
	 * Get the custom domain for this {@link Page}
	 * @return the custom domain
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Get the EmailLogo for this {@link Page}
	 * @return the EmailLogo
	 */
	public EmailLogo getEmailLogo() {
		return emailLogo;
	}
	
	/**
	 * Get the FaviconLogo for this {@link Page}
	 * @return the FaviconLogo
	 */
	public FaviconLogo getFaviconLogo() {
		return faviconLogo;
	}
	
	/**
	 * Get the headline for this {@link Page}
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}
	
	/**
	 * Get the HeroCover for this {@link Page}
	 * @return the HeroCover
	 */
	public HeroCover getHeroCover() {
		return heroCover;
	}
	
	/**
	 * Get the id of this {@link Page}
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the ip restrictions of this {@link Page}
	 * @return the ip restrictions
	 */
	public String getIpRestrictions() {
		return ipRestrictions;
	}
	
	/**
	 * Get the name of this {@link Page}
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the footer for the notification emails sent by this {@link Page}
	 * @return the email footer
	 */
	public String getNotificationsEmailFooter() {
		return notificationsEmailFooter;
	}
	
	/**
	 * This is a value where I don't know what it does. 
	 * It is documented in the statuspage.io api docs so I put it here.
	 * If someone knows what this does, help is appreciated
	 * @return the value
	 */
	public String getNotificationsFromEmail() {
		return notificationsFromEmail;
	}
	
	/**
	 * Get the page description of this {@link Page}
	 * @return the description
	 */
	public String getPageDescription() {
		return pageDescription;
	}
	
	/**
	 * Get the state of the {@link Page}
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Get the subdomain of the {@link Page}
	 * @return the subdomain
	 */
	public String getSubdomain() {
		return subdomain;
	}
	
	/**
	 * Get the support url of the {@link Page}
	 * @return the support url
	 */
	public String getSupportUrl() {
		return supportUrl;
	}
	
	/**
	 * Get the time zone of the {@link Page}
	 * @return the time zone
	 */
	public String getTimeZone() {
		return timeZone;
	}
	
	/**
	 * Get the TransactionalLogo of the {@link Page}
	 * @return the TransactionalLogo
	 */
	public TransactionalLogo getTransactionalLogo() {
		return transactionalLogo;
	}
	
	/**
	 * Get the TwitterLogo of the {@link Page}
	 * @return the TwitterLogo
	 */
	public TwitterLogo getTwitterLogo() {
		return twitterLogo;
	}
	
	/**
	 * Get the twitter username of the {@link Page}
	 * @return the twitter username
	 */
	public String getTwitterUsername() {
		return twitterUsername;
	}
	
	/**
	 * Get the time when this page was updated
	 * @return an OffsetDateTime representing the timestamp, when this page was updated
	 * @since 1.0.0
	 * @see #getCreatedAt()
	 */
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * Get the url of this {@link Page}
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Get if the page accepts subscriptions via email
	 * @return a boolean representing the value
	 */
	public boolean isAllowEmailSubscribers() {
		return allowEmailSubscribers;
	}
	
	/**
	 * Get if the page accepts subscriptions to specific incidents
	 * @return a boolean representing the value
	 */
	public boolean isAllowIncidentSubscribers() {
		return allowIncidentSubscribers;
	}
	
	/**
	 * Get if the page accepts subscriptions
	 * @return a boolean representing the value
	 */
	public boolean isAllowPageSubscribers() {
		return allowPageSubscribers;
	}
	
	/**
	 * Get if the rss feed is enabled
	 * @return a boolean representing the value
	 */
	public boolean isAllowRssSubscribers() {
		return allowRssSubscribers;
	}
	
	/**
	 * Get if the page accepts subscriptions via sms
	 * @return a boolean representing the value
	 */
	public boolean isAllowSmsSubscribers() {
		return allowSmsSubscribers;
	}
	
	/**
	 * Get if the page accepts subscriptions via webhook
	 * @return a boolean representing the value
	 */
	public boolean isAllowWebhookSubscribers() {
		return allowWebhookSubscribers;
	}
	
	/**
	 * Get if the page is hidden from searches
	 * @return a boolean representing the value
	 */
	public boolean isHiddenFromSearch() {
		return hiddenFromSearch;
	}
	
	/**
	 * Get if the page can only be viewed by team members
	 * @return a boolean representing the value
	 */
	public boolean isViewersMustBeTeamMembers() {
		return viewersMustBeTeamMembers;
	}
	
	public static class HeroCover {
		public final OffsetDateTime updatedAt;
		public final Integer size;
		public final String originalUrl;
		public final String retinaUrl;
		public final String normalUrl;
		
		private HeroCover(OffsetDateTime updatedAt, Integer size, String originalUrl, String retinaUrl, String normalUrl) {
			this.updatedAt = updatedAt;
			this.size = size;
			this.originalUrl = originalUrl;
			this.retinaUrl = retinaUrl;
			this.normalUrl = normalUrl;
		}
		
		public static HeroCover fromJson(JSONObject json) {
			OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
			Integer size = json.get("size") instanceof Number ? json.getInt("size") : null;
			String originalUrl = json.get("original_url") instanceof String ? json.getString("original_url") : null;
			String retinaUrl = json.get("retina_url") instanceof String ? json.getString("retina_url") : null;
			String normalUrl = json.get("normal_url") instanceof String ? json.getString("normal_url") : null;
			return new HeroCover(updatedAt, size, originalUrl, retinaUrl, normalUrl);
		}
	}
	
	public static class TransactionalLogo {
		public final OffsetDateTime updatedAt;
		public final Integer size;
		public final String originalUrl;
		public final String retinaUrl;
		public final String normalUrl;
		
		private TransactionalLogo(OffsetDateTime updatedAt, Integer size, String originalUrl, String retinaUrl, String normalUrl) {
			this.updatedAt = updatedAt;
			this.size = size;
			this.originalUrl = originalUrl;
			this.retinaUrl = retinaUrl;
			this.normalUrl = normalUrl;
		}
		
		public static TransactionalLogo fromJson(JSONObject json) {
			OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
			Integer size = json.get("size") instanceof Number ? json.getInt("size") : null;
			String originalUrl = json.get("original_url") instanceof String ? json.getString("original_url") : null;
			String retinaUrl = json.get("retina_url") instanceof String ? json.getString("retina_url") : null;
			String normalUrl = json.get("normal_url") instanceof String ? json.getString("normal_url") : null;
			return new TransactionalLogo(updatedAt, size, originalUrl, retinaUrl, normalUrl);
		}
	}
	
	public static class FaviconLogo {
		public final OffsetDateTime updatedAt;
		public final Integer size;
		public final String url;
		
		private FaviconLogo(OffsetDateTime updatedAt, Integer size, String url) {
			this.updatedAt = updatedAt;
			this.size = size;
			this.url = url;
		}
		
		public static FaviconLogo fromJson(JSONObject json) {
			OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
			Integer size = json.get("size") instanceof Number ? json.getInt("size") : null;
			String url = json.get("url") instanceof String ? json.getString("url") : null;
			return new FaviconLogo(updatedAt, size, url);
		}
	}
	
	public static class TwitterLogo {
		public final OffsetDateTime updatedAt;
		public final Integer size;
		public final String url;
		
		private TwitterLogo(OffsetDateTime updatedAt, Integer size, String url) {
			this.updatedAt = updatedAt;
			this.size = size;
			this.url = url;
		}
		
		public static TwitterLogo fromJson(JSONObject json) {
			OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
			Integer size = json.get("size") instanceof Number ? json.getInt("size") : null;
			String url = json.get("url") instanceof String ? json.getString("url") : null;
			return new TwitterLogo(updatedAt, size, url);
		}
	}
	
	public static class EmailLogo {
		public final OffsetDateTime updatedAt;
		public final Integer size;
		public final String originalUrl;
		public final String retinaUrl;
		public final String normalUrl;
		
		private EmailLogo(OffsetDateTime updatedAt, Integer size, String originalUrl, String retinaUrl, String normalUrl) {
			this.updatedAt = updatedAt;
			this.size = size;
			this.originalUrl = originalUrl;
			this.retinaUrl = retinaUrl;
			this.normalUrl = normalUrl;
		}
		
		public static EmailLogo fromJson(JSONObject json) {
			OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
			Integer size = json.get("size") instanceof Number ? json.getInt("size") : null;
			String originalUrl = json.get("original_url") instanceof String ? json.getString("original_url") : null;
			String retinaUrl = json.get("retina_url") instanceof String ? json.getString("retina_url") : null;
			String normalUrl = json.get("normal_url") instanceof String ? json.getString("normal_url") : null;
			return new EmailLogo(updatedAt, size, originalUrl, retinaUrl, normalUrl);
		}
	}
	
	@Override
	public String toString() {
		return "Page(id="+id+", name="+name+")";
	}
}
