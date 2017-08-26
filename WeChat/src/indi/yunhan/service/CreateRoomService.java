package indi.yunhan.service;

import indi.yunhan.Repository.GameRoomRepository;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.Cookie;

/**
 * Created by asus on 2017/8/9.
 */
public class CreateRoomService {
    private GameRoomRepository gameRoomRepository = new GameRoomRepository();
    private Cookie cookie;

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public GameRoomRepository getGameRoomRepository() {
        return gameRoomRepository;
    }

    public String createRoom(int maxNum) {
        try {
            String resp = this.getGameRoomRepository().createRoom(maxNum);
            JSONObject jsonObject = new JSONObject(resp);
            String timeStamp = System.currentTimeMillis() / 1000 + "";

            String openId = jsonObject.getString("openId");
            this.setCookie(new Cookie(
                            "i_h",
                            GameRoomRepository.getMD5(
                                    openId + timeStamp
                            )
                    )
            );
            this.getCookie().setMaxAge(120);
            this.getGameRoomRepository().setUser(GameRoomRepository.getMD5(openId + timeStamp), "0", openId);
            return resp;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isHost(String md5) {
        if (getGameRoomRepository().getUserType(md5).equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    public String getOpenIdByHost(String md5) {
        return this.getGameRoomRepository().getOpenIdByHost(md5);
    }

    public String getRoomData(String openId) {
        return this.getGameRoomRepository().getRoom(Integer.parseInt(openId));
    }


    public String createRoomUnique(String wordOne, String wordTwo, int maxNum) {
        return this.getGameRoomRepository().createRoomUnique(wordOne, wordTwo, maxNum);
    }

}
