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
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
				String id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String name, String pageDescription, String headline, String subdomain, String domain, String url, String supportUrl,
				boolean hiddenFromSearch, boolean allowPageSubscribers, boolean allowIncidentSubscribers, boolean allowEmailSubscribers, boolean allowSmsSubscribers, boolean allowRssSubscribers,
				boolean allowWebhookSubscribers, String notificationsFromEmail, String notificationsEmailFooter, int activityScore, String twitterUsername, boolean viewersMustBeTeamMembers,
				String ipRestrictions, String city, String state, String country, String timeZone, String cssBodyBackgroundColor, String cssFontColor, String cssLightFontColor, String cssGreens,
				String cssYellows, String cssOranges, String cssBlues, String cssReds, String cssBoderColor, String cssGraphColor, String cssLinkColor, String cssNoData, FaviconLogo faviconLogo,
				TransactionalLogo transactionalLogo, HeroCover heroCover, EmailLogo emailLogo, TwitterLogo twitterLogo) {
		this.api = api;
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.pageDescription = pageDescription;
		this.headline = headline;
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
		this.cssBorderColor = cssBoderColor;
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
				name, pageDescription, headline, subdomain, domain, url,
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
		Component comp = null;
		Route.CompiledRoute route = Route.Components.GET_COMPONENT.compile(id, componentId);
		Request request = new Request(route, Request.EMPTY_BODY);
		try {
			Response response = api.getRequester().queue(request);
			JSONObject json = new JSONObject(response.body().string());
			comp = Component.fromJson(api, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp;
	}
	
	//Getters
	
	public StatuspageAPI getApi() {
		return api;
	}
	
	public int getActivityScore() {
		return activityScore;
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
