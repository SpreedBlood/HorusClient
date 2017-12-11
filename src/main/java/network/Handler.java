package network;

import main.Game;
import network.clients.Client;
import network.packets.PacketExecutor;
import network.packets.PacketManager;
import network.packets.outgoing.rooms.RoomEntryComposer;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
class Handler extends SimpleChannelInboundHandler<ClientPacket> {

    private final PacketManager packetManager;

    Handler(final PacketManager packetManager) {
        this.packetManager = packetManager;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Game.getInstance().setClient(new Client(ctx));
        Game.getInstance().getClient().writeAndFlush(new RoomEntryComposer(4));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        super.channelReadComplete(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ClientPacket clientPacket) throws Exception {
        System.out.println("hihi");
        short header    = clientPacket.getHeader();
        IPacket packet  = this.packetManager.getPacket(header);
        Client client   = Game.getInstance().getClient();
        if (packet != null && client != null) {
            channelHandlerContext.executor().submit(new PacketExecutor(client, clientPacket, packet));
        } else {
            System.out.println("Error while processing packet: " + header);
        }
    }
}