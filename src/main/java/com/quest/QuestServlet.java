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

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {
    private static final String END_JSP = "/end.jsp";
    private static final String QUEST_JSP = "/quest.jsp";
    private static final String START_PAGE = "/start";
    private static final Logger LOGGER = LogManager.getLogger(QuestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");
        TreeNode currentRoot = (TreeNode) session.getAttribute("root");
        String currentNodeQuestion = (String) session.getAttribute("question");
        TreeNode currentNode = TreeNode.findNodeByQuestion(currentRoot, currentNodeQuestion);

        if (username == null || currentNodeQuestion == null || currentNode == null) {
            resp.sendRedirect(START_PAGE);
            return;
        }

        String answer = req.getParameter("answer");
        LOGGER.debug("Received request from user: {} with answer: {} for question: {}", username, answer, currentNodeQuestion);

        TreeNode nextNode = answer.equals("Yes") ? currentNode.getYesBranch() : currentNode.getNoBranch();

        session.setAttribute("question", nextNode.getQuestion());
        if (!nextNode.getFinal()) {
            getServletContext().getRequestDispatcher(QUEST_JSP).forward(req, resp);
        } else {
            LOGGER.debug("Last node question: {}", currentNodeQuestion);
            getServletContext().getRequestDispatcher(END_JSP).forward(req, resp);
        }
        LOGGER.info("Forwarding to next question or end page.");
    }
}

