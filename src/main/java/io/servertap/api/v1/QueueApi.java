package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Main;
import io.servertap.api.v1.models.Queue;
import me.clip.placeholderapi.PlaceholderAPI;

public class QueueApi {

    @OpenApi(
            path = "/v1/queue",
            summary = "Get queue stats",
            tags = {"Queue"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Queue.class))
            }
    )
    public static void queueGet(Context ctx) {
        Queue queue = new Queue();

        int regular = 0;
        int priority = 0;
        int veteran = 0;

        if(Main.placeholderAPI != null && Main.pistonQueuePlaceholder != null) {

            regular = Integer.parseInt(PlaceholderAPI.setPlaceholders(null, "%pistonqueue_regular%"));
            priority = Integer.parseInt(PlaceholderAPI.setPlaceholders(null, "%pistonqueue_priority%"));
            veteran = Integer.parseInt(PlaceholderAPI.setPlaceholders(null, "%pistonqueue_veteran%"));

        }

        queue.setRegular(regular);
        queue.setPriority(priority);
        queue.setVeteran(veteran);

        ctx.json(queue);
    }

}
