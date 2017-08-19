package indi.yunhan.exam.mission.one.service;

import indi.yunhan.exam.mission.one.repository.UserRepository;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class ReSetPsdService {
    private UserRepository userRepository = new UserRepository();

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String reSetPsd(Map<String, String> jsonMap) {
        String username = jsonMap.get("username");
        String reply = jsonMap.get("reply");
        String passwordNew = jsonMap.get("passwordNew");

        return getUserRepository().reSetPsd(username, reply, passwordNew);
    }
}
