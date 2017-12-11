package network;

import network.packets.types.ClientPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class Decoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buffer, List<Object> list) throws Exception {
        buffer.markReaderIndex();

        if (buffer.readableBytes() < 6) return;

        ClientPacket packet = new ClientPacket(buffer);
        list.add(packet);
    }

}
