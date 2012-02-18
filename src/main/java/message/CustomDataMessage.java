package message;

import java.util.Arrays;
import protocol.Message;

/**
 * This packet allows transfer of custom data for mods to the Notchian client.
 */
public class CustomDataMessage extends Message {

    private final byte[] data;
    private final String type;

    public CustomDataMessage(String type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CustomDataMessage{" + "data=" + Arrays.toString(data) + ", type=" + type + '}';
    }
}
