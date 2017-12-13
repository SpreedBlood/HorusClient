package horus.client.listeners;

import horus.client.HorusClient;
import horus.client.game.room.map.RoomCamera;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {

    private int lastX;
    private int lastY;
    private final HorusClient game;

    public MouseMotionHandler(final HorusClient game) {
        this.game = game;
    }

    /**
     * This is used to locate the mouse current position and what the mouse is at.
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        if (this.game.getRoom() == null) {
            return;
        }

        try {
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
