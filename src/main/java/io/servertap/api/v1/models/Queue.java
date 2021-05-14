package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

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
