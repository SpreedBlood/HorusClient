package network.packets.outgoing.rooms;

import network.packets.types.ServerPacket;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class RoomEntryComposer extends ServerPacket {
    public RoomEntryComposer(int roomId) {
        super((short)300);
        writeInt(roomId);
    }
}
