package listeners;

import main.Game;
import room.map.RoomCamera;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class MouseHandler implements MouseListener {

    private final Game game;

    public MouseHandler(final Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        RoomCamera camera = this.game.getRoom().getModel().getCamera();

        if (camera != null) {
            int cameraX = camera.getX();
            int cameraY = camera.getY();
            int carX = Math.round((e.getX() - cameraX - 64) / 64 + (e.getY() - cameraY) / 32);
            int carY = Math.round((e.getY() - cameraY) / 32 - (e.getX() - cameraY) / 64);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
