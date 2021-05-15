package io.servertap.api.v1;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.openapi.annotations.*;
import io.servertap.Constants;
import io.servertap.Util;
import io.servertap.api.v1.models.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

import java.util.ArrayList;
import java.util.UUID;

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
            players.add(setPlayer(player));
        }));

        ctx.json(players);
    }

    @OpenApi(
            path = "/v1/players/:uuid",
            method = HttpMethod.GET,
            summary = "Gets a specific online player by their UUID",
            tags = {"Player"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            pathParams = {
                    @OpenApiParam(name = "uuid", description = "UUID of the player")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Player.class))
            }
    )
    public static void playerGet(Context ctx) {
        if (ctx.pathParam("uuid").isEmpty()) {
            throw new BadRequestResponse(Constants.PLAYER_UUID_MISSING);
        }

        UUID playerUUID = Util.safeUUID(ctx.pathParam("uuid"));

        if (playerUUID == null) {
            throw new BadRequestResponse(Constants.INVALID_UUID);
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);

        if (offlinePlayer.getPlayer() == null) {
            throw new NotFoundResponse(Constants.PLAYER_NOT_FOUND);
        }

        ctx.json(setPlayer(offlinePlayer.getPlayer()));
    }

    private static Player setPlayer(org.bukkit.entity.Player player) {
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
        p.getStatistics().setPlayerKills(player.getStatistic(Statistic.PLAYER_KILLS));
        p.getStatistics().setMobKills(player.getStatistic(Statistic.MOB_KILLS));
        p.getStatistics().setDeaths(player.getStatistic(Statistic.DEATHS));

        p.getStatistics().getMined().setObsidian(player.getStatistic(Statistic.MINE_BLOCK, Material.OBSIDIAN));
        p.getStatistics().getMined().setEnderChest(player.getStatistic(Statistic.MINE_BLOCK, Material.ENDER_CHEST));

        p.getStatistics().getPlaced().setObsidian(player.getStatistic(Statistic.USE_ITEM, Material.OBSIDIAN));
        p.getStatistics().getPlaced().setEnderChests(player.getStatistic(Statistic.USE_ITEM, Material.ENDER_CHEST));
        return p;
    }
}
