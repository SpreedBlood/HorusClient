package network.packets;

import network.client.Client;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

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
