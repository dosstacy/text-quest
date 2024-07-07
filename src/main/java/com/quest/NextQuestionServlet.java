package com.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/next")
public class NextQuestionServlet extends HttpServlet {
    private static final String END = "end.jsp";
    private static final String QUEST = "/question.jsp";
    private static final Logger LOGGER = LogManager.getLogger(NextQuestionServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        String answer = req.getParameter("answer");
        String currentNodeQuestion = req.getParameter("currentNode");

        LOGGER.debug("Received request from user: {} with answer: {} for question: {}", username, answer, currentNodeQuestion);

        TreeNode currentNode = findNodeByQuestion((TreeNode) getServletContext().getAttribute("root"), currentNodeQuestion);
        TreeNode nextNode = "Yes".equals(answer) ? currentNode.getYesBranch() : currentNode.getNoBranch();

        req.getSession().setAttribute("username", username);
        if (!nextNode.getFinal()) {
            req.setAttribute("question", nextNode.getQuestion());
            req.getSession().setAttribute("question", nextNode.getQuestion());
            req.setAttribute("yesBranch", nextNode.getYesBranch());
            req.setAttribute("noBranch", nextNode.getNoBranch());
            req.getRequestDispatcher(QUEST).forward(req, resp);
        } else {
            LOGGER.debug("Last node question: {}", currentNodeQuestion);
            req.setAttribute("question", nextNode.getQuestion());
            req.getSession().setAttribute("question", nextNode.getQuestion());
            req.getRequestDispatcher(END).forward(req, resp);
        }
        LOGGER.info("Forwarding to next question or end page.");
    }

    private TreeNode findNodeByQuestion(TreeNode root, String question) {
        if (root == null) {
            LOGGER.warn("root is null");
            return null;
        }
        if (root.getQuestion().equals(question)) {
            return root;
        }
        TreeNode foundNode = findNodeByQuestion(root.getYesBranch(), question);
        if (foundNode == null) {
            foundNode = findNodeByQuestion(root.getNoBranch(), question);
        }
        return foundNode;
    }
}

