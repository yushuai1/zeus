package pro;

public class BytesUtil {

    public final static int headLen = 4;

    public int getLenFromBytes(byte[] lenBytes) {
        int len = 0;
        for (int i = 0; i < headLen; i++) {
            len = len | ((lenBytes[i] & 0xFF) << ((headLen - 1 - i) * 8));
        }
        return len;
    }


    public byte[] getBytesFromLen(int len) {

        byte[] lenBytes = new byte[headLen];
        for (int i = 0; i < headLen; i++) {
            lenBytes[i] = (byte) ((len >> ((headLen - 1 - i) * 8)) & 0xFF);
        }
        return lenBytes;
    }
}
