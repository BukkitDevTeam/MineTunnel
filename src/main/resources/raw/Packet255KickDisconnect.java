package raw;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet255KickDisconnect extends Packet {

    public String a;

    public Packet255KickDisconnect() {}

    public Packet255KickDisconnect(String s) {
        this.a = s;
    }

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 256);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return this.a.length();
    }
}
