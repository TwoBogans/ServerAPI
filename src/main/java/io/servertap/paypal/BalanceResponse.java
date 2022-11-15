package io.servertap.paypal;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public class BalanceResponse {
    @Getter
    @Setter
    @Expose
    private TotalAvailable totalAvailable;
    @Getter
    @Setter
    @Expose
    private TotalAvailable totalReserved;
    @Getter
    @Setter
    @Expose
    private BalanceAccount[] balanceAccounts;
    @Getter
    @Setter
    @Expose
    private Link[] links;
}

