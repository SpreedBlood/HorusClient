package main;

import room.map.RoomCamera;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class Mouse {

    private int x;
    private int y;

    public Mouse(int x, int y) {
        this.x      = x;
        this.y      = y;
    }

    public void move(int x, int y) {
        this.x  = x;
        this.y  = y;
    }


    public void renderMouseTile(Graphics graphics, BufferedImage img, int relativeX, int relativeY, RoomCamera camera) {
        graphics.drawImage(img, camera.getX() + (relativeX - relativeY) * 32, camera.getY() + (relativeX + relativeY) * 16, null);
    }
}
