package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

public class Motd {

    @Expose
    private String random;

    public void setMotd(String motd) { this.random = motd; }

    public String getMotd() { return random; }
}
