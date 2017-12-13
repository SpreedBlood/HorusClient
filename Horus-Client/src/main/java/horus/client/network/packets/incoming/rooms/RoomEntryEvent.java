package horus.client.network.packets.incoming.rooms;

import horus.client.HorusClient;
import horus.client.game.room.Room;
import horus.client.game.room.map.RoomCamera;
import horus.client.game.room.map.RoomModel;
import network.client.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

import java.util.ArrayList;
import java.util.List;

public class RoomEntryEvent implements IPacket {

    @Override
    public void run(Client client, ClientPacket message) {
        int roomId = message.readInt();
        int heightMapSize = message.readInt();

        List<String> heightMap = new ArrayList<>();

        for (int i = 0; i < heightMapSize; i++) {
            heightMap.add(message.readString());
        }

        HorusClient.getInstance().setRoom(new Room(roomId, new RoomModel(heightMap, new RoomCamera())));
    }

}
