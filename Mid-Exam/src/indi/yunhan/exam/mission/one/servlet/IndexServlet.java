package indi.yunhan.exam.mission.one.servlet;

import indi.yunhan.exam.mission.common.ServletDataHandle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by asus on 2017/8/19.
 */
@WebServlet(name = "index", value = "/one/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = ServletDataHandle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = ServletDataHandle.jsonToMap(jsonStr);

        HttpSession session = req.getSession();

        if (session.getAttribute("userTable") != null) {
            resp.getWriter().print("Hello World");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
