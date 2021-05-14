package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Main;
import io.servertap.Util;
import io.servertap.api.v1.models.Performance;
import io.servertap.api.v1.models.Server;
import org.bukkit.Bukkit;

import java.lang.management.ManagementFactory;

public class ServerApi {

    @OpenApi(
            path = "/v1/server",
            summary = "Get information about the server",
            tags = {"Server"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Server.class))
            }
    )
    public static void serverGet(Context ctx) {
        Server server = new Server();
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();

        server.setName(Bukkit.getServer().getName());
        server.setMotd(Main.randomMOTD.getRandomMotd());
        server.setVersion(Bukkit.getServer().getBukkitVersion());
        server.setUptime(Util.getReadableTime(uptime));
        server.setOnline(Bukkit.getOnlinePlayers().size());

//        Performance performance = new Performance();

        server.getPerformance().setTps(Util.getTPSFormatted());
        server.getPerformance().setUptimeLong(uptime);
        server.getPerformance().setCpus(Runtime.getRuntime().availableProcessors());
        server.getPerformance().setMaxMemory(Runtime.getRuntime().maxMemory());
        server.getPerformance().setTotalMemory(Runtime.getRuntime().totalMemory());
        server.getPerformance().setFreeMemory(Runtime.getRuntime().freeMemory());

//        server.setPerformance(performance);

        ctx.json(server);
    }

}
