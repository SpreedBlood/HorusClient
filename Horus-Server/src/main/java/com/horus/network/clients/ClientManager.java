package com.horus.network.clients;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class ClientManager {

    private Map<ChannelId, Client> clients;

    public ClientManager() {
        clients = new HashMap<>();
    }

    public void addClient(ChannelHandlerContext channel) {
        if (!this.clients.containsKey(channel.channel().id())) {
            clients.put(channel.channel().id(), new Client(channel));
        }
    }

    public void removeClient(ChannelId id) {
        if (this.clients.containsKey(id)) {
            this.clients.remove(id);
        }
    }

    public Client getClient(ChannelId id) {
        if (this.clients.containsKey(id)) {
            return this.clients.get(id);
        }
        return null;
    }

}
