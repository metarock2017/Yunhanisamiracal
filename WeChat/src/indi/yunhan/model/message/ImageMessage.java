package indi.yunhan.model.message;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2017/8/10.
 */
public class ImageMessage extends MessageProto {
    private String clearText;
    private Map<String, String> keyValue;

    private final String msgTemplate = "<xml><ToUserName><![CDATA[%s]]></ToUserName>\n" +
            "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
            "<CreateTime>%s</CreateTime>\n" +
            "<MsgType><![CDATA[image]]></MsgType>\n" +
            "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
            "<MsgId>%s</MsgId>\n" +
            "<MediaId><![CDATA[%s]]></MediaId>\n" +
            "</xml>";
    private String msgXml;

    public ImageMessage(String clearText) {
        this.clearText = clearText;
        this.msgType = "image";
    }

    public ImageMessage(String toUserName, String fromUserName, String picUrl, String msgId, String mediaId) {
        this.msgXml = String.format(
                msgTemplate,
                toUserName,
                fromUserName,
                System.currentTimeMillis() / 1000 + "",
                picUrl,
                msgId,
                mediaId);
    }

    public boolean hasDate() {
        if (this.keyValue.size() >= 1 && !(this.clearText.equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        if (this.hasDate()) {
            return this.msgXml;
        } else {
            System.out.println("Un Init");
            return null;
        }
    }
}
