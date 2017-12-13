package network.packets.outgoing.handshake;

import network.packets.types.ServerPacket;

public class SetupClientComposer extends ServerPacket {
    public SetupClientComposer() {
        super(1);
    }
}
