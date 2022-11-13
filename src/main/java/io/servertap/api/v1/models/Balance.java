package io.servertap.api.v1.models;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

public class Balance {

    @Expose
    @Getter
    @Setter
    private int wallet;

    @Expose
    @Getter
    @Setter
    private int costs;

    @Expose
    @Getter
    @Setter
    private String currency;

}
