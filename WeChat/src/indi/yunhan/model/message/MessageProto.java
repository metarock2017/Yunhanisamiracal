package indi.yunhan.model.message;

import java.util.Map;

/**
 * Created by asus on 2017/8/8.
 */
public class MessageProto {
    protected String msgType;
    protected String toUserName;
    protected String fromUserName;

    protected Map<String, String> keyValue;
    protected String clearText;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        fromUserName = fromUserName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
