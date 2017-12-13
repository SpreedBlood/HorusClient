package network.packets;

import network.packets.incoming.handshake.SetupClientEvent;
import network.packets.incoming.rooms.RoomEntryEvent;
import network.packets.incoming.rooms.entity.UserWalkEvent;
import network.packets.types.IPacket;

import java.util.HashMap;
import java.util.Map;


public class PacketManager {

    private Map<Short, IPacket> events;

    public PacketManager() {
        this.events = new HashMap<>();
        this.registerRoom();
        this.registerHandShake();
    }

    private void registerHandShake() {
        put(1, new SetupClientEvent());
    }

    private void registerRoom() {
        put(300, new RoomEntryEvent());
        put(2, new UserWalkEvent());
    }

    private void put(int id, IPacket packet) {
        this.events.put((short)id, packet);
    }

    public IPacket getPacket(short header) {
        if (this.events.containsKey(header)) {
            return this.events.get(header);
        }
        return null;
    }

}
