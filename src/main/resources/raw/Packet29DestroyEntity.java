package raw;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet29DestroyEntity extends Packet {

    public int a;

    public Packet29DestroyEntity() {}

    public Packet29DestroyEntity(int i) {
        this.a = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4;
    }
}
