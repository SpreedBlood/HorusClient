package room.map;

import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private int x;
    private int y;
    private double z;
    private BufferedImage tileImage;
    private BufferedImage tileOutline;
    private boolean hovering;
    private WallType wallType;

    public Tile(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileImage = SpriteStorage.getInstance().getSprite("tile.png");
        this.tileOutline = SpriteStorage.getInstance().getSprite("tileoutline.png");
        this.wallType = WallType.NONE;
        this.hovering = false;
    }

    void render(Graphics graphics, RoomCamera camera) {
        //Converts 2d points to isometric
        int isoX = camera.getX() - (this.y * 32) + (this.x * 32) - 32;
        int isoY = camera.getY() + (this.y * 16) + (this.x * 16);
        graphics.drawImage(this.tileImage, isoX, isoY, null);

        if (this.hovering) {
            graphics.drawImage(this.tileOutline, isoX, isoY - 4, null);
        }
    }

    void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    /**
     * Gets if the tile is on the edge and has a wall
     *
     * @return true, if successful
     */
    public boolean hasWall() {
        return this.wallType != WallType.NONE;
    }

    /**
     * Sets the wall type that the tile has.
     *
     * @param wallType if the tile has a wall
     */
    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + this.y;
    }

}
