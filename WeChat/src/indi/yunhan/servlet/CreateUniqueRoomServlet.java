package indi.yunhan.servlet;

import indi.yunhan.service.CreateRoomService;
import indi.yunhan.servlet.handle.ServletDataHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by asus on 2017/8/15.
 */
@WebServlet(name = "Create_Room_Unique", value = "/room/create/unique")
public class CreateUniqueRoomServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateRoomService createRoomService = new CreateRoomService();
        ServletDataHandle handle = new ServletDataHandle();

        String json = handle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = handle.jsonToMap(json);

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(createRoomService.createRoomUnique(jsonMap.get("word_one"),jsonMap.get("word_two"), Integer.parseInt(jsonMap.get("maxNum"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
