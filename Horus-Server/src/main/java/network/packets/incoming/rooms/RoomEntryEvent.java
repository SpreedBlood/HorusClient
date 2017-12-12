package network.packets.incoming.rooms;

import network.client.Client;
import network.packets.outgoing.rooms.RoomDataComposer;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

public class RoomEntryEvent implements IPacket {

    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int roomId = clientPacket.readInt();
        String heightMap = "xxxxxxx{13}x00000x{13}x00000x{13}000000x{13}x00000x{13}x00000x{13}x00000x{13}x00000x{13}xxxxxxx";
        client.writeAndFlush(new RoomDataComposer(roomId, heightMap));
    }

}
