package com.fantai.util;

public class DataFormat {
    /**
     * 十六进制数组转十六进制字符串
     */
    public static String bytes2HexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();


    }

    /**
     * 获取电压值
     */
    public static double getV(byte v) {
        double elect = 0.0;
        switch (v) {
            case (byte) 0x61:
                elect = 3.5;
                break;
            case (byte) 0x62:
                elect = 3.85;
                break;
            case (byte) 0x63:
                elect = 4.10;
                break;
            case (byte) 0x64:
                elect = 4.20;
                break;
        }
        return elect;


    }


    /**
     * 十六进制字符串转十六进制数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}
