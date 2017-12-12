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
            int x = e.getX() - cameraX;
            int y = e.getY() - cameraY;
            int isoX = (y + x / 2) / 32;
            int isoY = (y - x / 2) / 32;
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
