package horus.client.game.room;

import horus.client.entity.UserEntity;
import horus.client.game.IRenderable;
import horus.client.game.room.map.RoomModel;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Room implements IRenderable {

    private int roomId;
    private RoomModel model;
    private Map<Integer, UserEntity> entities;

    public Room(int roomId, RoomModel model) {
        this.roomId = roomId;
        this.model = model;
        this.entities = new ConcurrentHashMap<>();
    }

    @Override
    public void render(Graphics graphics) {
        this.model.render(graphics);
        for (UserEntity userEntity : this.entities.values()) {
            if (userEntity.isUpdateNeeded()) {
                userEntity.render(graphics, this.model.getCamera());
            }
        }
    }

    /**
     * Get the rooms id
     * @return the room id.
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Gets the model of the current room.
     *
     * @return the model.
     */
    public RoomModel getModel() {
        return this.model;
    }

    /**
     * Get the entity from the room.
     *
     * @param id the entity id
     * @return the entity if it exists, else null.
     */
    public UserEntity getEntity(int id) {
        if (this.entities.containsKey(id)) {
            return this.entities.get(id);
        }
        return null;
    }

    /**
     * Add the entity to the room.
     * @param id the userId
     * @param doorX the door X coordinate
     * @param doorY the door Y coordinate
     */
    public void addEntity(int id, int doorX, int doorY) {
        if (!this.entities.containsKey(id)) {
            UserEntity newEntity = new UserEntity(doorX, doorY);
            newEntity.setUpdateNeeded(true);
            this.entities.put(id, newEntity);
        }
    }

}
