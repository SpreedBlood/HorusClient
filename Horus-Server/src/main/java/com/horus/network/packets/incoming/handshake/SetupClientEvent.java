package com.horus.network.packets.incoming.handshake;

import com.horus.network.clients.Client;
import com.horus.network.packets.outgoing.rooms.RoomDataComposer;
import com.horus.network.packets.types.ClientPacket;
import com.horus.network.packets.types.IPacket;

/**
 * Created by SpreedBlood on 2017-12-12.
 */
public class SetupClientEvent implements IPacket {
    @Override
    public void run(Client client, ClientPacket clientPacket) {
        client.writeAndFlush(new RoomDataComposer(1, "xxxxxxxxxxxxxxxxxxxxxxxxx{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxxxxxxxxxxxxxxxxxx"));
    }
}
