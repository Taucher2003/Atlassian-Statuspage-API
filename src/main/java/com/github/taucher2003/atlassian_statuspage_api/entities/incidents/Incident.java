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

package com.github.taucher2003.atlassian_statuspage_api.entities.incidents;

import com.github.taucher2003.atlassian_statuspage_api.StatuspageAPI;
import com.github.taucher2003.atlassian_statuspage_api.entities.Component;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Incident {

    private final StatuspageAPI api;

    private String id;
    private List<Component> components;
    private OffsetDateTime createdAt;
    private IncidentImpact impact;
    private IncidentImpact impactOverride;
    private List<IncidentUpdate> incidentUpdates;
    private HashMap<String, HashMap<String, Object>> metadata;
    private OffsetDateTime monitoringAt;
    private String name;
    private String pageId;
    private String postmortemBody;
    private OffsetDateTime postmortemBodyLastUpdatedAt;
    private boolean postmortemIgnored;
    private boolean postmortemNotifiedSubscribers;
    private boolean postmortemNotifiedTwitter;
    private OffsetDateTime postmortemPublishedAt;
    private OffsetDateTime resolvedAt;
    private boolean scheduledAutoCompleted;
    private boolean scheduledAutoInProgress;
    private OffsetDateTime scheduledFor;
    private boolean scheduledRemindPrior;
    private OffsetDateTime scheduledRemindedAt;
    private OffsetDateTime scheduledUntil;
    private String shortlink;
    private IncidentType type;
    private OffsetDateTime updatedAt;

    private Incident(StatuspageAPI api, String id, List<Component> components, OffsetDateTime createdAt, IncidentImpact impact,
                     IncidentImpact impactOverride, List<IncidentUpdate> incidentUpdates,
                     HashMap<String, HashMap<String, Object>> metadata, OffsetDateTime monitoringAt, String name, String pageId,
                     String postmortemBody, OffsetDateTime postmortemBodyLastUpdatedAt, boolean postmortemIgnored,
                     boolean postmortemNotifiedSubscribers, boolean postmortemNotifiedTwitter, OffsetDateTime postmortemPublishedAt,
                     OffsetDateTime resolvedAt, boolean scheduledAutoCompleted, boolean scheduledAutoInProgress,
                     OffsetDateTime scheduledFor, boolean scheduledRemindPrior, OffsetDateTime scheduledRemindedAt,
                     OffsetDateTime scheduledUntil, String shortlink, IncidentType type, OffsetDateTime updatedAt) {
        this.api = api;
        this.id = id;
        this.components = components;
        this.createdAt = createdAt;
        this.impact = impact;
        this.impactOverride = impactOverride;
        this.incidentUpdates = incidentUpdates;
        this.metadata = metadata;
        this.monitoringAt = monitoringAt;
        this.name = name;
        this.pageId = pageId;
        this.postmortemBody = postmortemBody;
        this.postmortemBodyLastUpdatedAt = postmortemBodyLastUpdatedAt;
        this.postmortemIgnored = postmortemIgnored;
        this.postmortemNotifiedSubscribers = postmortemNotifiedSubscribers;
        this.postmortemNotifiedTwitter = postmortemNotifiedTwitter;
        this.postmortemPublishedAt = postmortemPublishedAt;
        this.resolvedAt = resolvedAt;
        this.scheduledAutoCompleted = scheduledAutoCompleted;
        this.scheduledAutoInProgress = scheduledAutoInProgress;
        this.scheduledFor = scheduledFor;
        this.scheduledRemindPrior = scheduledRemindPrior;
        this.scheduledRemindedAt = scheduledRemindedAt;
        this.scheduledUntil = scheduledUntil;
        this.shortlink = shortlink;
        this.type = type;
        this.updatedAt = updatedAt;
    }

    public static Incident fromJson(StatuspageAPI api, JSONObject json) {
        String id = json.get("id") instanceof String ? json.getString("id") : null;
        List<Component> components = new ArrayList<>();
        for (Object jsonObj : json.getJSONArray("components")) {
            if (jsonObj instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) jsonObj;
                Component component = Component.fromJson(api, jsonObject);
                components.add(component);
            } else {
                System.err.println(jsonObj + " was not an JSONObject");
            }
        }
        OffsetDateTime createdAt = json.get("created_at") instanceof String ? OffsetDateTime.parse(json.getString("created_at")) : null;
        IncidentImpact impact = json.get("impact") instanceof String ? IncidentImpact.valueOf(json.getString("impact")) : IncidentImpact.NONE;
        IncidentImpact impactOverride = json.get("impact_override") instanceof String ? IncidentImpact.valueOf(json.getString("impact_override")) : IncidentImpact.NONE;
        List<IncidentUpdate> incidentUpdates = new ArrayList<>();
        for (Object jsonObj : json.getJSONArray("incident_updates")) {
            if (jsonObj instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) jsonObj;
                IncidentUpdate incidentUpdate = IncidentUpdate.fromJson(api, jsonObject);
                incidentUpdates.add(incidentUpdate);
            } else {
                System.err.println(jsonObj + " was not an JSONObject");
            }
        }
        HashMap<String, HashMap<String, Object>> metadata = new HashMap<>();
        for (String key : json.getJSONObject("metadata").keySet()) {
            HashMap<String, Object> meta = new HashMap<>();
            for (String subkey : json.getJSONObject("metadata").getJSONObject(key).keySet()) {
                meta.put(subkey, json.getJSONObject("metadata").getJSONObject(key).get(subkey));
            }
            metadata.put(key, meta);
        }
        OffsetDateTime monitoringAt = json.get("monitoring_at") instanceof String ? OffsetDateTime.parse(json.getString("monitoring_at")) : null;
        String name = json.get("name") instanceof String ? json.getString("name") : null;
        String pageId = json.get("page_id") instanceof String ? json.getString("page_id") : null;
        String postmortemBody = json.get("postmortem_body") instanceof String ? json.getString("postmortem_body") : null;
        OffsetDateTime postmortemBodyLastUpdatedAt = json.get("postmortem_body_last_updated_at") instanceof String ? OffsetDateTime.parse(json.getString("postmortem_body_last_updated_at")) : null;
        boolean postmortemIgnored = json.getBoolean("postmortem_ignored");
        boolean postmortemNotifiedSubscribers = json.getBoolean("postmortem_notified_subscribers");
        boolean postmortemNotifiedTwitter = json.getBoolean("postmortem_notified_twitter");
        OffsetDateTime postmortemPublishedAt = json.get("postmortem_published_at") instanceof String ? OffsetDateTime.parse(json.getString("postmortem_published_at")) : null;
        OffsetDateTime resolvedAt = json.get("resolved_at") instanceof String ? OffsetDateTime.parse(json.getString("resolved_at")) : null;
        boolean scheduledAutoCompleted = json.getBoolean("scheduled_auto_completed");
        boolean scheduledAutoInProgress = json.getBoolean("scheduled_auto_in_progress");
        OffsetDateTime scheduledFor = json.get("scheduled_for") instanceof String ? OffsetDateTime.parse(json.getString("scheduled_for")) : null;
        boolean scheduledRemindPrior = json.getBoolean("scheduled_remind_prior");
        OffsetDateTime scheduledRemindedAt = json.get("scheduled_reminded_at") instanceof String ? OffsetDateTime.parse(json.getString("scheduled_reminded_at")) : null;
        OffsetDateTime scheduledUntil = json.get("scheduled_until") instanceof String ? OffsetDateTime.parse(json.getString("scheduled_until")) : null;
        String shortlink = json.get("shortlink") instanceof String ? json.getString("shortlink") : null;
        IncidentType type = json.get("status") instanceof String ? IncidentType.valueOf("status") : IncidentType.REALTIME;
        OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
        return new Incident(api, id, components, createdAt, impact, impactOverride, incidentUpdates, metadata, monitoringAt, name,
                pageId, postmortemBody, postmortemBodyLastUpdatedAt, postmortemIgnored, postmortemNotifiedSubscribers,
                postmortemNotifiedTwitter, postmortemPublishedAt, resolvedAt, scheduledAutoCompleted, scheduledAutoInProgress,
                scheduledFor, scheduledRemindPrior, scheduledRemindedAt, scheduledUntil, shortlink, type, updatedAt);
    }

    public void set(String name, IncidentStatus status, IncidentImpact impactOverride, OffsetDateTime scheduledFor,
                    OffsetDateTime scheduledUntil, boolean scheduledRemindPrior, boolean scheduledAutoInProgress,
                    boolean scheduledAutoCompleted, HashMap<String, HashMap<String, Object>> metadata,
                    String body) {

    }

    public StatuspageAPI getApi() {
        return api;
    }

    public List<Component> getComponents() {
        return components;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public IncidentImpact getImpact() {
        return impact;
    }

    public IncidentImpact getImpactOverride() {
        return impactOverride;
    }

    public List<IncidentUpdate> getIncidentUpdates() {
        return incidentUpdates;
    }

    public HashMap<String, HashMap<String, Object>> getMetadata() {
        return metadata;
    }

    public OffsetDateTime getMonitoringAt() {
        return monitoringAt;
    }

    public String getName() {
        return name;
    }

    public String getPageId() {
        return pageId;
    }

    public String getPostmortemBody() {
        return postmortemBody;
    }

    public OffsetDateTime getPostmortemBodyLastUpdatedAt() {
        return postmortemBodyLastUpdatedAt;
    }

    public OffsetDateTime getPostmortemPublishedAt() {
        return postmortemPublishedAt;
    }

    public OffsetDateTime getResolvedAt() {
        return resolvedAt;
    }

    public OffsetDateTime getScheduledFor() {
        return scheduledFor;
    }

    public OffsetDateTime getScheduledRemindedAt() {
        return scheduledRemindedAt;
    }

    public OffsetDateTime getScheduledUntil() {
        return scheduledUntil;
    }

    public String getShortlink() {
        return shortlink;
    }

    public IncidentType getType() {
        return type;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isPostmortemIgnored() {
        return postmortemIgnored;
    }

    public boolean isPostmortemNotifiedSubscribers() {
        return postmortemNotifiedSubscribers;
    }

    public boolean isPostmortemNotifiedTwitter() {
        return postmortemNotifiedTwitter;
    }

    public boolean isScheduledAutoCompleted() {
        return scheduledAutoCompleted;
    }

    public boolean isScheduledAutoInProgress() {
        return scheduledAutoInProgress;
    }

    public boolean isScheduledRemindPrior() {
        return scheduledRemindPrior;
    }
}
