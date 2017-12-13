package horus.client.network.packets;

import horus.client.network.packets.incoming.rooms.RoomEntryEvent;
import network.packets.types.IPacket;

import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private Map<Integer, IPacket> events;

    public PacketManager() {
        this.events = new HashMap<>();
        this.registerRoom();
    }

    private void registerRoom() {
        this.events.put(300, new RoomEntryEvent());
    }

    public boolean hasPacket(int header) {
        return this.events.containsKey(header);
    }

    public IPacket getPacket(int header) {
        if (this.events.containsKey(header)) {
            return this.events.get(header);
        }
        return null;
    }

}
