package room.entity;

import room.map.RoomCamera;
import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SpreedBlood on 2017-12-11.
 */
public class UserEntity {

    private int x;
    private int y;
    private BufferedImage img;

    public UserEntity(int x, int y) {
        this.x = x;
        this.y = y;
        img = SpriteStorage.getInstance().getSprite("avatar.png");
    }

    public void render(Graphics graphics, RoomCamera camera) {
        int isoX = camera.getX() - (this.y * 32) + (this.x * 32) - 32;
        int isoY = camera.getY() + (this.y * 16) + (this.x * 16);

        graphics.drawImage(this.img, isoX - 5, isoY - 87, null);
    }

}
