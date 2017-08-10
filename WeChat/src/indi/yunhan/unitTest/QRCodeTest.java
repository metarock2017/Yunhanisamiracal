package indi.yunhan.unitTest;

import com.google.gson.Gson;
import indi.yunhan.model.QRCode.QRCode;
import indi.yunhan.model.QRCode.Scene;
import indi.yunhan.service.AccessTokenService;
import indi.yunhan.util.YhCurl;

/**
 * Created by asus on 2017/8/6.
 */
public class QRCodeTest {
    public static void main(String[] args) {
        QRCode qrCode = new QRCode(604800, "QR_SCENE", new Scene(123, "indi/yunhan/demo"));

        Gson gson = new Gson();

        String s = gson.toJson(qrCode);

        System.out.println(s);

        try {
            YhCurl.sendPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+ AccessTokenService.getAccessToken(),s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //JznNeviWtT4rRl0kMkApgWF_OEPvlnNuFIz1JB7366IvJcLMR4PjBQh9mhcJXO9JZe1SXWD85i6aaVvjBpVH0XyfsXFhdN7X30lQsDMIoQsKDNfAIAATD

        try {
            YhCurl.sendGet("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGX8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAydTVRdUFtd3k5T18xeC1WZ3hwMVMAAgT_-oZZAwSAOgkA");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //gQGX8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAydTVRdUFtd3k5T18xeC1WZ3hwMVMAAgT_-oZZAwSAOgkA
    }
}
