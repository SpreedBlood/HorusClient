package listeners;

import main.Game;
import room.Room;
import room.map.RoomCamera;
import sprite.SpriteStorage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class MouseMotionHandler implements MouseMotionListener {

    private int lastX;
    private int lastY;
    private BufferedImage tileOutline;
    private final Game game;

    public MouseMotionHandler(final Game game) {
        this.game = game;
        this.tileOutline = SpriteStorage.getInstance().getSprite("tile_outline.png");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*RoomCamera camera = game.getRoom().getModel().getCamera();
        int currentX = e.getX();
        int currentY = e.getY();

        int distanceX = lastX - currentX;
        int distanceY = lastY - currentY;
        if (camera != null) {
            camera.move(distanceX, distanceY);
        }
        lastX = currentX;
        lastY = currentY;*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        RoomCamera camera = this.game.getRoom().getModel().getCamera();

        if (camera != null) {
            int cameraX = camera.getX();
            int cameraY = camera.getY();
            int x = e.getX() - cameraX;
            int y = e.getY() - cameraY;
            int isoX = (y + x/2)/32;
            int isoY = (y - x/2)/32;
            this.game.getRoom().getModel().setHovering(isoX, isoY);
        }
    }
}
