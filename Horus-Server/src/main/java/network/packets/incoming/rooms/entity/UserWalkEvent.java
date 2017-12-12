package network.packets.incoming.rooms.entity;

import network.client.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

/**
 * Created by SpreedBlood on 2017-12-12.
 */
public class UserWalkEvent implements IPacket{

    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int x = clientPacket.readInt();
        int y = clientPacket.readInt();

    }
}
