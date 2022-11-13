package io.servertap.api.v1.models;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

public class Queue {

    @Expose
    @Getter
    @Setter
    private int regular;

    @Expose
    @Getter
    @Setter
    private int priority;

    @Expose
    @Getter
    @Setter
    private int veteran;

}
