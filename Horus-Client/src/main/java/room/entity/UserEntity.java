package room.entity;

import room.map.RoomCamera;
import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UserEntity {

    private int x;
    private int y;
    private boolean updateNeeded;
    private BufferedImage img;

    public UserEntity(int x, int y) {
        this.x = x;
        this.y = y;
        this.updateNeeded = false;
        img = SpriteStorage.getInstance().getSprite("avatar.png");
    }

    /**
     * Check if update is needed to prevent rendering all users.
     * @return wether or not the user needs to be updated.
     */
    public boolean isUpdateNeeded() {
        return updateNeeded;
    }

    /**
     * Set if theres a need to update the avatar.
     * @param updateNeeded wether or not it's needed to render the avatar.
     */
    public void setUpdateNeeded(boolean updateNeeded) {
        this.updateNeeded = updateNeeded;
    }

    /**
     * The rendering of the avatar.
     * TODO: Avatar parsing.
     * @param graphics the graphics that it's going to draw to
     * @param camera the camera object for the offsets.
     */
    public void render(Graphics graphics, RoomCamera camera) {
        int isoX = camera.getX() - (this.y * 32) + (this.x * 32) - 32;
        int isoY = camera.getY() + (this.y * 16) + (this.x * 16);

        graphics.drawImage(this.img, isoX - 5, isoY - 87, null);
    }

}
