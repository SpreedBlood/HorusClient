package network.packets.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public abstract class ServerPacket {

    private ByteBuf buf;

    public ServerPacket(int header) {
        this.buf = Unpooled.buffer();
        this.buf.writeShort((short)header);
    }

    protected void writeInt(int i) {
        this.buf.writeInt(i);
    }

    protected void writeString(String s) {
        int length = s.length();
        this.buf.writeShort(length);
        this.buf.writeBytes(s.getBytes());
    }

    public ByteBuf getBuf() {
        return this.buf;
    }

}
