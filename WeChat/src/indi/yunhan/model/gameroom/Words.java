package indi.yunhan.model.gameroom;

/**
 * Created by asus on 2017/8/23.
 */
public class Words {

    private String wordId;
    private String wordOne;
    private String wordTwo;

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
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

    public void setWords(String[] words){
        this.setWordOne(words[0]);
        this.setWordTwo(words[1]);
    }

    public Words(String id,String wordOne, String wordTwo) {
        this.wordId = id;
        this.wordOne = wordOne;
        this.wordTwo = wordTwo;
    }

    @Override
    public String toString() {
        return "{" +
                "wordId='" + wordId + '\'' +
                ", wordOne='" + wordOne + '\'' +
                ", wordTwo='" + wordTwo + '\'' +
                '}';
    }
}
