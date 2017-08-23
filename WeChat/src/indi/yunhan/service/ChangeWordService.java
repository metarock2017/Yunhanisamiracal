package indi.yunhan.service;

import indi.yunhan.Repository.GameRoomRepository;

import java.util.Map;

/**
 * Created by asus on 2017/8/21.
 */

public class ChangeWordService {
    private GameRoomRepository gameRoomRepository = new GameRoomRepository();

    public GameRoomRepository getGameRoomRepository() {
        return gameRoomRepository;
    }

    public void setGameRoomRepository(GameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }

    public String change(Map<String,String> jsonMap){
        return "";
    }
}
