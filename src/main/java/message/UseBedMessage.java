package message;

import protocol.Message;

public final class UseBedMessage extends Message {
    // TODO: find a better name then used and possably rename this whole class

    private final int id, used, x, y, z;

    public UseBedMessage(int id, int used, int x, int y, int z) {
        this.id = id;
        this.used = used;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getId() {
        return id;
    }

    public int getUsed() {
        return used;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "UseBedMessage{id=" + id + ",used=" + used + ",x=" + x + ",y=" + y + ",z=" + z + "}";
    }
}
