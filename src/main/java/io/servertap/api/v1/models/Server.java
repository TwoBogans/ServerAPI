package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public class Server {

    @Expose
    @Getter
    @Setter
    private String name;

    @Expose
    @Getter
    @Setter
    private String motd;

    @Expose
    @Getter
    @Setter
    private String version;

    @Expose
    @Getter
    @Setter
    private String uptime;

    @Expose
    @Getter
    @Setter
    private int online;

    @Expose
    @Getter
    @Setter
    private Performance performance = new Performance();

}
