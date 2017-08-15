package indi.yunhan.service;

import indi.yunhan.Repository.GameRoomRepository;

/**
 * Created by asus on 2017/8/9.
 */
public class CreateRoomService {
    private GameRoomRepository gameRoomRepository = new GameRoomRepository();

    public GameRoomRepository getGameRoomRepository() {
        return gameRoomRepository;
    }

    public String createRoom(int max_num) {
        return this.getGameRoomRepository().createRoom(max_num);
    }

    public String createRoomUnique(String wordOne, String wordTwo, int maxNum) {
        return this.getGameRoomRepository().createRoomUnique(wordOne,wordTwo,maxNum);
    }
}
