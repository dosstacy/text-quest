package com.quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("username", username);

        HttpSession session = req.getSession();
        session.setAttribute("username", username);

        TreeNode root = (TreeNode) getServletContext().getAttribute("root");
        if (root != null) {
            req.setAttribute("question", root.getQuestion());
            req.setAttribute("yesBranch", root.getYesBranch());
            req.setAttribute("noBranch", root.getNoBranch());
        }

        req.getRequestDispatcher("/greeting.jsp").forward(req, resp);
    }
}

