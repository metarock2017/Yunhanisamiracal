package indi.yunhan.util;

/**
 * Created by asus on 2017/8/6.
 */

import indi.yunhan.service.AccessTokenService;
import indi.yunhan.unitTest.ButtonToJson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class YhCurl {

    public static final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET请求
    public static void sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //默认值我GET
        con.setRequestMethod("GET");

        //添加请求头
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        System.out.println(response.toString());

    }

    // HTTP POST请求
    public static void sendPost(String url, String json) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        //发送Post请求
        con.setDoOutput(true);
        OutputStream outputStream = con.getOutputStream();
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + json);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        System.out.println(response.toString());

    }

    public static void main(String[] args) throws Exception {

        YhCurl http = new YhCurl();

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" +
                AccessTokenService.getAccessToken(), new ButtonToJson().ButtonStringTest());
    }

}