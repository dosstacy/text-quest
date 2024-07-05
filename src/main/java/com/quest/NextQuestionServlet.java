package com.quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/next")
public class NextQuestionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        String answer = req.getParameter("answer");
        String currentNodeQuestion = req.getParameter("currentNode");

        TreeNode currentNode = findNodeByQuestion((TreeNode) getServletContext().getAttribute("root"), currentNodeQuestion);
        TreeNode nextNode = "Yes".equals(answer) ? currentNode.getYesBranch() : currentNode.getNoBranch();

        req.getSession().setAttribute("username", username);
        if (nextNode != null) {
            req.setAttribute("question", nextNode.getQuestion());
            req.setAttribute("yesBranch", nextNode.getYesBranch());
            req.setAttribute("noBranch", nextNode.getNoBranch());
            req.getRequestDispatcher("/question.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/end.jsp").forward(req, resp);
        }
    }

    private TreeNode findNodeByQuestion(TreeNode root, String question) {
        if (root == null) {
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

