package room;

import main.IRenderable;
import room.entity.UserEntity;
import room.map.RoomModel;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class Room implements IRenderable {

    private RoomModel model;
    private List<UserEntity> users;

    public Room(RoomModel model) {
        this.model = model;
        this.users = new LinkedList<>();
        this.users.add(new UserEntity(model.getDoorX(), model.getDoorY()));
    }

    @Override
    public void render(Graphics graphics) {
        this.model.render(graphics);
        for (UserEntity userEntity : this.users) {
            userEntity.render(graphics, this.model.getCamera());
        }
    }

    public RoomModel getModel() {
        return this.model;
    }

}
