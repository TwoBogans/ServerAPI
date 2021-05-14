package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Util;
import io.servertap.api.v1.models.Player;
import io.servertap.api.v1.models.stats.Mined;
import io.servertap.api.v1.models.stats.Placed;
import io.servertap.api.v1.models.stats.Statistics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;

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
            p.setTicksAlive(player.getStatistic(Statistic.TIME_SINCE_DEATH));
            p.setJoinDateMillis(player.getFirstPlayed());
            p.setPlayTimeTicks(player.getStatistic(Statistic.PLAY_ONE_TICK));
            p.setLastDeath(Util.getFormattedTicks(player.getStatistic(Statistic.TIME_SINCE_DEATH)));

            Statistics stats = new Statistics();

            stats.setDistanceFlown(player.getStatistic(Statistic.FLY_ONE_CM));
//TODO:       WALK_ONE_CM,
//            SWIM_ONE_CM,
//            FALL_ONE_CM,
//            SNEAK_TIME,
//            CLIMB_ONE_CM,
//            DIVE_ONE_CM,
//            MINECART_ONE_CM,
//            BOAT_ONE_CM,
//            PIG_ONE_CM,
//            HORSE_ONE_CM,
//            SPRINT_ONE_CM,
//            CROUCH_ONE_CM,
//            AVIATE_ONE_CM,
            stats.setPlayerKills(player.getStatistic(Statistic.PLAYER_KILLS));
            stats.setMobKills(player.getStatistic(Statistic.MOB_KILLS));
            stats.setDeaths(player.getStatistic(Statistic.DEATHS));

            Mined mined = new Mined();

            mined.setObsidian(player.getStatistic(Statistic.MINE_BLOCK, Material.OBSIDIAN));
            mined.setEnderChest(player.getStatistic(Statistic.MINE_BLOCK, Material.ENDER_CHEST));
            stats.setMined(mined);

            Placed placed = new Placed();

            placed.setObsidian(player.getStatistic(Statistic.USE_ITEM, Material.OBSIDIAN));
            placed.setEnderChests(player.getStatistic(Statistic.USE_ITEM, Material.ENDER_CHEST));
            stats.setPlaced(placed);

            p.setStatistics(stats);
            players.add(p);
        }));

        ctx.json(players);
    }
}
