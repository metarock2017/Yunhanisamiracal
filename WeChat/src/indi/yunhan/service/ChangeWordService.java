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

    public String change(String md5, Map<String, String> jsonMap) {
        int maxNum = -1;
        if (jsonMap.get("maxNum") != null) {
            maxNum = Integer.parseInt(jsonMap.get("maxNum"));
        }
        String newGameRoom = this.getGameRoomRepository().changeWord(md5, maxNum);

        return newGameRoom;
    }
}
