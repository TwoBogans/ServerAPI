package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Constants;
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

        if(Main.placeholderAPI == null) {
            throw new NotFoundResponse(Constants.PAPI_NOT_FOUND);
        }

        if(Main.pistonQueuePlaceholder == null) {
            throw new NotFoundResponse(Constants.QUEUE_PAPI_NOT_FOUND);
        }

        queue.setRegular(Integer.parseInt(PlaceholderAPI.setPlaceholders(null, "%pistonqueue_regular%")));
        queue.setPriority(Integer.parseInt(PlaceholderAPI.setPlaceholders(null, "%pistonqueue_priority%")));
        queue.setVeteran(Integer.parseInt(PlaceholderAPI.setPlaceholders(null, "%pistonqueue_veteran%")));

        ctx.json(queue);
    }

}
