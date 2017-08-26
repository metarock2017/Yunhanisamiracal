package indi.yunhan.servlet;

import com.google.gson.Gson;
import indi.yunhan.service.PlayerService;
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
 * Created by asus on 2017/8/14.
 */
@WebServlet(name = "Add_Player", value = "/player/create")
public class AddPlayerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerService playerService = new PlayerService();
        ServletDataHandle handle = new ServletDataHandle();
        Gson gson = new Gson();

        String json = handle.getJsonFromStream(req.getInputStream());
        Map<String, String> jsonMap = handle.jsonToMap(json);

        resp.setContentType("application/json;charset=utf-8");

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
                if (cookie.getName().equals("i_h")) {
                    System.out.println("i_h");
                    System.out.println("host cookie " + cookie.getValue());
                    if (playerService.isHost(Integer.parseInt(jsonMap.get("roomId")), cookie.getValue())) {
                        System.out.println(jsonMap.get("roomId"));
                        System.out.println(cookie.getValue());
                        resp.getWriter().print(gson.toJson(new CommonError("500", "房主")));
                    }
                }
                else if (cookie.getName().equals("i_p")) {
                    System.out.println("i_p");
                    System.out.println("player cookie " + cookie.getValue());
                    if (playerService.hasPlayer(Integer.parseInt(jsonMap.get("roomId")), cookie.getValue())) {
                        System.out.println("玩家不存在");
                        // 更新房间信息 (roomId)
                        // 更新玩家持有id信息 (md5)
                        System.out.println(cookie.getValue());
                        String respData = playerService.addPlayer(Integer.parseInt(jsonMap.get("roomId")), cookie.getValue());
                        resp.addCookie(playerService.getCookie());
                        resp.getWriter().print(respData);
                    } else {
                        // 只返回玩家信息
                        System.out.println("玩家已存在");
                        System.out.println(cookie.getValue());
                        resp.getWriter().print(playerService.getPlayer(cookie.getValue()));
                    }
                }
                else {
                    System.out.println("else");
                    String respData = playerService.addPlayer(Integer.parseInt(jsonMap.get("roomId")), null);
                    System.out.println(respData);
                    resp.addCookie(playerService.getCookie());
                    resp.getWriter().print(respData);
                }
            }
        } else {
            System.out.println("else");
            String respData = playerService.addPlayer(Integer.parseInt(jsonMap.get("roomId")), null);
            System.out.println(respData);
            resp.addCookie(playerService.getCookie());
            resp.getWriter().print(respData);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
