package com.horus.network.packets.outgoing.rooms;

import com.horus.network.packets.types.ServerPacket;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class RoomDataComposer extends ServerPacket {
    public RoomDataComposer(int roomId, String heightMap) {
        super((short)300);
        writeInt(roomId);
        writeString(heightMap);
    }
}
