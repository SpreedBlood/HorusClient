package network.packets.outgoing.handshake;

import network.packets.types.ServerPacket;

/**
 * Created by SpreedBlood on 2017-12-12.
 */
public class SetupClientComposer extends ServerPacket {
    public SetupClientComposer() {
        super(1);
    }
}
