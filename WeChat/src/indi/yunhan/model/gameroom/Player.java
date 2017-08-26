package indi.yunhan.model.gameroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 2017/8/9.
 */
public class Player {
    private int roomId;
    private int playerNum;

    private String playerWord;
    private String playerChar;

    private Set openIds = new HashSet();

    public int getUserNum() {
        return playerNum;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getPlayerWord() {
        return playerWord;
    }

    public void setPlayerWord(String playerWord) {
        this.playerWord = playerWord;
    }

    public String getPlayerChar() {
        return playerChar;
    }

    public void setPlayerChar(String playerChar) {
        this.playerChar = playerChar;
    }

    public Set getOpenIds() {
        return openIds;
    }

    public void setOpenIds(Set openIds) {
        this.openIds = openIds;
    }

    public void setUserNum(int userNum) {
        this.playerNum = userNum;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUserWord() {
        return playerWord;
    }

    public void setUserWord(String userWord) {
        this.playerWord = userWord;
    }

    public String getUserChar() {
        return playerChar;
    }

    public void setUserChar(String userChar) {
        this.playerChar = userChar;
    }

    public Player(int playerNum, int roomId, String playerWord, boolean isUndercover) {
        this.playerNum = playerNum;
        this.roomId = roomId;
        this.playerWord = playerWord;
        if (isUndercover) {
            this.playerChar = "卧底";
        } else {
            this.playerChar = "平民";
        }
    }
}
