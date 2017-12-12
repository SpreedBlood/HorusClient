package listeners;

import main.Game;
import room.Room;
import room.map.RoomCamera;
import sprite.SpriteStorage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class MouseMotionHandler implements MouseMotionListener {

    private int lastX;
    private int lastY;
    private final Game game;

    public MouseMotionHandler(final Game game) {
        this.game = game;
    }

    /**
     * This is used to locate the mouse current position and what the mouse is at.
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        RoomCamera camera = this.game.getRoom().getModel().getCamera();

        if (camera != null) {
            int cameraX = camera.getX();
            int cameraY = camera.getY();
            int x = e.getX() - cameraX;
            int y = e.getY() - cameraY;
            int isoX = (y + x / 2) / 32;
            int isoY = (y - x / 2) / 32;
            this.game.getRoom().getModel().setHovering(isoX, isoY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
