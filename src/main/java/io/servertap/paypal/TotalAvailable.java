package io.servertap.paypal;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public class TotalAvailable {
    @Getter
    @Setter
    @Expose
    private String currencyCode;
    @Getter
    @Setter
    @Expose
    private String value;
}
