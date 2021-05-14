package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.Main;
import io.servertap.api.v1.models.Motd;

import java.util.HashMap;

public class MotdsApi {

    @OpenApi(
            path = "/v1/motd/random",
            summary = "Get random motd",
            tags = {"Stats"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Motd.class))
            }
    )
    public static void getRandomMotd(Context ctx) {
        Motd motd = new Motd();

        motd.setMotd(Main.randomMOTD.getRandomMotd());

        ctx.json(motd);
    }

    @OpenApi(
            path = "/v1/motd/all",
            summary = "Get all motds",
            tags = {"Stats"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Motd.class))
            }
    )
    public static void getAllMotd(Context ctx) {
        HashMap<Integer, String> motds = new HashMap<>();

        for(String string : Main.randomMOTD.getMotdList()){
            motds.put(Main.randomMOTD.getMotdList().indexOf(string), string);
        }

        ctx.json(motds);
    }
}
