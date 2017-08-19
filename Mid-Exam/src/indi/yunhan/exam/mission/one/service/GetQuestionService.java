package indi.yunhan.exam.mission.one.service;

import indi.yunhan.exam.mission.one.repository.QuestionRepository;

import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
public class GetQuestionService {
    private QuestionRepository questionRepository = new QuestionRepository();

    public QuestionRepository getQuestionRepository() {
        return questionRepository;
    }

    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public String getQuestion(Map<String, String> jsonMap) {
        String username = jsonMap.get("username");

        return getQuestionRepository().getQuestion(username);
    }
}
