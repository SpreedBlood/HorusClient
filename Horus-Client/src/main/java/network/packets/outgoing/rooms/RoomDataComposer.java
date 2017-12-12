package network.packets.outgoing.rooms;

import network.packets.types.ServerPacket;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class RoomDataComposer extends ServerPacket {
    public RoomDataComposer(int roomId, String heightMap) {
        super(300);
        writeInt(roomId);
        writeString(heightMap);
    }
}
