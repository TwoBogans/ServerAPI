package io.servertap.api.v1;

import com.github.sarxos.xchange.ExchangeCache;
import com.github.sarxos.xchange.ExchangeException;
import com.github.sarxos.xchange.ExchangeRate;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Main;
import io.servertap.api.v1.models.Balance;
import io.servertap.api.v1.models.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BalanceApi {

    @OpenApi(
            path = "/v1/balance",
            summary = "Gets current server balance.",
            tags = {"Player"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Player.class, isArray = true))
            }
    )
    public static void balanceGet(Context ctx) {
        Balance balance = new Balance();

        balance.setWallet(getWalletBalance());
        balance.setCosts(Main.bukkitConfig.getInt("balance.costs", 255));
        balance.setCurrency(Main.bukkitConfig.getString("balance.currency", "USD"));

        ctx.json(balance);
    }

    private static int getWalletBalance() {
        try {
            Stripe.apiKey = Main.bukkitConfig.getString("balance.stripe-key");

            List<com.stripe.model.Balance.Money> available = com.stripe.model.Balance.retrieve().getAvailable();

            AtomicInteger totalBalance = new AtomicInteger(0);

            available.forEach(money -> totalBalance.getAndAdd(convertCurrency(money)));

            return totalBalance.get();
        } catch (StripeException e) {
            return 0;
        }
    }

    private static int convertCurrency(String from, String to, float amount)  {
        Main.log.info(String.format("Converting $%s from %s to %s", amount, from, to));
        try {
            ExchangeCache.setParameter("openexchangerates.org.apikey", Main.bukkitConfig.getString("balance.exchange-key"));
            ExchangeCache cache = new ExchangeCache(to);
            ExchangeRate rate = cache.getRate(from);

            BigDecimal newAmount = new BigDecimal(amount);
            BigDecimal converted = rate.convert(newAmount);

            Main.log.info(String.format("Converted $%s from %s to %s is $%s", amount, from, to, converted.intValue()));
            return converted.intValue();
        } catch (ExchangeException e) {
            return Math.round(amount);
        }
    }

    private static int convertCurrency(com.stripe.model.Balance.Money stripeMoney) {
        return convertCurrency(
                stripeMoney.getCurrency().toUpperCase(),
                Main.bukkitConfig.getString("balance.currency", "USD"),
                Math.abs(stripeMoney.getAmount()) / 100f
        );
    }
}
