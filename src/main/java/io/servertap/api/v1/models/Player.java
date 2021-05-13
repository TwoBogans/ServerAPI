package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

/**
 * An online player
 */
public class Player {
    @Expose
    private String uuid = null;

    @Expose
    private String displayName = null;

    /**
     * The Player's UUID
     *
     * @return uuid
     **/
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Player uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * The Player's display name
     *
     * @return displayName
     **/
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Player displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

}
