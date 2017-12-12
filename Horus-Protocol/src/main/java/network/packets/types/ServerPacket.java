package network.packets.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public abstract class ServerPacket {

    private ByteBuf buf;

    public ServerPacket(int header) {
        this.buf = Unpooled.buffer();
        this.buf.writeShort(header);
    }

    protected void writeInt(int i) {
        this.buf.writeInt(i);
    }

    protected void writeString(String s) {
        this.buf.writeShort(s.length());
        this.buf.writeBytes(s.getBytes());
    }

    public ByteBuf getBuf() {
        return this.buf;
    }

}
