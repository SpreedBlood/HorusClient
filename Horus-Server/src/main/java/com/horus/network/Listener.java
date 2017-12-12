package com.horus.network;

import com.horus.network.clients.ClientManager;
import com.horus.network.packets.PacketManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by SpreedBlood on 2017-12-09.
 */
public class Listener implements Runnable {

    private final int port;

    public Listener(final int port) {
        this.port = port;
    }

    public void run() {
        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new Handler(new PacketManager(), new ClientManager()));
                }
            });
            bootstrap.option(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT).option(ChannelOption.TCP_NODELAY, true);
            ChannelFuture channel = bootstrap.bind(this.port).sync();
            if (channel.isSuccess()) {
                System.out.println("Server is now listening on " + this.port);
            } else {
                System.out.println("Failed to listen on " + this.port);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
