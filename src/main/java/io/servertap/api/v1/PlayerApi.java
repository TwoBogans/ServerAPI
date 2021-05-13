package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.api.v1.models.Player;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

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

            players.add(p);
        }));

        ctx.json(players);
    }

    @OpenApi(
            path = "/v1/players/all",
            summary = "Gets all players that have ever joined the server ",
            tags = {"Player"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = io.servertap.api.v1.models.OfflinePlayer.class, isArray = true))
            }
    )
    public static void offlinePlayersGet(Context ctx) {

        ArrayList<io.servertap.api.v1.models.OfflinePlayer> players = new ArrayList<>();

        OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();

        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            io.servertap.api.v1.models.OfflinePlayer p = new io.servertap.api.v1.models.OfflinePlayer();

            p.setUuid(offlinePlayer.getUniqueId().toString());
            p.setDisplayName(offlinePlayer.getName());

            players.add(p);
        }

        ctx.json(players);
    }
}
