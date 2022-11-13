package io.servertap.api.v1.models;

import lombok.Getter;
import lombok.Setter;
import io.servertap.api.v1.models.stats.Statistics;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

public class Player {

    @Expose
    @Getter
    @Setter
    private String uuid;

    @Expose
    @Getter
    @Setter
    private String displayName;

    @Expose
    @Getter
    @Setter
    private long ticksAlive;

    @Expose
    @Getter
    @Setter
    private long joinDateMillis;

    @Expose
    @Getter
    @Setter
    private long playTimeTicks;

    @Expose
    @Getter
    @Setter
    private String joinDate;

    @Expose
    @Getter
    @Setter
    private String playTime;

    @Expose
    @Getter
    @Setter
    private String lastDeath;

    @Expose
    @Getter
    @Setter
    private Statistics statistics = new Statistics();

}
