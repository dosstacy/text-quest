package com.quest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogicServlet", value = "/start")
public class LogicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        TreeBuilder builder = new TreeBuilder();
        try {
            TreeNode root = builder.buildTreeFromJson("C:\\Moje\\text-quest\\src\\main\\resources\\questions.json");
            System.out.println(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*String username = req.getParameter("username");

        HttpSession session = req.getSession();
        session.setAttribute("username", username);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");*/
    }
}


