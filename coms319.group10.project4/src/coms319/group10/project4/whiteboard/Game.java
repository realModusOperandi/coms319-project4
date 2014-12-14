/**
 *
 */
package coms319.group10.project4.whiteboard;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * @author Andrew
 */
public class Game extends Thread
{
    private static Game instance = new Game();
    public static final int GAME_SIZE = 600;
    public static final int GAME_SPEED = 50;
    public static boolean[][] board = new boolean[121][121];
    Set<Player> players = Collections.synchronizedSet(new HashSet<Player>());
    AtomicBoolean gameRunning = new AtomicBoolean(false);
    AtomicBoolean paused = new AtomicBoolean(false);

    private Game() {}

    public static Game getGame() {
        return instance;
    }

    public void addPlayer(Player p) {
        players.add(p);
        System.out.println("Player " + players.size() + " has joined.");
        broadcastPlayerList(this, players);
    }

    public void removePlayer(Player p) {
        p.playerStatus = "Disconnected";
        p.disconnect();
        System.out.println("Player " + p.playerNo + " disconnected.");
        broadcastPlayerList(this, players);
    }

    @Override
    public void run() {
        for (int i = 0; i < GAME_SIZE / Player.PLAYER_SIZE + 1; i++) {
            Arrays.fill(board[i], true);
        }
        gameRunning.set(true);
        System.out.println("Game started");

        while (gameRunning.get()) {
            while (!paused.get()) {
                delay(GAME_SPEED);
                for (Player p : players) {
                    if (p.isAlive) {
                        if (p.movePlayer()) {
                            broadcastMove(this, p);
                        } else {
                            broadcastPlayerList(this, players);
                        }
                    }
                }
            }
            delay(500); // don't thrash for pausing
        }
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
        }
    }

    private void broadcastMove(Game g, Player p) {
        String json = p.toJson();
        for (Player player : g.players)
            player.sendTextToClient(json);
    }

    private void broadcastPlayerList(Game g, Set<Player> players) {
        JsonArrayBuilder array = Json.createArrayBuilder();
        for (Player p : players) {
            array.add(Json.createObjectBuilder()
                            .add("name", "player " + p.playerNo)
                            .add("status", p.playerStatus)
                            .add("color", p.color));
        }
        JsonObject obj = Json.createObjectBuilder().add("playerlist", array).build();
        System.out.println("Playerlist: " + obj.toString());

        for (Player player : g.players)
            player.sendTextToClient(obj.toString());
    }

    public void startGame() {
        paused.set(false);
        for (Player p : players)
            if ("Connected".equals(p.playerStatus))
                p.playerStatus = "Alive";
        broadcastPlayerList(this, players);
        if (!gameRunning.get())
            this.start();
    }

    public void pause() {
        paused.set(true);
    }

    public void stopGame() {
        gameRunning.set(false);
    }
}
