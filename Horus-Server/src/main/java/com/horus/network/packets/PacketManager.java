package com.horus.network.packets;

import com.horus.network.packets.incoming.IncomingHeaders;
import com.horus.network.packets.incoming.handshake.SetupClientEvent;
import com.horus.network.packets.incoming.rooms.RoomEntryEvent;
import com.horus.network.packets.types.IPacket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class PacketManager {

    private Map<Short, IPacket> events;

    public PacketManager() {
        this.events = new HashMap<>();
        this.registerRoom();
        this.registerHandShake();
    }

    private void registerHandShake() {
        put(IncomingHeaders.SetupClientMessageEvent, new SetupClientEvent());
    }

    private void registerRoom() {
        put(IncomingHeaders.RoomEntryMessageEvent, new RoomEntryEvent());
    }

    private void put(short id, IPacket packet) {
        this.events.put(id, packet);
    }

    public IPacket getPacket(short header) {
        if (this.events.containsKey(header)) {
            return this.events.get(header);
        }
        return null;
    }

}
