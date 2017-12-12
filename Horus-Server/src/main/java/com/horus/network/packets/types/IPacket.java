package com.horus.network.packets.types;

import com.horus.network.clients.Client;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public interface IPacket {

    void run(Client client, ClientPacket clientPacket);

}
