package io.servertap.api.v1.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

@Getter
@Setter
@ToString
public class ChatMessage {
    @Expose private String name;
    @Expose private String message;
    @Expose private String type;
}
