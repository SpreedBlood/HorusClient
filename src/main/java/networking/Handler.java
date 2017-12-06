package networking;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import networking.packets.PacketManager;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
class Handler extends ChannelInboundHandlerAdapter {

    private PacketManager packetManager;

    Handler(PacketManager packetManager) {
        this.packetManager = packetManager;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //TODO: Packets
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
