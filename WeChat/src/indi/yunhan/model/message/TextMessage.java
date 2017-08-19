package indi.yunhan.model.message;

import indi.yunhan.model.message.messagehandle.MessageHandle;

import java.util.Map;

/**
 * Created by asus on 2017/8/8.
 */
public class TextMessage extends MessageProto {
    private String clearText;
    private Map<String, String> keyValue;

    private final String msgTemplate = "" +
            "<xml><ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
            "<CreateTime>%s</CreateTime>\n" +
            "<MsgType><![CDATA[text]]></MsgType>\n" +
            "<Content><![CDATA[%s]]></Content>\n" +
            "<MsgId>%s</MsgId>\n" +
            "</xml>";

    private String msgXml;

    public String getClearText() {
        return clearText;
    }

    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    public Map<String, String> getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Map<String, String> keyValue) {
        this.keyValue = keyValue;
    }

    public String getMsgTemplate() {
        return msgTemplate;
    }

    public String getMsgXml() {
        return msgXml;
    }

    public void setMsgXml(String msgXml) {
        this.msgXml = msgXml;
    }

    public TextMessage(String clearText) {
        this.clearText = clearText;
        this.msgType = "test";
    }

    public TextMessage(String toUserName, String fromUserName, String content, String msgId) {
        this.msgXml = String.format(
                this.msgTemplate,
                toUserName,
                fromUserName,
                System.currentTimeMillis() / 1000 + "",
                content,
                msgId);
        this.msgType = "text";
    }

    public void setKeyValue(String clearText) {
        this.keyValue = MessageHandle.getKeyValueFromClearText(clearText);
    }

    public void exchangeUser() {
        String toUserName = this.keyValue.get("ToUserName");
        String fromUserName = this.keyValue.get("FromUserName");

        this.keyValue.put("ToUserName", fromUserName);
        this.keyValue.put("FromUserName", toUserName);
    }

    @Override
    public String toString() {
        return String.format(
                this.msgTemplate,
                this.keyValue.get("ToUserName"),
                this.keyValue.get("FromUserName"),
                this.keyValue.get("CreateTime"),
                this.keyValue.get("Content"),
                this.keyValue.get("MsgId"));
    }
}
