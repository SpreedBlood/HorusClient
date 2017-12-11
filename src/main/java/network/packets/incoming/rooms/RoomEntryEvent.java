package network.packets.incoming.rooms;

import main.Game;
import network.clients.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;
import room.Room;
import room.map.RoomCamera;
import room.map.RoomModel;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class RoomEntryEvent implements IPacket {

    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int roomId = clientPacket.readInt();
        String heightMap = clientPacket.readString();
        Game.getInstance().addRenderable(new Room(new RoomModel(heightMap, new RoomCamera())));
        System.out.println("haha");
    }

}
