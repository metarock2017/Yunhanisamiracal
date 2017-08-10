package indi.yunhan.model.message;

import java.util.Map;

/**
 * Created by asus on 2017/8/10.
 */
public class ImageAndTextMessage extends MessageProto {
    private String clearText;
    private Map<String, String> keyValue;

    private String msgTemplateFirst = "<xml>\n" +
            "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
            "<CreateTime>%s</CreateTime>\n" +
            "<MsgType><![CDATA[news]]></MsgType>\n" +
            "<ArticleCount>%s</ArticleCount>\n" +
            "<Articles>";

    private String msgTemplateItem = "<item>\n" +
            "<Title><![CDATA[%s]]></Title> \n" +
            "<Description><![CDATA[%s]]></Description>\n" +
            "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
            "<Url><![CDATA[%s]]></Url>\n" +
            "</item>";

    private String msgTemplateLast = "<item>\n" +
            "<Title><![CDATA[%s]]></Title>\n" +
            "<Description><![CDATA[%s]]></Description>\n" +
            "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
            "<Url><![CDATA[%s]]></Url>\n" +
            "</item>\n" +
            "</Articles>\n" +
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

    public String getMsgTemplateFirst() {
        return msgTemplateFirst;
    }

    public void setMsgTemplateFirst(String msgTemplateFirst) {
        this.msgTemplateFirst = msgTemplateFirst;
    }

    public String getMsgTemplateItem() {
        return msgTemplateItem;
    }

    public void setMsgTemplateItem(String msgTemplateItem) {
        this.msgTemplateItem = msgTemplateItem;
    }

    public String getMsgTemplateLast() {
        return msgTemplateLast;
    }

    public void setMsgTemplateLast(String msgTemplateLast) {
        this.msgTemplateLast = msgTemplateLast;
    }

    public String getMsgXml() {
        return msgXml;
    }

    public void setMsgXml(String msgXml) {
        this.msgXml = msgXml;
    }

    @Override
    public String toString(){
        return "";
    }
}
