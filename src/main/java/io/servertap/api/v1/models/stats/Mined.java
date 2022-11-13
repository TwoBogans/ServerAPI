package io.servertap.api.v1.models.stats;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

public class Mined {

    @Expose
    @Getter
    @Setter
    private long obsidian;

    @Expose
    @Getter
    @Setter
    private long enderChest;
}
