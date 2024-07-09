package com.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/start")
public class StartServlet extends HttpServlet {
    private static final String GREETING_PAGE = "/greeting";
    private static final String START_JSP = "/start.jsp";
    private static final Logger LOGGER = LogManager.getLogger(StartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(START_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");

        HttpSession session = req.getSession();
        session.setAttribute("username", username);

        TreeNode root = TreeNode.treeInitializer();
        if (root != null) {
            session.setAttribute("root", root);
            session.setAttribute("question", root.getQuestion());
        } else {
            LOGGER.error("Root node is null");
            resp.sendRedirect(START_JSP);
            return;
        }

        resp.sendRedirect(GREETING_PAGE);
    }
}
