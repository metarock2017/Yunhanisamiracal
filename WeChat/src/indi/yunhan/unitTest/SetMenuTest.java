package indi.yunhan.unitTest;

import indi.yunhan.service.AccessTokenService;
import indi.yunhan.util.YhCurl;

import java.io.IOException;

/**
 * Created by asus on 2017/8/6.
 */
public class SetMenuTest {
    public static void main(String[] args) throws IOException {
        try {
            YhCurl.sendPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                    + AccessTokenService.getAccessToken(), new ButtonToJson().ButtonStringTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
