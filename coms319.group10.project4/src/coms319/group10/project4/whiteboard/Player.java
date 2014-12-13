/**
 *
 */
package coms319.group10.project4.whiteboard;

import javax.websocket.Session;

/**
 * @author Andrew
 */
public class Player {

    public static enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static final int PLAYER_SIZE = 5;
    public Session client;
    public final String color;
    DIRECTION direction = DIRECTION.RIGHT;
    int x;
    int y;
    int playerNo;
    String playerStatus = "Connected";

    public Player(Session client, String color) {
        this.color = color;
        this.client = client;
    }

    public Player(Session client, String color, int xstart, int ystart) {
        this.color = color;
        this.client = client;
        x = xstart;
        y = ystart;
    }

    public void movePlayer() {
        switch (direction) {
            case UP:
                if (y - PLAYER_SIZE >= 0)
                    y -= PLAYER_SIZE;
                break;
            case DOWN:
                if (y + PLAYER_SIZE < Game.GAME_SIZE)
                    y += PLAYER_SIZE;
                break;
            case RIGHT:
                if (x + PLAYER_SIZE < Game.GAME_SIZE)
                    x += PLAYER_SIZE;
                break;
            case LEFT:
                if (x - PLAYER_SIZE >= 0)
                    x -= PLAYER_SIZE;
                break;
        }
    }

    public String toJson() {
        // {"shape":"square","color":"#FF0000","coords":{"x":251,"y":89}}
        StringBuffer sb = new StringBuffer("{\"shape\":\"square\",\"color\":\"");
        sb.append(this.color);
        sb.append("\",\"coords\":{\"x\":");
        sb.append(this.x);
        sb.append(",\"y\":");
        sb.append(this.y);
        sb.append("}}");
        return sb.toString();
    }

    public void setDirection(String dir) {
        direction = DIRECTION.valueOf(dir);
    }
}
