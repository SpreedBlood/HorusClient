package listeners;

import config.StaticSettings;
import main.Game;
import room.map.RoomCamera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by SpreedBlood on 2017-12-11.
 */
public class KeyHandler implements KeyListener {

    private final Game game;

    public KeyHandler(final Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        RoomCamera camera = this.game.getRoom().getModel().getCamera();
        if (camera != null) {
            int newX = 0;
            int newY = 0;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                newX -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                newX += 5;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                newY -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                newY += 5;
            }
            camera.move(newX, newY);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
