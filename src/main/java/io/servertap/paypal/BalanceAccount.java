package io.servertap.paypal;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public class BalanceAccount {
    @Getter
    @Setter
    @Expose
    private TotalAvailable available;
    @Getter
    @Setter
    @Expose
    private TotalAvailable reserved;
    @Getter
    @Setter
    @Expose
    private Object[] links;
}
