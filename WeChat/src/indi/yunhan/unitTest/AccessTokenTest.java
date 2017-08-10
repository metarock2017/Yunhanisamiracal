package indi.yunhan.unitTest;

import indi.yunhan.service.AccessTokenService;

/**
 * Created by asus on 2017/8/5.
 */
public class AccessTokenTest {

    public String tokenTest(){
        String token = AccessTokenService.getAccessToken();
        System.out.println(token);

        return token;
    }
    public static void main(String[] args) {
//        AccessTokenService.setAccessToken(AccessTokenService.getTokenFromCurl());

        System.out.println(AccessTokenService.getAccessToken());
    }
}
