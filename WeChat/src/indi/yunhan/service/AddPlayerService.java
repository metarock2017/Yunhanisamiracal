package indi.yunhan.service;

import indi.yunhan.Repository.GameRoomRepository;

/**
 * Created by asus on 2017/8/14.
 */
public class AddPlayerService {
    private GameRoomRepository gameRoomRepository = new GameRoomRepository();

    public GameRoomRepository getGameRoomRepository() {
        return gameRoomRepository;
    }

    public String addPlayer(int roomId) {
        return this.getGameRoomRepository().addPlayer(roomId);
    }
}
