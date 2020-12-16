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
import com.github.taucher2003.atlassian_statuspage_api.entities.Status;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class IncidentUpdate {

    private final StatuspageAPI api;

    private String id;
    private String incidentId;
    private List<IncidentUpdateAffectedComponent> affectedComponents;
    private String body;
    private OffsetDateTime createdAt;
    private String customTweet;
    private boolean deliverNotifications;
    private OffsetDateTime displayAt;
    private IncidentStatus status;
    private String tweetId;
    private OffsetDateTime twitterUpdatedAt;
    private OffsetDateTime updatedAt;
    private boolean wantsTwitterUpdate;

    private IncidentUpdate(StatuspageAPI api,
                           String id, String incidentId, List<IncidentUpdateAffectedComponent> affectedComponents, String body,
                           OffsetDateTime createdAt, String customTweet, boolean deliverNotifications, OffsetDateTime displayAt,
                           IncidentStatus status, String tweetId, OffsetDateTime twitterUpdatedAt, OffsetDateTime updatedAt, boolean wantsTwitterUpdate){
        this.api = api;
        this.id = id;
        this.incidentId = incidentId;
        this.affectedComponents = affectedComponents;
        this.body = body;
        this.createdAt = createdAt;
        this.customTweet = customTweet;
        this.deliverNotifications = deliverNotifications;
        this.displayAt = displayAt;
        this.status = status;
        this.tweetId = tweetId;
        this.twitterUpdatedAt = twitterUpdatedAt;
        this.updatedAt = updatedAt;
        this.wantsTwitterUpdate = wantsTwitterUpdate;
    }

    public static IncidentUpdate fromJson(StatuspageAPI api, JSONObject json){
        String id = json.get("id") instanceof String ? json.getString("id") : null;
        String incidentId = json.get("incident_id") instanceof String ? json.getString("incident_id") : null;
        List<IncidentUpdateAffectedComponent> incidentUpdateAffectedComponents = new ArrayList<>();
        for(Object obj : json.getJSONArray("affected_components")){
            if (obj instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) obj;
                IncidentUpdateAffectedComponent incidentUpdateAffectedComponent = IncidentUpdateAffectedComponent.fromJson(api, jsonObject);
                incidentUpdateAffectedComponents.add(incidentUpdateAffectedComponent);
            } else {
                System.err.println(obj + " was not an JSONObject");
            }
        }
        String body = json.get("body") instanceof String ? json.getString("body") : null;
        OffsetDateTime createdAt = json.get("created_at") instanceof String ? OffsetDateTime.parse(json.getString("created_at")) : null;
        String customTweet = json.get("custom_tweet") instanceof String ? json.getString("custom_tweet") : null;
        boolean deliverNotifications = json.getBoolean("deliver_notifications");
        OffsetDateTime displayAt = json.get("display_at") instanceof String ? OffsetDateTime.parse(json.getString("display_at")) : null;
        IncidentStatus status = json.get("status") instanceof String ? IncidentStatus.valueOf(json.getString("status")) : null;
        String tweetId = json.get("tweet_id") instanceof String ? json.getString("tweet_id") : null;
        OffsetDateTime twitterUpdatedAt = json.get("twitter_updated_at") instanceof String ?
                OffsetDateTime.parse(json.getString("twitter_updated_at")) : null;
        OffsetDateTime updatedAt = json.get("updated_at") instanceof String ? OffsetDateTime.parse(json.getString("updated_at")) : null;
        boolean wantsTwitterUpdate = json.getBoolean("wants_twitter_update");
        return new IncidentUpdate(api, id, incidentId, incidentUpdateAffectedComponents, body, createdAt, customTweet, deliverNotifications,
                displayAt, status, tweetId, twitterUpdatedAt, updatedAt, wantsTwitterUpdate);
    }

    public StatuspageAPI getApi() {
        return api;
    }

    public String getId() {
        return id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public List<IncidentUpdateAffectedComponent> getAffectedComponents() {
        return affectedComponents;
    }

    public String getBody() {
        return body;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCustomTweet() {
        return customTweet;
    }

    public OffsetDateTime getDisplayAt() {
        return displayAt;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public String getTweetId() {
        return tweetId;
    }

    public OffsetDateTime getTwitterUpdatedAt() {
        return twitterUpdatedAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeliverNotifications() {
        return deliverNotifications;
    }

    public boolean isWantsTwitterUpdate() {
        return wantsTwitterUpdate;
    }

    public static class IncidentUpdateAffectedComponent {
        private final StatuspageAPI api;

        private String code;
        private String name;
        private Status oldStatus;
        private Status newStatus;

        private IncidentUpdateAffectedComponent(StatuspageAPI api, String code, String name, Status oldStatus, Status newStatus){
            this.api = api;
            this.code = code;
            this.name = name;
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;
        }

        public static IncidentUpdateAffectedComponent fromJson(StatuspageAPI api, JSONObject json){
            String code = json.get("code") instanceof String ? json.getString("code") : null;
            String name = json.get("name") instanceof String ? json.getString("name") : null;
            Status oldStatus = json.get("old_status") instanceof String ? Status.valueOf(json.getString("old_status")) : null;
            Status newStatus = json.get("new_status") instanceof String ? Status.valueOf(json.getString("new_status")) : null;
            return new IncidentUpdateAffectedComponent(api, code, name, oldStatus, newStatus);
        }

        public StatuspageAPI getApi() {
            return api;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Status getOldStatus() {
            return oldStatus;
        }

        public Status getNewStatus() {
            return newStatus;
        }
    }
}
