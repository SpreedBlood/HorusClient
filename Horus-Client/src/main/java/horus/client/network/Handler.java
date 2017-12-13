package network;

import io.netty.channel.ChannelInboundHandlerAdapter;
import horus.client.Horus;
import network.client.Client;
import network.packets.PacketExecutor;
import network.packets.PacketManager;
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
        Horus.getInstance().getClient().writeAndFlush(new SetupClientComposer());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {

        ClientPacket clientPacket = new ClientPacket((ByteBuf)msg);
        short header = clientPacket.getHeader();
        IPacket packet = this.packetManager.getPacket(header);
        Client client = Horus.getInstance().getClient();

        if (packet != null && client != null) {
            channelHandlerContext.executor().submit(new PacketExecutor(client, clientPacket, packet));
        } else {
            System.out.println("Error while processing packet: " + header);
        }

    }
}
