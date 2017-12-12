package network;

import network.client.Client;
import network.clients.ClientManager;
import network.packets.PacketExecutor;
import network.packets.PacketManager;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

class Handler extends ChannelInboundHandlerAdapter {

    private final PacketManager packetManager;
    private final ClientManager clientManager;

    Handler(final PacketManager packetManager, ClientManager clientManager) {
        this.packetManager = packetManager;
        this.clientManager = clientManager;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        this.clientManager.addClient(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        this.clientManager.removeClient(ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf)msg;
        ClientPacket clientPacket = new ClientPacket(buffer);
        short header = clientPacket.getHeader();
        IPacket packet = this.packetManager.getPacket(header);
        Client client = this.clientManager.getClient(ctx.channel().id());
        if (packet != null && client != null) {
            ctx.executor().submit(new PacketExecutor(client, clientPacket, packet));
        } else {
            System.out.println("Error while processing packet: " + header);
        }
    }
}
