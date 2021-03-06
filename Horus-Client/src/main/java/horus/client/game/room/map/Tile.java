package horus.client.game.room.map;

import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {


    private int x;
    private int y;
    private double z;
    private BufferedImage tileImage;
    private BufferedImage tileOutline;
    private BufferedImage doorLeft;
    private BufferedImage doorRight;
    private BufferedImage wallLeft;
    private BufferedImage wallRight;
    private boolean hovering;
    private boolean door;
    private WallType wallType;

    public Tile(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileImage = SpriteStorage.getInstance().getSprite("tile.png");
        this.tileOutline = SpriteStorage.getInstance().getSprite("tileoutline.png");
        this.doorLeft = SpriteStorage.getInstance().getSprite("door_left.png");
        this.doorRight = SpriteStorage.getInstance().getSprite("door_right.png");
        this.wallLeft = SpriteStorage.getInstance().getSprite("wall_left.png");
        this.wallRight = SpriteStorage.getInstance().getSprite("wall_right.png");
        this.wallType = WallType.NONE;
        this.hovering = false;
    }

    void renderFloor(Graphics graphics, RoomCamera camera) {
        //Converts 2d points to isometric
        int isoX = camera.getX() - (this.y * 32) + (this.x * 32) - 32;
        int isoY = camera.getY() + (this.y * 16) + (this.x * 16);

        graphics.drawImage(this.tileImage, isoX, isoY, null);

        if (this.hovering) {
            graphics.drawImage(this.tileOutline, isoX, isoY - 4, null);
        }
    }

    void renderWall(Graphics graphics, RoomCamera camera) {
        //Converts 2d points to isometric
        int isoX = camera.getX() - (this.y * 32) + (this.x * 32) - 32;
        int isoY = camera.getY() + (this.y * 16) + (this.x * 16);

        if (this.wallType != WallType.NONE) {
            if (this.wallType == WallType.RIGHT) {
                if (this.door) {
                    graphics.drawImage(this.doorRight, isoX + 33, isoY - 124, null);
                } else {
                    graphics.drawImage(this.wallRight, isoX + 33, isoY - 124, null); // TODO: Align door for the right side
                }
            }

            if (this.wallType == WallType.LEFT) {
                if (this.door) {
                    graphics.drawImage(this.doorLeft, isoX + 32, isoY - 107, null);
                } else {
                    graphics.drawImage(this.wallLeft, isoX - 9, isoY - 125, null);
                }
            }

            if (this.wallType == WallType.LEFT_AND_RIGHT) {
                if (this.door) {
                    graphics.drawImage(this.doorLeft, isoX + 32, isoY - 107, null);
                    graphics.drawImage(this.doorRight, isoX + 33, isoY - 124, null);
                } else {
                    graphics.drawImage(this.wallLeft, isoX - 9, isoY - 125, null);
                    graphics.drawImage(this.wallRight, isoX + 33, isoY - 124, null);
                }
            }
        }
    }

    void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    /**
     * Gets the tile wall type
     *
     * @return the type
     */
    public WallType getWallType() {
        return wallType;
    }

    /**
     * Sets the wall type that the tile has.
     *
     * @param wallType if the tile has a wall
     */
    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    /**
     * Gets whether this tile is a door or not
     * @return true, if successful
     */
    public boolean isDoor() {
        return door;
    }

    /**
     * Sets whether this tile is a door or not
     * @param door the door setting
     */
    public void setDoor(boolean door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + this.y;
    }
}
