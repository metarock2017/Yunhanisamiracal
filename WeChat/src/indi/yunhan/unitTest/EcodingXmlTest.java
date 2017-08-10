package indi.yunhan.unitTest;

import indi.yunhan.model.message.TextMessage;

/**
 * Created by asus on 2017/8/8.
 */
public class EcodingXmlTest {
    public static void main(String[] args) {
        TextMessage textMessage = new TextMessage("<xml><ToUserName><![CDATA[gh_de2e7c3a4672]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oLxGzwMCE9ifU0QlQUMah00qJBc4]]></FromUserName>\n" +
                "<CreateTime>1502195397</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[123]]></Content>\n" +
                "<MsgId>6451880102854462831</MsgId>\n" +
                "</xml>");

        textMessage.getKeyValueFromClearText();
        System.out.println(textMessage.toString());
        textMessage.exchangeUser();
        System.out.println(textMessage.toString());
    }
}
