package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.api.v1.models.Player;
import io.servertap.api.v1.models.Statistics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class PlayerApi {

    @OpenApi(
            path = "/v1/players",
            summary = "Gets all currently online players",
            tags = {"Player"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Player.class, isArray = true))
            }
    )
    public static void playersGet(Context ctx) {
        ArrayList<Player> players = new ArrayList<>();

        Bukkit.getOnlinePlayers().forEach((player -> {
            Player p = new Player();
            p.setUuid(player.getUniqueId().toString());
            p.setDisplayName(player.getDisplayName());
            p.setTicksAlive(player.getTicksLived());
            p.setJoinDateMillis(player.getFirstPlayed());
            p.setPlayTimeTicks(player.getStatistic(Statistic.PLAY_ONE_TICK));

            Statistics stats = new Statistics();
            stats.setObsidianPlaced(player.getStatistic(Statistic.USE_ITEM, Material.OBSIDIAN));
            stats.setEnderChestsMined(player.getStatistic(Statistic.MINE_BLOCK, Material.ENDER_CHEST));
            stats.setDistanceFlown(player.getStatistic(Statistic.FLY_ONE_CM));

            p.setStatistics(stats);
            players.add(p);
        }));

        ctx.json(players);
    }
}
