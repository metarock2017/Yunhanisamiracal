package indi.yunhan.servlet;

import indi.yunhan.service.AddPlayerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 2017/8/14.
 */
@WebServlet(name = "Add_Player", value = "/player/create")
public class AddPlayerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddPlayerService addPlayerService = new AddPlayerService();
        resp.setContentType("test/html;charset=utf-8");
        resp.getWriter().print(addPlayerService.addPlayer(Integer.parseInt(req.getParameter("roomId"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
