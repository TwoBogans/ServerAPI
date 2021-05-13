package io.servertap;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.servertap.api.v1.PlayerApi;
import io.servertap.api.v1.ServerApi;
import io.servertap.api.v1.StatsApi;
import io.swagger.v3.oas.models.info.Info;
import me.moomoo.worldstats.StatsAPI;
import me.moomoo.worldstats.WorldStats;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class PluginEntrypoint extends JavaPlugin {

    private static final Logger log = Bukkit.getLogger();
    private static Javalin app;

    public static WorldStats worldStats;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration bukkitConfig = getConfig();

        if (getServer().getPluginManager().getPlugin("WorldStats") != null) {
            worldStats = (WorldStats) getServer().getPluginManager().getPlugin("WorldStats");
        }

        Bukkit.getScheduler().runTaskTimer(this, new Lag(), 100, 1);

        // Get the current class loader.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Temporarily set this thread's class loader to the plugin's class loader.
        // Replace JavalinTestPlugin.class with your own plugin's class.
        Thread.currentThread().setContextClassLoader(PluginEntrypoint.class.getClassLoader());

        // Instantiate the web server (which will now load using the plugin's class
        // loader).
        if (app == null) {
            app = Javalin.create(config -> {
                config.defaultContentType = "application/json";
                config.showJavalinBanner = false;

                // Create an accessManager to verify the path is a swagger call, or has the correct authentication
                config.accessManager((handler, ctx, permittedRoles) -> {
                    String path = ctx.req.getPathInfo();
                    String[] noAuthPaths = new String[]{"/swagger", "/swagger-docs"};
                    List<String> noAuthPathsList = Arrays.asList(noAuthPaths);
                    if (noAuthPathsList.contains(path) || !bukkitConfig.getBoolean("useKeyAuth") || bukkitConfig.getString("key").equals(ctx.header("key"))) {
                        handler.handle(ctx);
                    } else {
                        ctx.status(401).result("Unauthorized key, reference the key existing in config.yml");
                    }
                });

                config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));
            });

        }
        // Don't create a new instance if the plugin is reloaded
        app.start(bukkitConfig.getInt("port"));

        if (bukkitConfig.getBoolean("debug")) {
            app.before(ctx -> log.info(ctx.req.getPathInfo()));
        }
        app.routes(() -> {
            // Routes for v1 of the API
            path("v1", () -> {
                get("players", PlayerApi::playersGet);
                get("players/all", PlayerApi::offlinePlayersGet);
                get("server", ServerApi::serverGet);
                get("stats", StatsApi::statsGet);
            });
        });

        // Put the original class loader back where it was.
        Thread.currentThread().setContextClassLoader(classLoader);

    }


    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
        // Release port so that /reload will work
        if (app != null) {
            app.stop();
        }
    }

    private OpenApiOptions getOpenApiOptions() {
        Info applicationInfo = new Info()
                .title(this.getDescription().getName())
                .version(this.getDescription().getVersion())
                .description(this.getDescription().getDescription());
        return new OpenApiOptions(applicationInfo)
                .path("/swagger-docs")
                .activateAnnotationScanningFor("io.servertap.api.v1")
                .swagger(new SwaggerOptions("/swagger"));
    }

}
