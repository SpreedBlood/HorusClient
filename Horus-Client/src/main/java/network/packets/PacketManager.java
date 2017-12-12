package network.packets;

import network.packets.incoming.IncomingHeaders;
import network.packets.incoming.rooms.RoomEntryEvent;
import network.packets.types.IPacket;

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
    }

    private void registerRoom() {
        this.events.put(IncomingHeaders.RoomEntryMessageEvent, new RoomEntryEvent());
    }

    public IPacket getPacket(short header) {
        if (this.events.containsKey(header)) {
            return this.events.get(header);
        }
        return null;
    }

}
