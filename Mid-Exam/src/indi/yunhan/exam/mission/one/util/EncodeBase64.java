package indi.yunhan.exam.mission.one.util;


import sun.misc.BASE64Encoder;

/**
 * Created by asus on 2017/8/19.
 */
public class EncodeBase64 {
    public static String Encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();

        String base64Str = encoder.encode(str.getBytes());

        return base64Str;
    }
}
