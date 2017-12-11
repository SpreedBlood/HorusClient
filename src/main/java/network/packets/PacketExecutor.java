package network.packets;

import network.clients.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class PacketExecutor implements Runnable {

    private final IPacket packet;
    private final Client client;
    private final ClientPacket clientPacket;

    public PacketExecutor(final Client client, final ClientPacket clientPacket, final IPacket packet) {
        this.packet = packet;
        this.client = client;
        this.clientPacket = clientPacket;
    }

    @Override
    public void run() {
        this.packet.run(this.client, this.clientPacket);
    }

}
