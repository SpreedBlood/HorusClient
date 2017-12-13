package network.packets.incoming.handshake;

import network.client.Client;
import network.packets.outgoing.rooms.RoomDataComposer;
import network.packets.types.ClientPacket;
import network.packets.types.IPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetupClientEvent implements IPacket {
    @Override
    public void run(Client client, ClientPacket clientPacket) {
        String heightMap = "xxxxxxx{13}x00000x{13}x00000x{13}000000x{13}x00000x{13}x00000x{13}x00000x{13}x00000x{13}xxxxxxx";
        List<String> lines = new ArrayList<>(Arrays.asList(heightMap.split("\\{13}")));

        client.writeAndFlush(new RoomDataComposer(1, lines));
    }
}
