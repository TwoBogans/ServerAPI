package io.servertap.paypal;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public class Link {
    @Getter
    @Setter
    @Expose
    private String rel;
    @Getter
    @Setter
    @Expose
    private String href;
}
