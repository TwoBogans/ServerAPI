package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

/**
 * An offline player
 */
public class OfflinePlayer {

    @Expose
    private String uuid = null;

    @Expose
    private String name = null;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public OfflinePlayer uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * The Player's display name
     *
     * @return displayName
     **/
    public String getDisplayName() {
        return name;
    }

    public void setDisplayName(String displayName) {
        this.name = displayName;
    }

    public OfflinePlayer displayName(String displayName) {
        this.name = displayName;
        return this;
    }


}
