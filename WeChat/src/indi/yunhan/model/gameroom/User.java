package indi.yunhan.model.gameroom;

/**
 * Created by asus on 2017/8/9.
 */
public class User {
    private int userNum;
    private int userKeyWord;

    public User(int userNum, int userKeyWord) {
        this.userNum = userNum;
        this.userKeyWord = userKeyWord;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getUserKeyWord() {
        return userKeyWord;
    }

    public void setUserKeyWord(int userKeyWord) {
        this.userKeyWord = userKeyWord;
    }
}
