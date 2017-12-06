package room;

import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class Tile {

    private int x;
    private int y;
    private double z;
    private BufferedImage img;

    Tile(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.img = SpriteStorage.getInstance().getSprite("tile.png");
    }

    public void tick() {
        this.x++;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void render(Graphics graphics, RoomCamera camera) {
        graphics.drawImage(this.img, camera.getX() + (this.y - this.x) * 32, (camera.getY() + (this.y + this.x) * 16) - (int)this.z * 10, null);
    }

}
