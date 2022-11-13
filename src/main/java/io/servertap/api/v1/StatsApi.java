package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Constants;
import io.servertap.Main;
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

        if(Main.worldStats == null) {
            throw new NotFoundResponse(Constants.WORLD_STATS_NOT_FOUND);
        }

        stats.setAge(Main.worldStats.time);
        stats.setSize(Main.worldStats.size);
        stats.setPlayers(Main.worldStats.offlinePlayers);

        ctx.json(stats);
    }
}
