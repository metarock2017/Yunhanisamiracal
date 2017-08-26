package indi.yunhan.service;

import com.google.gson.Gson;
import indi.yunhan.Repository.GameRoomRepository;

import javax.servlet.http.Cookie;

/**
 * Created by asus on 2017/8/14.
 */
public class PlayerService {
    private GameRoomRepository gameRoomRepository = new GameRoomRepository();

    public GameRoomRepository getGameRoomRepository() {
        return gameRoomRepository;
    }

    Cookie cookie;
    Gson gson = new Gson();

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public String addPlayer(int roomId, String md5) {
        if (md5 != null) {
            this.getGameRoomRepository().addPlayer(roomId);

            String data = this.getGameRoomRepository().addPlayer(roomId);
            this.getGameRoomRepository().setPlayer(md5, data);
            return data;
        } else {
            String data = this.getGameRoomRepository().addPlayer(roomId);
            String timeStamp = System.currentTimeMillis() / 1000 + "";
            md5 = this.getGameRoomRepository().getMD5(timeStamp);
            this.getGameRoomRepository().setPlayer(md5, data);

            this.setCookie(new Cookie("i_p", md5));
            this.getCookie().setMaxAge(120);

            return data;
        }
    }

    public String clearPlayer(String md5) {
        return this.getGameRoomRepository().clearPlayerByMd5(md5);
    }

    public boolean isHost(int openId, String md5) {
        if (Integer.valueOf(this.getGameRoomRepository().getOpenIdByHost(md5)) == openId) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasPlayer(int roomId, String md5) {
        return this.getGameRoomRepository().hasPlayer(roomId, md5);
    }

    public String getPlayer(String md5) {
        return this.getGameRoomRepository().getPlayer(md5);
    }
}
