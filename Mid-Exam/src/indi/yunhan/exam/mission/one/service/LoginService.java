package indi.yunhan.exam.mission.one.service;

import indi.yunhan.exam.mission.one.YhConst.Const;
import indi.yunhan.exam.mission.one.repository.UserRepository;
import indi.yunhan.exam.mission.one.util.EncodeBase64;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class LoginService {
    private UserRepository userRepository = new UserRepository();

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(Map<String, String> jsonMap) {
        String username = jsonMap.get("username");
        String password = jsonMap.get("password");

        return getUserRepository().login(username, EncodeBase64.Encode(password + Const.SALT));
    }

    public Map<String,String> getUserMap(Map<String,String> jsonMap){
        Map<String,String> userMap = new HashMap<>();

        userMap.put("username",jsonMap.get("username"));
        userMap.put("isOnline","1");

        return userMap;
    }
}
