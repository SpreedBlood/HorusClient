package horus.client.network.packets.incoming.rooms;

import horus.client.Horus;
import horus.client.game.room.Room;
import horus.client.game.room.map.RoomCamera;
import horus.client.game.room.map.RoomModel;
import network.client.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

public class RoomEntryEvent implements IPacket {

    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int roomId = clientPacket.readInt();
        String heightMap = clientPacket.readString();
        Horus.getInstance().setRoom(new Room(roomId, new RoomModel(heightMap, new RoomCamera())));
    }

}
