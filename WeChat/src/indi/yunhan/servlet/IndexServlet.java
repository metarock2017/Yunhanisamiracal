package indi.yunhan.servlet;

import indi.yunhan.service.MessageService;
import indi.yunhan.util.Const;
import indi.yunhan.util.EncryptUtil;
import indi.yunhan.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by asus on 2017/8/3.
 */
@WebServlet(name = "index", value = "/yunhan")
public class IndexServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // 分发请求
        MessageService messageTestService = new MessageService();
        String message = null;

        response.getWriter().print(messageTestService.replyMsg(request));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token = Const.Token;
        // 获取请求的四个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        // 检验四个参数是否有效
        if (!StringUtil.hasBlank(signature, timestamp, nonce, echostr)) {
            String[] list = {token, timestamp, nonce};
            // 字典排序
            Arrays.sort(list);
            // 拼接字符串
            StringBuilder builder = new StringBuilder();
            for (String str : list) {
                builder.append(str);
            }
            // sha1加密
            String hashcode = EncryptUtil.sha1(builder.toString());
            // 不区分大小写差异情况下比较是否相同
            if (hashcode.equalsIgnoreCase(signature)) {
                // 响应输出
                response.getWriter().println(echostr);
            }
        }
    }
}