package network.packets.types;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public class ClientPacket {

    private final ByteBuf buf;
    private final short header;

    public ClientPacket(ByteBuf buf) {
        this.buf = buf;
        this.header = buf.readShort();
    }

    public int readInt() {
        return this.buf.readInt();
    }

    public String readString() {
        short length = this.buf.readShort();
        byte[] data = new byte[length];
        this.buf.readBytes(data);
        return new String(data);
    }

    public short getHeader() {
        return this.header;
    }

    @Override
    public String toString() {
        return this.buf.toString(Charset.forName("UTF-8"));
    }

}
