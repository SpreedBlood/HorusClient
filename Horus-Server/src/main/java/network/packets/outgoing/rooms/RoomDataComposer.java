package network.packets.outgoing.rooms;

import network.packets.types.ServerPacket;

public class RoomDataComposer extends ServerPacket {
    public RoomDataComposer(int roomId, String heightMap) {
        super((short)300);
        writeInt(roomId);
        writeString(heightMap);
    }
}
