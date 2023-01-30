package io.servertap.api.v1;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;
import io.servertap.Constants;
import io.servertap.Util;
import io.servertap.api.v1.models.OfflinePlayer;
import org.bukkit.Bukkit;

public class OfflinePlayerApi {

    @OpenApi(
            path = "/v1/seen/:uuid",
            method = HttpMethod.GET,
            summary = "Gets a specific players last seen date by their UUID",
            tags = {"Player"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            pathParams = {
                    @OpenApiParam(name = "uuid", description = "Player UUID")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = OfflinePlayer.class))
            }
    )
    public static void seenGet(Context ctx) {
        if (ctx.pathParam("uuid").isEmpty()) {
            throw new BadRequestResponse(Constants.PLAYER_UUID_MISSING);
        }

        var playerUUID = Util.safeUUID(ctx.pathParam("uuid"));

        if (playerUUID == null) {
            throw new BadRequestResponse(Constants.INVALID_UUID);
        }

        var bukkitPlayer = Bukkit.getOfflinePlayer(playerUUID);
        var offlinePlayer = new OfflinePlayer();

        offlinePlayer.setUuid(bukkitPlayer.getUniqueId());
        offlinePlayer.setName(bukkitPlayer.getName());
        offlinePlayer.setLastSeen(Util.getReadableTime(bukkitPlayer.getLastPlayed()) + " ago");
        offlinePlayer.setFirstPlayed(bukkitPlayer.getFirstPlayed());
        offlinePlayer.setLastPlayed(bukkitPlayer.getLastPlayed());

        ctx.json(offlinePlayer);
    }
}
