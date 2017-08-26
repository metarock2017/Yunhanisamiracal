package indi.yunhan.servlet;

import indi.yunhan.servlet.handle.ServletDataHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 2017/8/26.
 */
@WebServlet(name = "demo",value = "/demo")
public class demo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletDataHandle servletDataHandle = new ServletDataHandle();
        String jsonStr = servletDataHandle.getJsonFromStream(req.getInputStream());
        System.out.println(jsonStr);
    }
}
