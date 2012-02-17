package raw;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet39AttachEntity extends Packet {

    public int a;
    public int b;

    public Packet39AttachEntity() {}

    public Packet39AttachEntity(Entity entity, Entity entity1) {
        this.a = entity.id;
        this.b = entity1 != null ? entity1.id : -1;
    }

    public int a() {
        return 8;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }
}
