package indi.yunhan.model.gameroom;

/**
 * Created by asus on 2017/8/9.
 */
public class GameRoom {
    private int openId;
    private String md5;

    private int maxNum;
    private int nowNum;

    private String wordOne;
    private String wordTwo;

    private int undercover;

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public String getWordOne() {
        return wordOne;
    }

    public void setWordOne(String wordOne) {
        this.wordOne = wordOne;
    }

    public String getWordTwo() {
        return wordTwo;
    }

    public void setWordTwo(String wordTwo) {
        this.wordTwo = wordTwo;
    }

    public int getUndercover() {
        return undercover;
    }

    public void setUndercover(int undercover) {
        this.undercover = undercover;
    }

    public GameRoom() {

    }

    public GameRoom(int openId, String md5, int maxNum, Words words) {
        this.setOpenId(openId);
        this.setMd5(md5);
        this.setMaxNum(maxNum);
        this.setWord(words);
    }

    @Override
    public String toString() {
        return "{" +
                "openId=" + openId +
                ", md5='" + md5 + '\'' +
                ", maxNum=" + maxNum +
                ", nowNum=" + nowNum +
                ", wordOne='" + wordOne + '\'' +
                ", wordTwo='" + wordTwo + '\'' +
                ", undercover=" + undercover +
                '}';
    }

    public void setWord(String[] words) {
        this.setWordOne(words[0]);
        this.setWordTwo(words[1]);
    }

    public void setWord(Words word) {
        this.setWordOne(word.getWordOne());
        this.setWordTwo(word.getWordTwo());
    }

    public void addUndercover() {
        int now = this.getNowNum();
        this.setNowNum(now + 1);
        int nowUndercover = this.getUndercover();
        this.setUndercover(nowUndercover + 1);
    }

    public void addCommonPerson() {
        int now = this.getNowNum();
        this.setNowNum(now + 1);
    }

    public static void main(String[] args) {
        GameRoom gameRoom = new GameRoom();
        System.out.println(gameRoom.toString());

        gameRoom.addCommonPerson();
        gameRoom.addCommonPerson();
        gameRoom.addCommonPerson();
        gameRoom.addCommonPerson();
        gameRoom.addCommonPerson();

        gameRoom.addUndercover();
        gameRoom.addUndercover();
        gameRoom.addUndercover();
        gameRoom.addUndercover();

        System.out.println(gameRoom.toString());
    }
}
