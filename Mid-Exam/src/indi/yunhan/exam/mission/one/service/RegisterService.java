package indi.yunhan.exam.mission.one.service;

import indi.yunhan.exam.mission.one.YhConst.Const;
import indi.yunhan.exam.mission.one.repository.UserRepository;
import indi.yunhan.exam.mission.one.util.EncodeBase64;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class RegisterService {
    private UserRepository userRepository = new UserRepository();

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(Map<String, String> jsonMap) {
        String username = jsonMap.get("username");
        String password = jsonMap.get("password");

        return getUserRepository().register(username, EncodeBase64.Encode(password + Const.SALT));
    }
}
