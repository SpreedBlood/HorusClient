package network.packets.incoming.rooms;

import main.Game;
import network.client.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;
import room.Room;
import room.map.RoomCamera;
import room.map.RoomModel;

public class RoomEntryEvent implements IPacket {

    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int roomId = clientPacket.readInt();
        String heightMap = clientPacket.readString();
        Game.getInstance().setRoom(new Room(roomId, new RoomModel(heightMap, new RoomCamera())));
    }

}
