package indi.yunhan.model.gameroom;

/**
 * Created by asus on 2017/8/9.
 */
public class Player {
    private int playerNum;
    private int roomId;

    private String playerWord;
    private String playerChar;

    public int getUserNum() {
        return playerNum;
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
