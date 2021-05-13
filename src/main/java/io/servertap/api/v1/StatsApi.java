package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.PluginEntrypoint;
import io.servertap.api.v1.models.Stats;

public class StatsApi {

    @OpenApi(
            path = "/v1/stats",
            summary = "Get world stats",
            tags = {"Stats"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Stats.class))
            }
    )
    public static void statsGet(Context ctx) {
        Stats stats = new Stats();

        stats.setAge(PluginEntrypoint.worldStats.getApi().getAge());
        stats.setSize(PluginEntrypoint.worldStats.getApi().getSize());
        stats.setPlayers(PluginEntrypoint.worldStats.getApi().getPlayers());
        stats.setYears(PluginEntrypoint.worldStats.getApi().getYears());
        stats.setMonths(PluginEntrypoint.worldStats.getApi().getMonths());
        stats.setDays(PluginEntrypoint.worldStats.getApi().getDays());

        ctx.json(stats);
    }
}
