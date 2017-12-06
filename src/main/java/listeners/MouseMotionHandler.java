package listeners;

import room.RoomCamera;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class MouseMotionHandler implements MouseMotionListener {

    private RoomCamera camera;
    private int lastX;
    private int lastY;

    public MouseMotionHandler(RoomCamera camera) {
        this.camera = camera;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int currentX = e.getX();
        int currentY = e.getY();

        int distanceX = lastX - currentX;
        int distanceY = lastY - currentY;
        if (this.camera != null)
            this.camera.move(distanceX, distanceY);
        lastX = currentX;
        lastY = currentY;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
