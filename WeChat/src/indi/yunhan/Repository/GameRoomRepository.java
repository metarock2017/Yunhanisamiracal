package indi.yunhan.Repository;

import com.google.gson.Gson;
import indi.yunhan.model.gameroom.GameRoom;
import indi.yunhan.model.gameroom.Player;
import indi.yunhan.model.gameroom.TypeAndId;
import indi.yunhan.model.gameroom.Words;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by asus on 2017/8/9.
 */
public class GameRoomRepository {
    private DBConnection dbConnection;
    private Gson gson = new Gson();
    private Jedis jedis = new Jedis("localhost");

    private Words nullWord = new Words("-1", "null", "null");
    private GameRoom nullRoom = new GameRoom(-1, "", -1, nullWord);

//    private static Set<Integer> rooms = new HashSet<>();

    class GameRoomError {
        private String errorCode;
        private String errorMsg;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public GameRoomError(String errorCode, String errorMsg) {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }
    }


    public DBConnection getDbConnection() {
        dbConnection = new DBConnection();
        return dbConnection;
    }

    public int undercoverRate(int openId) {
        GameRoom gameRoom = gson.fromJson(getRoom(openId), GameRoom.class);

        if (gameRoom.getNowNum() + 1 == gameRoom.getMaxNum() && !hasUndercover(openId)) {
            return 100;
        } else {
            int rate = randomInt(0, 100) + gameRoom.getNowNum() * 10;
            return rate;
        }
    }

