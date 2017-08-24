package indi.yunhan.servlet;

import com.google.gson.Gson;
import indi.yunhan.service.ChangeWordService;
import indi.yunhan.service.CreateRoomService;
import indi.yunhan.servlet.handle.CommonError;
import indi.yunhan.servlet.handle.ServletDataHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by asus on 2017/8/21.
 */
@WebServlet(name = "word/change", value = "/room/patch")
public class ChangeWordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChangeWordService changeWordService = new ChangeWordService();
        ServletDataHandle handle = new ServletDataHandle();
        String json = handle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = handle.jsonToMap(json);
        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new Gson();

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("i_h")) {
                    String respData = changeWordService.change(cookie.getValue(), jsonMap);
                    resp.getWriter().print(respData);
                }
            }
        } else {
            resp.getWriter().print(gson.toJson(new CommonError("500", "房间过期啦")));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
