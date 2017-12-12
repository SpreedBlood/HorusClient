package network.packets.incoming.rooms.entity;

import network.client.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

public class UpdateEntityEvent implements IPacket {
    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int x = clientPacket.readInt();
        int y = clientPacket.readInt();

    }
}
