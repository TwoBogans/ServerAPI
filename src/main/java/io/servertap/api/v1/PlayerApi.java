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
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

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
        var players = Bukkit.getOnlinePlayers()
                .stream()
                .map(player -> setPlayer(player, false))
                .collect(Collectors.toList());
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
    public static void playerUUIDGet(Context ctx) {
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

        ctx.json(setPlayer(offlinePlayer.getPlayer(), true));
    }

    // TODO USE CACHE
    private static Player setPlayer(org.bukkit.entity.Player player, boolean stats) {
        Player p = new Player();

        p.setUuid(player.getUniqueId().toString());
        p.setDisplayName(player.getDisplayName());

        p.setTicksAlive(player.getStatistic(Statistic.TIME_SINCE_DEATH));
        p.setJoinDateMillis(player.getFirstPlayed());
        p.setPlayTimeTicks(player.getStatistic(Statistic.PLAY_ONE_TICK));
        p.setLastDeath(Util.getFormattedTicks(player.getStatistic(Statistic.TIME_SINCE_DEATH)));
        p.setJoinDate(Util.getFormattedDate(p.getJoinDateMillis()));
        p.setPlayTime(Util.getFormattedTicks(p.getPlayTimeTicks()));

        var tempMap = new HashMap<String, Integer>();

        if (stats) {
            for (Statistic statistic : Statistic.values()) {
                if (!statistic.isSubstatistic()) {
                    tempMap.put(statistic.name(), player.getStatistic(statistic));
                }

                if (statistic.getType() == Statistic.Type.BLOCK || statistic.getType() == Statistic.Type.ITEM) {
                    for (Material material : Material.values()) {
                        var name = "%s_%s".formatted(statistic.name(), material.name());
                        int stat = -1;

                        try {
                            stat = player.getStatistic(statistic, material);
                        } catch (Exception e) {
                            System.out.printf("%s: %s", name, e.getMessage());
                        }

                        if (stat != -1) {
                            tempMap.put(name.toUpperCase(), stat);
                        }
                    }
                }

                if (statistic.getType() == Statistic.Type.ENTITY) {
                    for (EntityType entityType : EntityType.values()) {
                        var name = "%s_%s".formatted(statistic.name(), entityType.name());
                        int stat = -1;

                        try {
                            stat = player.getStatistic(statistic, entityType);
                        } catch (Exception e) {
                            System.out.printf("%s: %s", name, e.getMessage());
                        }

                        if (stat != -1) {
                            tempMap.put(name.toUpperCase(), stat);
                        }
                    }
                }
            }
        }

        p.setStatistics(tempMap);
        return p;
    }
}
