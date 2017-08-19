package indi.yunhan.exam.mission.two.servlet;

import indi.yunhan.exam.mission.two.service.ShowStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 2017/8/19.
 */
@WebServlet(name = "/student/show",value = "/two/student/show")
public class ShowStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShowStudentService showStudentService = new ShowStudentService();
        resp.getWriter().print(showStudentService.show());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
