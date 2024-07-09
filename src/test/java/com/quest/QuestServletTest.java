package com.quest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

class QuestServletTest {
    private QuestServlet questServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() throws ServletException {
        questServlet = new QuestServlet();

        ServletConfig config = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);
        when(config.getServletContext()).thenReturn(servletContext);
        questServlet.init(config);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost_NonFinalNode() throws Exception {
        TreeNode root = new TreeNode("Is it an animal?", false);
        TreeNode yesNode = new TreeNode("Does it have fur?", false);
        root.setYesBranch(yesNode);

        when(servletContext.getAttribute("root")).thenReturn(root);
        when(request.getParameter("currentNode")).thenReturn("Is it an animal?");
        when(request.getParameter("answer")).thenReturn("Yes");

        when(request.getRequestDispatcher("/question.jsp")).thenReturn(dispatcher);

        questServlet.doPost(request, response);

        verify(request).setAttribute("question", "Does it have fur?");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_FinalNode() throws Exception {
        TreeNode root = new TreeNode("Is it an animal?", false);
        TreeNode yesNode = new TreeNode("It's a cat!", true);
        root.setYesBranch(yesNode);
        when(servletContext.getAttribute("root")).thenReturn(root);
        when(request.getParameter("currentNode")).thenReturn("Is it an animal?");
        when(request.getParameter("answer")).thenReturn("Yes");
        when(request.getRequestDispatcher("/end.jsp")).thenReturn(dispatcher);

        questServlet.doPost(request, response);

        verify(request).setAttribute("question", "It's a cat!");
        verify(dispatcher).forward(request, response);
    }

}