package network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.nio.NioSocketChannel;
import main.Game;
import network.clients.Client;
import network.packets.PacketManager;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by SpreedBlood on 2017-12-09.
 */
public class NetworkClient {

    private final int port;
    private final String host;

    public NetworkClient(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new Encoder()).addLast(new Decoder()).addLast(new Handler(new PacketManager()));
                }
            });

            ChannelFuture f = b.connect(this.host, this.port).sync();
            if (f.isSuccess()) {
                System.out.println("Successfully connected to host: " + this.host + " on port: " + this.port);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}