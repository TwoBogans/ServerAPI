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
            p.setJoinDate(Util.getFormattedDate(p.getJoinDateMillis()));
            p.setPlayTime(Util.getFormattedTicks(p.getPlayTimeTicks()));

            p.getStatistics().setDistanceFlown(player.getStatistic(Statistic.FLY_ONE_CM));
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
            p.getStatistics().setPlayerKills(player.getStatistic(Statistic.PLAYER_KILLS));
            p.getStatistics().setMobKills(player.getStatistic(Statistic.MOB_KILLS));
            p.getStatistics().setDeaths(player.getStatistic(Statistic.DEATHS));

            p.getStatistics().getMined().setObsidian(player.getStatistic(Statistic.MINE_BLOCK, Material.OBSIDIAN));
            p.getStatistics().getMined().setEnderChest(player.getStatistic(Statistic.MINE_BLOCK, Material.ENDER_CHEST));

            p.getStatistics().getPlaced().setObsidian(player.getStatistic(Statistic.USE_ITEM, Material.OBSIDIAN));
            p.getStatistics().getPlaced().setEnderChests(player.getStatistic(Statistic.USE_ITEM, Material.ENDER_CHEST));

//            p.setStatistics(stats);
            players.add(p);
        }));

        ctx.json(players);
    }
}
