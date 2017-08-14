package indi.yunhan.service;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import indi.yunhan.util.Const;
import indi.yunhan.util.CurlUtil;

import java.util.Map;

/**
 * Created by asus on 2017/8/5.
 */
public class AccessTokenService {

    public static Jedis jedis = new Jedis("localhost");

    public static String getTokenFromCurl() {

        String accessTokenJson = CurlUtil.getContent("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
                Const.TestAppId + "&secret=" +
                Const.TestAppSecret, null, "GET", 1);

//        System.out.println(accessTokenJson);

        Gson gson = new Gson();

        Map<String, String> map = gson.fromJson(accessTokenJson, Map.class);

        AccessTokenService.setAccessToken(map.get("access_token"));

        return map.get("access_token");
    }

    public static void setAccessToken(String accessToken) {

        jedis.set("access_token", accessToken);

        jedis.expire("access_token", 7200);
    }

    public static String getAccessToken() {

        synchronized (AccessTokenService.class) {
            String token = jedis.get("access_token");

            if (token == null || token.equals("null") || token.equals("")) {
                System.out.println("AccessToken was out of time");

                return getTokenFromCurl();
            } else {
                System.out.println(jedis.get("access_token"));
                return token;
            }
        }
    }

    public static void clearAccessToken(){
        jedis.set("access_token", "");
    }
}
