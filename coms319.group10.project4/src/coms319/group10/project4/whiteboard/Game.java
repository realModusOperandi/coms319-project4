/**
 *
 */
package coms319.group10.project4.whiteboard;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Andrew
 */
public class Game extends Thread
{
    private static Game instance = new Game();
    public static final int GAME_SIZE = 800;
    public static final int GAME_SPEED = 75;
    Set<Player> players = new HashSet<>();
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
        players.remove(p);
        System.out.println("Player disconnected.");
        broadcastPlayerList(this, players);
    }

    @Override
    public void run() {
        gameRunning.set(true);
        System.out.println("Game started");

        while (gameRunning.get()) {
            while (!paused.get()) {
                delay(GAME_SPEED);
                for (Player p : players) {
                    p.movePlayer();
                    broadcastMove(this, p);
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
        for (Player player : g.players) {
            try {
                player.client.getBasicRemote().sendText(json);
            } catch (IOException e) {
                // ignore
            }
        }
    }

    private void broadcastPlayerList(Game g, Set<Player> players) {
        String playerList = "{";
        for (Player p : players)
            playerList += "\"player" + p.playerNo + "\":" + "\"" + p.playerStatus + "\",";
        playerList += "\"playerlist\":\"true\"}";

        for (Player player : g.players) {
            try {
                player.client.getBasicRemote().sendText(playerList);
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public void startGame() {
        paused.set(false);
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
