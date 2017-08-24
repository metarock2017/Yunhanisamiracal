package indi.yunhan.service;

import indi.yunhan.Repository.GameRoomRepository;

/**
 * Created by asus on 2017/8/14.
 */
public class PlayerService {
    private GameRoomRepository gameRoomRepository = new GameRoomRepository();

    public GameRoomRepository getGameRoomRepository() {
        return gameRoomRepository;
    }

    public String addPlayer(int roomId) {
        return this.getGameRoomRepository().addPlayer(roomId);
    }

    public String clearPlayer(String md5){
        return this.getGameRoomRepository().clearPlayerByMd5(md5);
    }
}
