package horus.client.network;

import horus.client.network.packets.PacketExecutor;
import horus.client.network.packets.PacketManager;
import io.netty.channel.ChannelInboundHandlerAdapter;
import horus.client.HorusClient;
import network.client.Client;
import network.packets.outgoing.handshake.SetupClientComposer;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

class Handler extends ChannelInboundHandlerAdapter {

    private final PacketManager packetManager;

    Handler(final PacketManager packetManager) {
        this.packetManager = packetManager;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        HorusClient.getInstance().getClient().writeAndFlush(new SetupClientComposer());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if (!(msg instanceof ByteBuf)) {
            return;
        }

        ClientPacket message = new ClientPacket((ByteBuf) msg);

        if (!this.packetManager.hasPacket(message.getHeader())) {
            System.out.println("No handler registered for packet: " + message.getHeader());
            return;
        }

        IPacket packet = this.packetManager.getPacket(message.getHeader());
        Client client = HorusClient.getInstance().getClient();

        if (packet != null && client != null) {
            channelHandlerContext.executor().submit(new PacketExecutor(client, message, packet));
        } else {
            System.out.println("Error while processing packet: " + message.getHeader());
        }
    }
}
