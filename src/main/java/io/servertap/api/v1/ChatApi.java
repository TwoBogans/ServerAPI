package io.servertap.api.v1;

import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import io.servertap.api.v1.models.ChatMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public class ChatApi implements Listener {

    private static ChatMessage latest = new ChatMessage();

    @OpenApi(
            path = "/v1/chat",
            summary = "Get latest chat message",
            tags = {"Chat"},
            headers = {
                    @OpenApiParam(name = "key")
            },
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = ChatMessage.class))
            }
    )
    public static void chatGet(Context ctx) {
        ctx.json(Collections.singletonMap(latest.toString().hashCode(), latest));
    }

    @EventHandler
    public void on(AsyncPlayerChatEvent e) {
        final var name = e.getPlayer().getName();
        final var message = e.getMessage();
        final var chat = new ChatMessage();

        chat.setName(name);
        chat.setMessage("<%s> %s".formatted(name, message));
        chat.setType("chat");

        ChatApi.latest = chat;
    }

    @EventHandler
    public void on(PlayerJoinEvent e) {
        final var name = e.getPlayer().getName();
        final var chat = new ChatMessage();

        chat.setName(name);
        chat.setMessage("%s joined".formatted(name));
        chat.setType("join");

        ChatApi.latest = chat;
    }

    @EventHandler
    public void on(PlayerQuitEvent e) {
        final var name = e.getPlayer().getName();
        final var chat = new ChatMessage();

        chat.setName(name);
        chat.setMessage("%s left".formatted(name));
        chat.setType("quit");

        ChatApi.latest = chat;
    }

    @EventHandler
    public void on(PlayerDeathEvent e) {
        final var chat = new ChatMessage();

        chat.setName(e.getEntity().getName());
        chat.setMessage(e.getDeathMessage());
        chat.setType("death");

        ChatApi.latest = chat;
    }
}
