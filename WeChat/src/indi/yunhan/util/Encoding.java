package indi.yunhan.util;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * Created by asus on 2017/8/8.
 */
public class Encoding {
    public static final String decrypt(String msgSignature, String timestamp, String nonce, String decryptedXml) {
        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(Const.Token, Const.EncodingAESKey, Const.AppId);

            String clearText = wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, decryptedXml);

            return clearText;
        } catch (AesException e) {
            e.printStackTrace();
        }

        return "null";
    }

    public static final String encrypt(String replyMsg, String nonce) {
        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(Const.Token, Const.EncodingAESKey, Const.AppId);

            String unClearText = wxBizMsgCrypt.encryptMsg(replyMsg, System.currentTimeMillis() / 1000 + "", nonce);

            return unClearText;
        } catch (AesException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
