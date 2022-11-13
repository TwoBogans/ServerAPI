package io.servertap.api.v1.models.stats;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

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
