package com.horus.network.packets.incoming.rooms;

import com.horus.network.clients.Client;
import com.horus.network.packets.outgoing.rooms.RoomDataComposer;
import com.horus.network.packets.types.ClientPacket;
import com.horus.network.packets.types.IPacket;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class RoomEntryEvent implements IPacket {

    @Override
    public void run(Client client, ClientPacket clientPacket) {
        int roomId = clientPacket.readInt();
        String heightMap = "xxxxxxxxxxxxxxxxxxxxxxxxx{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxxxxxxxxxx00000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}x000000000000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxx0000000000000000{13}xxxxxxxxxxxxxxxxxxxxxxxxx";
        client.writeAndFlush(new RoomDataComposer(roomId, heightMap));
    }

}
