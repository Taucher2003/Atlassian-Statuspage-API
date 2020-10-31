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

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import de.taucher.atlassian_statuspage_api.StatuspageAPI;
import de.taucher.atlassian_statuspage_api.requests.Request;
import de.taucher.atlassian_statuspage_api.requests.Route;

public class Page {
	
	private StatuspageAPI api;

	private String id;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
	private String name, 
					pageDescription, 
					headline,
					branding,
					subdomain,
					domain,
					url,
					supportUrl;
	private boolean hiddenFromSearch,
					allowPageSubscribers,
					allowIncidentSubscribers,
					allowEmailSubscribers,
					allowSmsSubscribers,
					allowRssSubscribers,
					allowWebhookSubscribers;
	private String notificationsFromEmail,
					notificationsEmailFooter;
	private int activityScore;
	private String twitterUsername;
	private boolean viewersMustBeTeamMembers;
	private String ipRestrictions,
					city,
					state,
					country,
					timeZone,
					cssBodyBackgroundColor,
					cssFontColor,
					cssLightFontColor,
					cssGreens,
					cssYellows,
					cssOranges,
					cssBlues,
					cssReds,
					cssBorderColor,
					cssGraphColor,
					cssLinkColor,
					cssNoData;
	private FaviconLogo faviconLogo;
	private TransactionalLogo transactionalLogo;
	private HeroCover heroCover;
	private EmailLogo emailLogo;
	private TwitterLogo twitterLogo;
	
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
	
	// Modify
	
	public void setName(String name) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setDomain(String domain) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setSubdomain(String subdomain) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setUrl(String url) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setBranding(String branding) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSBodyBackgroundColor(String cssBodyBackgroundColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSFontColor(String cssFontColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSLightFontColor(String cssLightFontColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSGreens(String cssGreens) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSYellows(String cssYellows) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSOranges(String cssOranges) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSReds(String cssReds) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSBlues(String cssBlues) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSBorderColor(String cssBorderColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSGraphColor(String cssGraphColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSLinkColor(String cssLinkColor) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setCSSNoData(String cssNoData) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setHiddenFromSearch(boolean hiddenFromSearch) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setViewersMustBeTeamMembers(boolean viewersMustBeTeamMembers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setAllowPageSubscribers(boolean allowPageSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setAllowIncidentSubscribers(boolean allowIncidentSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setAllowEmailSubscribers(boolean allowEmailSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setAllowSmsSubscribers(boolean allowSmsSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setAllowRssSubscribers(boolean allowRssSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setAllowWebhookSubscribers(boolean allowWebhookSubscribers) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setNotificationsFromEmail(String notificationsFromEmail) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setTimeZone(String timeZone) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
	public void setNotificationsEmailFooter(String notificationsEmailFooter) {
		set(name, domain, subdomain, url, branding, cssBodyBackgroundColor, cssFontColor, cssLightFontColor, cssGreens, cssYellows, cssOranges, cssReds, cssBlues, cssBorderColor, cssGraphColor,
				cssLinkColor, cssNoData, hiddenFromSearch, viewersMustBeTeamMembers, allowPageSubscribers, allowIncidentSubscribers, allowEmailSubscribers, allowSmsSubscribers, allowRssSubscribers,
				allowWebhookSubscribers, notificationsFromEmail, timeZone, notificationsEmailFooter);
	}
	
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
	
	public StatuspageAPI getApi() {
		return api;
	}
	
	public int getActivityScore() {
		return activityScore;
	}
	
	public String getBranding() {
		return branding;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public String getCssBlues() {
		return cssBlues;
	}
	
	public String getCssBodyBackgroundColor() {
		return cssBodyBackgroundColor;
	}
	
	public String getCssBorderColor() {
		return cssBorderColor;
	}
	
	public String getCssFontColor() {
		return cssFontColor;
	}
	
	public String getCssGraphColor() {
		return cssGraphColor;
	}
	
	public String getCssGreens() {
		return cssGreens;
	}
	
	public String getCssLightFontColor() {
		return cssLightFontColor;
	}
	
	public String getCssLinkColor() {
		return cssLinkColor;
	}
	
	public String getCssNoData() {
		return cssNoData;
	}
	
	public String getCssOranges() {
		return cssOranges;
	}
	
	public String getCssReds() {
		return cssReds;
	}
	
	public String getCssYellows() {
		return cssYellows;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public EmailLogo getEmailLogo() {
		return emailLogo;
	}
	
	public FaviconLogo getFaviconLogo() {
		return faviconLogo;
	}
	
	public String getHeadline() {
		return headline;
	}
	
	public HeroCover getHeroCover() {
		return heroCover;
	}
	
	public String getId() {
		return id;
	}
	
	public String getIpRestrictions() {
		return ipRestrictions;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNotificationsEmailFooter() {
		return notificationsEmailFooter;
	}
	
	public String getNotificationsFromEmail() {
		return notificationsFromEmail;
	}
	
	public String getPageDescription() {
		return pageDescription;
	}
	
	public String getState() {
		return state;
	}
	
	public String getSubdomain() {
		return subdomain;
	}
	
	public String getSupportUrl() {
		return supportUrl;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	
	public TransactionalLogo getTransactionalLogo() {
		return transactionalLogo;
	}
	
	public TwitterLogo getTwitterLogo() {
		return twitterLogo;
	}
	
	public String getTwitterUsername() {
		return twitterUsername;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public String getUrl() {
		return url;
	}
	
	public boolean isAllowEmailSubscribers() {
		return allowEmailSubscribers;
	}
	
	public boolean isAllowIncidentSubscribers() {
		return allowIncidentSubscribers;
	}
	
	public boolean isAllowPageSubscribers() {
		return allowPageSubscribers;
	}
	
	public boolean isAllowRssSubscribers() {
		return allowRssSubscribers;
	}
	
	public boolean isAllowSmsSubscribers() {
		return allowSmsSubscribers;
	}
	
	public boolean isAllowWebhookSubscribers() {
		return allowWebhookSubscribers;
	}
	
	public boolean isHiddenFromSearch() {
		return hiddenFromSearch;
	}
	
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
