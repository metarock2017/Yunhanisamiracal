package indi.yunhan.servlet;

import indi.yunhan.service.CreateRoomService;
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
 * Created by asus on 2017/8/14.
 */
@WebServlet(name = "Create_Room", value = "/room/create")
public class CreateRoomServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateRoomService createRoomService = new CreateRoomService();
        ServletDataHandle handle = new ServletDataHandle();
        String json = handle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = handle.jsonToMap(json);
        resp.setContentType("application/json;charset=utf-8");

        System.out.println(json);

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
                if (cookie.getName().equals("i_h")) {
                    resp.getWriter().print(createRoomService.getRoomData(createRoomService.getOpenIdByHost(cookie.getValue())));
                }
                else {
                    String repdata = createRoomService.createRoom(Integer.parseInt(jsonMap.get("maxNum")));
                    if (createRoomService.getCookie() != null) {
                        System.out.println(createRoomService.getCookie().getName() + " : " + createRoomService.getCookie().getValue());
                        resp.addCookie(createRoomService.getCookie());
                        resp.getWriter().print(repdata);
                    } else {
                        resp.getWriter().print(-1);
                    }
                }
            }
        } else {
            String repdata = createRoomService.createRoom(Integer.parseInt(jsonMap.get("maxNum")));
            if (createRoomService.getCookie() != null) {
                System.out.println(createRoomService.getCookie().getName() + " : " + createRoomService.getCookie().getValue());
                resp.addCookie(createRoomService.getCookie());
                resp.getWriter().print(repdata);
            } else {
                resp.getWriter().print(-1);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
