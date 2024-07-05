package com.quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/displayTree")
public class DisplayTreeServlet extends HttpServlet {

    private TreeNode root;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            root = new TreeBuilder().buildTreeFromJson("C:\\Moje\\text-quest\\src\\main\\resources\\questions.json");
            if (root == null) {
                throw new ServletException("Failed to build tree from JSON.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException("Error reading JSON file.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Tree Structure</h1>");
        displayTree(out, root);
        out.println("</body></html>");
    }

    private void displayTree(PrintWriter out, TreeNode node) {
        if (node == null) {
            out.println("<p>Null node</p>");
            return;
        }
        out.println("<p>" + node.getQuestion() + "</p>");
        if (node.getYesBranch() != null) {
            out.println("<div style='margin-left: 20px;'>");
            out.println("<strong>Yes:</strong>");
            displayTree(out, node.getYesBranch());
            out.println("</div>");
        }
        if (node.getNoBranch() != null) {
            out.println("<div style='margin-left: 20px;'>");
            out.println("<strong>No:</strong>");
            displayTree(out, node.getNoBranch());
            out.println("</div>");
        }
    }
}
