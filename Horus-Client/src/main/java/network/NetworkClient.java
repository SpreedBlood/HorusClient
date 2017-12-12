package network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import main.Game;
import network.client.Client;
import network.packets.PacketManager;

public class NetworkClient {

    private final int port;
    private final String host;

    public NetworkClient(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new Handler(new PacketManager()));
            }
        });

        bootstrap.remoteAddress(this.host, this.port);

        final ChannelFuture connectFuture = bootstrap.connect();

        connectFuture.addListener((future) -> {
            if (future.isSuccess()) {
                Game.initialize(new Client(connectFuture.channel()));
            } else {
                System.out.println("Failed to connect to the server: " + this.host + " on port: " + this.port);
            }
        });
    }
}