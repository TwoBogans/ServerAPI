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

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;

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

        final long age = Main.worldStats.time;

        stats.setAge(age);
        stats.setSize(Main.worldStats.size);
        stats.setPlayers(Main.worldStats.offlinePlayers);

        Main.calendar.setTimeInMillis(System.currentTimeMillis() - age);

        int year = Main.calendar.get(Calendar.YEAR) - 1970;
        int month = Main.calendar.get(Calendar.MONTH);
        int day = Main.calendar.get(Calendar.DAY_OF_MONTH) - 1;

        if (year < 0) {
            year = 0;
            month = 0;
            day = 0;
        }

        stats.setYears(year);
        stats.setMonths(month);
        stats.setDays(day);

        ctx.json(stats);
    }
}
