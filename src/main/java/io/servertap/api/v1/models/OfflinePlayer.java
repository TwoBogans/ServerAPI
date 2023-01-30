package io.servertap.api.v1.models;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

import java.util.UUID;

@Getter
@Setter
public class OfflinePlayer {
    @Expose private UUID uuid;
    @Expose private String name;
    @Expose private String lastSeen;
    @Expose private long firstPlayed;
    @Expose private long lastPlayed;
}
