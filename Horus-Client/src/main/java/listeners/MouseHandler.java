package listeners;

import main.Game;
import network.client.Client;
import network.packets.outgoing.rooms.entity.MoveEntityComposer;
import room.map.RoomCamera;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private final Game game;

    public MouseHandler(final Game game) {
        this.game = game;
    }

    /**
     * To locate where the mouse is at when clicking
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        RoomCamera camera = this.game.getRoom().getModel().getCamera();
        Client client = this.game.getClient();

        if (camera != null && client != null) {
            int cameraX = camera.getX();
            int cameraY = camera.getY();
            int x = e.getX() - cameraX;
            int y = e.getY() - cameraY;
            int isoX = (y + x / 2) / 32;
            int isoY = (y - x / 2) / 32;
            client.writeAndFlush(new MoveEntityComposer(isoX, isoY));
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
