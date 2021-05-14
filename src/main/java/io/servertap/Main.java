package io.servertap;

import com.github.anarchyplugins.randommotd.RandomMOTD;
import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.servertap.api.v1.PlayerApi;
import io.servertap.api.v1.QueueApi;
import io.servertap.api.v1.ServerApi;
import io.servertap.api.v1.StatsApi;
import io.swagger.v3.oas.models.info.Info;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.moomoo.worldstats.WorldStats;
import net.pistonmaster.pistonqueueplaceholder.PistonQueuePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Main extends JavaPlugin {

    private static final Logger log = Bukkit.getLogger();
    private static Javalin app;

    public static WorldStats worldStats;
    public static RandomMOTD randomMOTD;
    public static PlaceholderAPIPlugin placeholderAPI;
    public static PistonQueuePlaceholder pistonQueuePlaceholder;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration bukkitConfig = getConfig();

        setupHooks();

        Bukkit.getScheduler().runTaskTimer(this, new Util(), 100, 1);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());

        if (app == null) {
            app = Javalin.create(config -> {
                config.defaultContentType = "application/json";
                config.showJavalinBanner = false;
                config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));
            });
        }

        app.start(bukkitConfig.getInt("port"));

        if (bukkitConfig.getBoolean("debug")) {
            app.before(ctx -> log.info(ctx.req.getPathInfo()));
        }

        app.routes(() -> {
            path("v1", () -> {
                get("players", PlayerApi::playersGet);
                get("server", ServerApi::serverGet);
                get("stats", StatsApi::statsGet);
                get("queue", QueueApi::queueGet);
            });
        });

        Thread.currentThread().setContextClassLoader(classLoader);

    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));

        if (app != null) {
            app.stop();
        }
    }

    private OpenApiOptions getOpenApiOptions() {
        Info applicationInfo = new Info().title(getDescription().getName()).version(getDescription().getVersion()).description(getDescription().getDescription());
        return new OpenApiOptions(applicationInfo).path("/json-docs").activateAnnotationScanningFor("io.servertap.api.v1").swagger(new SwaggerOptions("/api-docs"));
    }

    private void setupHooks() {
        if (getServer().getPluginManager().getPlugin("WorldStats") != null) {
            worldStats = (WorldStats) getServer().getPluginManager().getPlugin("WorldStats");
        }

        if (getServer().getPluginManager().getPlugin("RandomMOTD") != null) {
            randomMOTD = (RandomMOTD) getServer().getPluginManager().getPlugin("RandomMOTD");
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeholderAPI = (PlaceholderAPIPlugin) getServer().getPluginManager().getPlugin("PlaceholderAPI");
        }

        if (getServer().getPluginManager().getPlugin("PistonQueuePlaceholder") != null) {
            pistonQueuePlaceholder = (PistonQueuePlaceholder) getServer().getPluginManager().getPlugin("PistonQueuePlaceholder");
        }
    }
}
