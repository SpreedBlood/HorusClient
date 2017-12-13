package network.packets.outgoing.rooms.entity;

import network.packets.types.ServerPacket;

public class MoveEntityComposer extends ServerPacket {
    public MoveEntityComposer(int x, int y) {
        super(2);
        writeInt(x);
        writeInt(y);
    }
}
