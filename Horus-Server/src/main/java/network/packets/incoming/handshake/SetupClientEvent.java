package network.packets.incoming.handshake;

import network.client.Client;
import network.packets.outgoing.rooms.RoomDataComposer;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

public class SetupClientEvent implements IPacket {
    @Override
    public void run(Client client, ClientPacket clientPacket) {
        client.writeAndFlush(new RoomDataComposer(1, "xxxxxxx{13}x00000x{13}x00000x{13}000000x{13}x00000x{13}x00000x{13}x00000x{13}x00000x{13}xxxxxxx"));
    }
}
