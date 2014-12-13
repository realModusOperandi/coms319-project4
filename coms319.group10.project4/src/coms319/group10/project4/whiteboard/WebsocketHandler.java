package coms319.group10.project4.whiteboard;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket")
public class WebsocketHandler {

    private static final ConcurrentMap<Session, Player> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session client) {
        Game game = Game.getGame();
        Player p = PlayerFactory.initNextPlayer(game, client);
        clients.put(client, p);
        game.addPlayer(p);
    }

    @OnClose
    public void onClose(Session peer) {
        Game game = Game.getGame();
        game.removePlayer(clients.get(peer));
        clients.remove(peer);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        System.out.println("got message: " + message);
        JsonObject json = Json.createReader(new StringReader(message)).readObject();

        if (json.containsKey("message")) {
            if (json.getString("message").equals("GAME_START")) {
                Game.getGame().startGame();
            }
            if (json.getString("message").equals("GAME_PAUSE")) {
                Game.getGame().pause();
                System.out.println("Stopped the game");
            }
        }
        if (json.containsKey("direction")) {
            Player p = clients.get(session);
            p.setDirection(json.getString("direction"));
        }
    }
}