    public static int randomInt(int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        int randomInt = random.nextInt(max - min) + min + 1;

        return randomInt;
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPlayerSum(int openId) {
        GameRoom gameRoom = gson.fromJson(getRoom(openId), GameRoom.class);

        return gameRoom.getNowNum();
    }

    public boolean hasUndercover(int openId) {
        GameRoom gameRoom = gson.fromJson(getRoom(openId), GameRoom.class);
        if (gameRoom.getUndercover() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getRoom(int openId) {
        String roomJson = jedis.hget("rooms", String.valueOf(openId));
        if (roomJson == null) {
            return gson.toJson(nullRoom);
        } else {
            return roomJson;
        }
    }

    public int getOpenId() {
        int openId = Integer.parseInt(jedis.spop("roomidset"));

        return openId;
    }

    public boolean isRoomFull(int openId) {
        GameRoom gameRoom = gson.fromJson(getRoom(openId), GameRoom.class);
        if (gameRoom.getNowNum() == gameRoom.getMaxNum()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRoomExist(int openId) {
        String data = jedis.get(String.valueOf(openId));
        if (!(data.equals("")) && data != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getPlayer(int playerNum, int openId, String playerWord, boolean isUnderCover) {
        Player player = new Player(playerNum, openId, playerWord, isUnderCover);

        return gson.toJson(player);
    }

    public Words getWordNameById(int id) {
        String wordJson = jedis.hget("words", String.valueOf(id));
        Words words = gson.fromJson(wordJson, Words.class);

        return words;
    }

    public int createWord(String wordOne, String wordTWo) {
        String setSql = "INSERT INTO words (word_one,word_two) VALUES ('" + wordOne + "','" + wordTWo + "')";
        String getIdSql = "SELECT * FROM words WHERE word_one='" + wordOne + "' AND word_two='" + wordTWo + "'";
        boolean setFlag = dbConnection.excuteSqlWithoutResult(setSql);

        if (setFlag) {
            ResultSet rs = dbConnection.excuteSqlWithResult(getIdSql);
            try {
                rs.first();
                int wordId = rs.getInt("word_id");
                return wordId;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void clearRoom() {
        double stamp = System.currentTimeMillis();
        Set<String> outTimeRoom = jedis.zrangeByScore("roomstamp", 0, stamp - 10.0);

        jedis.zremrangeByScore("roomstamp", 0, stamp - 10.0);

        for (String roomid : outTimeRoom) {
            jedis.sadd("roomidset", roomid);
        }
    }

    public void setUser(String md5, String type,String openId) {
        String relation = gson.toJson(new TypeAndId(type,openId));
        jedis.hset("user", md5, relation);
    }

    public String getUserType(String md5){
        TypeAndId relation = gson.fromJson(jedis.hget("user",md5),TypeAndId.class);

        return relation.getType();
    }

    public String getOpenIdByHost(String md5){
        TypeAndId relation = gson.fromJson(jedis.hget("user",md5),TypeAndId.class);

        return relation.getOpenId();
    }

    public String createRoom(int maxNum) {
        clearRoom();
        String roomData;
        String md5 = getMD5(System.currentTimeMillis() + "");
        int openId = getOpenId();
        if (openId == -1) {
            return gson.toJson(new GameRoomError("1", "Too much Room"));
        } else {
            int wordId = randomInt(0, 216);
            GameRoom gameRoom = new GameRoom(
                    openId,
                    md5,
                    maxNum,
                    getWordNameById(wordId)
            );
            roomData = gson.toJson(gameRoom);

            jedis.zadd("roomStamp", System.currentTimeMillis() / 1000, String.valueOf(openId));
            jedis.hset("rooms", String.valueOf(openId), roomData);

            return roomData;
        }
    }

    public void updateRoom(int openId, String roomJson) {
        jedis.hset("rooms", String.valueOf(openId), roomJson);
    }

    public String createRoomUnique(String word_one, String word_two, int max_num) {
        return null;
    }


    public String addPlayer(int openId) {

        GameRoom gameRoom = gson.fromJson(getRoom(openId), GameRoom.class);
        if (gameRoom.getOpenId() > 0) {
            if (!isRoomFull(openId)) {
                if (undercoverRate(openId) > 70 && !(hasUndercover(openId))) {
                    gameRoom.addUndercover();
                    updateRoom(openId, gson.toJson(gameRoom));
                    return gson.toJson(
                            new Player(
                                    gameRoom.getNowNum(),
                                    gameRoom.getOpenId(),
                                    gameRoom.getWordTwo(), true
                            )
                    );
                } else {
                    gameRoom.addCommonPerson();
                    updateRoom(openId, gson.toJson(gameRoom));
                    return gson.toJson(
                            new Player(
                                    gameRoom.getNowNum(),
                                    gameRoom.getOpenId(),
                                    gameRoom.getWordOne(), false
                            )
                    );
                }
            } else {
                return gson.toJson(
                        new GameRoomError(
                                "-1",
                                "Room is full")
                );
            }
        } else {
            return gson.toJson(
                    new GameRoomError(
                            "-1",
                            "Room is not exist")
            );
        }
    }

    public String changeWord(int openId) {
        return "";
    }

    public void initRedisRoomNum() {
        if (jedis.scard("roomidset") > 0) {
            return;
        } else {
            for (int i = 1000; i < 9999; i++) {
                jedis.sadd("roomidset", String.valueOf(i));
            }
            return;
        }
    }

    public Map<String, String> getAllWords() {
        String getSql = "SELECT * FROM words";
        ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);
        Map<String, String> words = new HashMap<>();

        try {
            while (rs.next()) {
                words.put(rs.getString("id"),
                        gson.toJson(
                                new Words(
                                        rs.getString("id"),
                                        rs.getString("word_one"),
                                        rs.getString("word_two")
                                )));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return words;
    }

    public Long initWordsSet(Map map) {
        return jedis.hlen("words");
    }


    public void demo() {
//        System.out.println(jedis.hget("words","1"));
//        System.out.println(getWordNameById(1).toString());
        clearRoom();
    }

    public static void main(String[] args) {
        GameRoomRepository repository = new GameRoomRepository();
        repository.jedis.flushAll();
        repository.initRedisRoomNum();
        repository.initWordsSet(repository.getAllWords());
//
//        repository.createRoom(10);
//        repository.demo();
    }
}