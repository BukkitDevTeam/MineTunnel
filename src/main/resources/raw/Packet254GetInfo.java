package raw;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet254GetInfo extends Packet {

    public Packet254GetInfo() {}

    public void a(DataInputStream datainputstream) {}

    public void a(DataOutputStream dataoutputstream) {}

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 0;
    }
}
