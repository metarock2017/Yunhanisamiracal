package indi.yunhan.exam.mission.one.servlet;

import indi.yunhan.exam.mission.one.service.SetQuestionService;
import indi.yunhan.exam.mission.common.ServletDataHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
@WebServlet(name = "user/setQuestion",value = "/one/question/set")
public class SetQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = ServletDataHandle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = ServletDataHandle.jsonToMap(jsonStr);

        SetQuestionService setQuestionService = new SetQuestionService();
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(setQuestionService.setQuestion(jsonMap));
    }
}
