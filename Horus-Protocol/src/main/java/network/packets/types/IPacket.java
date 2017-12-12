package network.packets.types;

import network.client.Client;

public interface IPacket {

    void run(Client client, ClientPacket clientPacket);

}
