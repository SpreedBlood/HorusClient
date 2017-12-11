package network.clients;

import network.packets.types.ServerPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class Client {

    private final ChannelHandlerContext channel;

    public Client(final ChannelHandlerContext channel) {
        this.channel = channel;
    }

    public void writeAndFlush(ServerPacket packet) {
        this.channel.writeAndFlush(packet);
    }

}
