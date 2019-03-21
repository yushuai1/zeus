package com.移位运算.byte转int;

/**
 * Created by jiaobuchong on 2015/11/21.
 */
public class BytesToInt {
    /**
     * 低位在前, 高位在后, 将整型数字的每个字节保存到数组中
     *
     * @param value
     * @return
     */
    public static byte[] intToBytes(int value) {
        byte[] des = new byte[4];
        des[0] = (byte) (value & 0xff);  // 低位(右边)的8个bit位
        des[1] = (byte) ((value >> 8) & 0xff); //第二个8 bit位
        des[2] = (byte) ((value >> 16) & 0xff); //第三个 8 bit位
        /**
         * (byte)((value >> 24) & 0xFF);
         * value向右移动24位, 然后和0xFF也就是(11111111)进行与运算
         * 在内存中生成一个与 value 同类型的值
         * 然后把这个值强制转换成byte类型, 再赋值给一个byte类型的变量 des[3]
         */
        des[3] = (byte) ((value >> 24) & 0xff); //第4个 8 bit位
        return des;
    }

    /**
     * 将上面转成的byte数组转换成int原始值
     * @param des
     * @param offset
     * @return
     */
    public static int bytesToInt(byte[] des, int offset) {

        System.out.println((des[offset + 2] & 0xff) << 6);

        int value;
        value = (int) (des[offset] & 0xff
                | ((des[offset + 1] & 0xff) << 8)
                | ((des[offset + 2] & 0xff) << 16)
                | (des[offset + 3] & 0xff) << 24);
        return value;
    }

    public static void main(String[] args) {
        byte[] res = intToBytes(123456789);
        System.out.println("----"+((byte)1<<33));
        System.out.println(bytesToInt(res, 0));  //30
    }
}
