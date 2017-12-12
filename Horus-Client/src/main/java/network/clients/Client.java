package network.clients;

import io.netty.channel.Channel;
import network.packets.types.ServerPacket;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class Client {

    private final Channel channel;

    public Client(final Channel channel) {
        this.channel = channel;
    }

    public void writeAndFlush(ServerPacket packet) {
        this.channel.writeAndFlush(packet.getBuf());
    }

}
