package indi.yunhan.Repository;

import com.google.gson.Gson;
import indi.yunhan.model.gameroom.GameRoom;
import indi.yunhan.model.gameroom.Player;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by asus on 2017/8/9.
 */
public class GameRoomRepository extends RepositoryProto {
    private DBConnection dbConnection;
    private Gson gson = new Gson();

    public String noRoomReturn = "{\"playerNum\":0,\"roomId\":0,\"playerWord\":\"房间不存在\",\"playerChar\":\"emm\"}";
    public String fullRoomReturn = "{\"playerNum\":0,\"roomId\":0,\"playerWord\":\"房间已满\",\"playerChar\":\"emm\"}";

    public DBConnection getDbConnection() {
        dbConnection = new DBConnection();
        return dbConnection;
    }

    public static int randomWordInt() {
        Random random = new Random(System.currentTimeMillis());
        int randomInt = random.nextInt(216) + 1;

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

    public int getWordId(int roomId) {
        String getSql = "SELECT word_id FROM rooms WHERE id='" + roomId + "'";

        ResultSet rs = this.getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            return rs.getInt("word_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getPlayerSum(int roomId) {
        String getSql = "SELECT now_num FROM rooms WHERE id='" + roomId + "'";

        ResultSet rs = this.getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            return rs.getInt("now_num");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String[] getWordNameById(int id) {
        String[] wordArray = new String[2];
        String getSql = "SELECT * FROM words WHERE id='" + id + "' AND status=1";

        ResultSet rs = this.getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            wordArray[0] = rs.getString("word_one");
            wordArray[1] = rs.getString("word_two");

            return wordArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWordNameBySingle(int id, boolean isUndercover) {
        String getSql = "SELECT * FROM words WHERE id='" + id + "' AND status=1";

        ResultSet rs = this.getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();

            if (isUndercover) {
                return rs.getString("word_two");
            } else {
                return rs.getString("word_one");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createRoom(int maxNum) {
        Map<String, String> map = new HashMap<>();

        String md5 = getMD5(System.currentTimeMillis() + "");
        int randowWordId = randomWordInt();

        String createSql = "INSERT INTO rooms (max_num,word_id,md5) VALUES ('" + maxNum + "','" + randowWordId + "','" + md5 + "')";
        String getSql = "SELECT * FROM rooms WHERE md5='" + md5 + "'";

        boolean flag = getDbConnection().excuteSqlWithoutResult(createSql);
        if (flag) {
            try {
                ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);
                rs.first();

                GameRoom gameRoom = new GameRoom(rs);
                gameRoom.setWord(getWordNameById(randowWordId));
                String gameRoomJson = gson.toJson(gameRoom);

                return gameRoomJson;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("fail");
        }
        return null;
    }

    public String createRoomUnique(String word_one, String word_two, int max_num) {

        String setWordSql = "INSERT INTO words (word_one, word_two) VALUES('" + word_one + "','" + word_two + "')";
        String getSql = "SELECT * FROM words WHERE word_one='" + word_one + "' AND word_two='" + word_two + "'";
        int word_id;
        boolean flag = getDbConnection().excuteSqlWithoutResult(setWordSql);
        if (flag){
            ResultSet rs1 = getDbConnection().excuteSqlWithResult(getSql);
            try {
                rs1.first();
                word_id = rs1.getInt("id");

                String md5 = getMD5(System.currentTimeMillis() + "");
                String setRoomSql = "INSERT INTO rooms (max_num,word_id,md5) VALUES ('" + max_num + "','" + word_id + "','" + md5 + "')";

                boolean roomFlag = getDbConnection().excuteSqlWithoutResult(setRoomSql);

                if (roomFlag){
                    String getRoomSql = "SELECT * FROM rooms WHERE md5='" + md5 + "'";
                    ResultSet rs2 = getDbConnection().excuteSqlWithResult(getRoomSql);

                    rs2.first();

                    GameRoom gameRoom = new GameRoom(rs2);
                    gameRoom.setWord(getWordNameById(word_id));
                    String gameRoomJson = gson.toJson(gameRoom);

                    return gameRoomJson;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean hasUndercover(int roomId) {
        String getSql = "SELECT undercover FROM rooms WHERE id='" + roomId + "'";
        ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);

        if (rs == null) {
            return false;
        } else {
            try {
                rs.first();
                if (rs.getInt("undercover") > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getRoom(int roomId) {
        String getSql = "SELECT * FROM rooms WHERE id='" + roomId + "'";
        ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            GameRoom gameRoom = new GameRoom(rs);

            return gson.toJson(gameRoom);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPlayer(int playerNum, int roomId, String playerWord, boolean isUnderCover) {
        Player player = new Player(playerNum, roomId, playerWord, isUnderCover);

        return gson.toJson(player);
    }

    public boolean isRoomFull(int roomId) {
        String getSql = "SELECT * FROM rooms WHERE id='" + roomId + "'";
        ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            int max = rs.getInt("max_num");
            int now = rs.getInt("now_num");

            if (max == now) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isRoomExist(int roomId) {
        String getSql = "SELECT * FROM rooms WHERE id='" + roomId + "'";
        ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            if (!(rs.getRow() > 0)) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int undercoverRate(int roomId) {
        String getSql = "SELECT * FROM rooms WHERE id='" + roomId + "'";
        ResultSet rs = getDbConnection().excuteSqlWithResult(getSql);

        try {
            rs.first();
            return rs.getInt("now_num") * 10;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String addPlayer(int roomId) {
        String addPlayerSql = "UPDATE rooms SET now_num=now_num+1 WHERE id='" + roomId + "'";
        String addUndercoverSql = "UPDATE rooms SET undercover=undercover+1 WHERE id='" + roomId + "'";

        Random random = new Random(System.currentTimeMillis());
        int randomRate = random.nextInt(100) + 1 + undercoverRate(roomId);

        boolean existFlag = isRoomExist(roomId);
        boolean fullFlag = isRoomFull(roomId);
        boolean hasUndercoverFlag = hasUndercover(roomId);

        if (existFlag) {
            if (!fullFlag) {
                if (!hasUndercoverFlag) {
                    if (randomRate >= 90) {
                        getDbConnection().excuteSqlWithoutResult(addPlayerSql);
                        getDbConnection().excuteSqlWithoutResult(addUndercoverSql);

                        // TODO SOMETHING
                        return getPlayer(getPlayerSum(roomId), roomId, getWordNameBySingle(getWordId(roomId), true), true);
                    } else {
                        getDbConnection().excuteSqlWithoutResult(addPlayerSql);
                        // TODO SOMETHING
                        return getPlayer(getPlayerSum(roomId), roomId, getWordNameBySingle(getWordId(roomId), false), false);
                    }
                } else {
                    getDbConnection().excuteSqlWithoutResult(addPlayerSql);
                    // TODO SOMETHING
                    return getPlayer(getPlayerSum(roomId), roomId, getWordNameBySingle(getWordId(roomId), false), false);
                }
            } else {
                return fullRoomReturn;
            }
        } else {
            return noRoomReturn;
        }
    }

    public static void main(String[] args) {
        GameRoomRepository gameRoomRepository = new GameRoomRepository();

        System.out.println(gameRoomRepository.createRoomUnique("123","1234",10));
    }
}