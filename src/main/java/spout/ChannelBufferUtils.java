package spout;

import org.jboss.netty.buffer.ChannelBuffer;

public class ChannelBufferUtils {

    /**
     * Writes a string to the buffer.
     *
     * @param buf The buffer.
     * @param str The string.
     * @throws IllegalArgumentException if the string is too long <em>after</em>
     * it is encoded.
     */
    public static void writeString(ChannelBuffer buf, String str) {
        int len = str.length();
        if (len >= 65536) {
            throw new IllegalArgumentException("String too long.");
        }

        buf.writeShort(len);
        for (int i = 0; i < len; ++i) {
            buf.writeChar(str.charAt(i));
        }
    }

    /**
     * Reads a string from the buffer.
     *
     * @param buf The buffer.
     * @return The string.
     */
    public static String readString(ChannelBuffer buf) {
        int len = buf.readUnsignedShort();

        char[] characters = new char[len];
        for (int i = 0; i < len; i++) {
            characters[i] = buf.readChar();
        }

        return new String(characters);
    }

    public static int getShifts(int height) {
        if (height == 0) {
            return 0;
        }
        int shifts = 0;
        int tempVal = height;
        while (tempVal != 1) {
            tempVal >>= 1;
            ++shifts;
        }
        return shifts;
    }

    public static int getExpandedHeight(int shift) {
        if (shift > 0 && shift < 12) {
            return 2 << shift;
        } else if (shift >= 32) {
            return shift;
        }
        return 128;
    }
}
