package network;

import network.packets.types.ServerPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class Encoder extends MessageToMessageEncoder<ServerPacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ServerPacket serverPacket, List<Object> list) throws Exception {

        list.add(serverPacket.getBuf());

    }
}
