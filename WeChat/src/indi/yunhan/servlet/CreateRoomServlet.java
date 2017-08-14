package indi.yunhan.servlet;

import indi.yunhan.service.CreateRoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 2017/8/14.
 */
@WebServlet(name = "Create_Room", value = "/room/create")
public class CreateRoomServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateRoomService createRoomService = new CreateRoomService();
        resp.setContentType("test/html;charset=utf-8");
        resp.getWriter().print(createRoomService.createRoom(Integer.parseInt(req.getParameter("maxNum"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
