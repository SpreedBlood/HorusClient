package network.client;

import io.netty.channel.Channel;
import network.packets.types.ServerPacket;

public class Client {

    private final Channel channel;
    public Client(final Channel channel) {
        this.channel = channel;
    }

    public void writeAndFlush(ServerPacket packet) {
        this.channel.writeAndFlush(packet.getBuf());
    }

}
