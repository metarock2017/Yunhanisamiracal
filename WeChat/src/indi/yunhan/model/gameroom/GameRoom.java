package indi.yunhan.model.gameroom;

import java.util.ArrayList;

/**
 * Created by asus on 2017/8/9.
 */
public class GameRoom {
    private int roomNum;
    private int roomSize;

    private ArrayList<User> userList = null;

    public GameRoom(int roomNum, int roomSize) {
        this.roomNum = roomNum;
        this.roomSize = roomSize;
        this.userList = new ArrayList<>();
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object obj) {
        GameRoom gm = (GameRoom) obj;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GameRoom)) {
            return false;
        }
        if (this.roomNum == gm.roomNum) {
            return true;
        } else {
            return false;
        }
    }
}
