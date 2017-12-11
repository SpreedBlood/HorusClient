package room;

import main.IRenderable;
import room.map.RoomModel;

import java.awt.*;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class Room implements IRenderable {

    private RoomModel model;

    public Room(RoomModel model) {
        this.model = model;
    }

    @Override
    public void render(Graphics graphics) {
        this.model.render(graphics);
    }

    public RoomModel getModel() {
        return this.model;
    }

}
