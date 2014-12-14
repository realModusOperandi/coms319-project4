/**
 *
 */
package coms319.group10.project4.whiteboard;

import javax.websocket.Session;

import coms319.group10.project4.whiteboard.Player.DIRECTION;

/**
 * @author Andrew
 * 
 */
public class PlayerFactory {

    private enum PlayerData {
        START_1("#FF0000", 50, 50, DIRECTION.RIGHT),
        START_2("#0000FF", 50, Game.GAME_SIZE - 50, DIRECTION.UP),
        START_3("#00FF00", Game.GAME_SIZE - 50, 50, DIRECTION.DOWN),
        START_4("#FF00FF", Game.GAME_SIZE - 50, Game.GAME_SIZE - 50, DIRECTION.LEFT);

        public final String color;
        public final int x;
        public final int y;
        public final DIRECTION dir;

        PlayerData(String color, int x, int y, DIRECTION dir) {
            this.color = color;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private static final PlayerData[] startingData = new PlayerData[] { PlayerData.START_1, PlayerData.START_2, PlayerData.START_3, PlayerData.START_4 };

    public static Player initNextPlayer(Game g, Session client) {
        PlayerData data = startingData[g.players.size()];
        Player p = new Player(g, client, data.color, data.x, data.y);
        p.direction = data.dir;
        p.playerName = "Player " + (g.players.size() + 1);
        return p;
    }
}
