package io.servertap.api.v1.models.stats;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public class Placed {

    @Expose
    @Getter
    @Setter
    private long obsidian;

    @Expose
    @Getter
    @Setter
    private long enderChests;

}
