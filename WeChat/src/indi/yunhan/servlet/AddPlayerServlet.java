package indi.yunhan.servlet;

import indi.yunhan.service.PlayerService;
import indi.yunhan.servlet.handle.ServletDataHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by asus on 2017/8/14.
 */
@WebServlet(name = "Add_Player", value = "/player/create")
public class AddPlayerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerService playerService = new PlayerService();
        ServletDataHandle handle = new ServletDataHandle();

        String json = handle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = handle.jsonToMap(json);

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(playerService.addPlayer(Integer.parseInt(jsonMap.get("roomId"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
