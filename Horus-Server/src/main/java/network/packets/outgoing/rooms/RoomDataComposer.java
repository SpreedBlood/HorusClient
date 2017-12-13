package network.packets.outgoing.rooms;

import network.packets.types.ServerPacket;

import java.util.List;

public class RoomDataComposer extends ServerPacket {
    public RoomDataComposer(int roomId, List<String> heightMap) {
        super((short)300);
        writeInt(roomId);
        writeInt(heightMap.size());

        for (String line : heightMap) {
            writeString(line);
        }
    }
}
