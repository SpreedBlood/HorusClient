package room.map;

import config.StaticSettings;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class RoomCamera {

    private int x;
    private int y;

    public RoomCamera() {
        this.x = StaticSettings.WIDTH / 2;
        this.y = StaticSettings.HEIGHT / 2;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
