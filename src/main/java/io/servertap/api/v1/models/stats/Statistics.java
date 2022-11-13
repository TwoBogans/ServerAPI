package io.servertap.api.v1.models.stats;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

public class Statistics {

    @Expose
    @Getter
    @Setter
    private long playerKills;

    @Expose
    @Getter
    @Setter
    private long mobKills;

    @Expose
    @Getter
    @Setter
    private long deaths;

    @Expose
    @Setter
    private long distanceFlown;

    @Expose
    @Getter
    @Setter
    private Mined mined = new Mined();

    @Expose
    @Getter
    @Setter
    private Placed placed = new Placed();

}
