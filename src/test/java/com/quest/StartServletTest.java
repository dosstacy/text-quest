package com.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

class StartServletTest {
    private StartServlet startServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext servletContext;
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    public void setUp() throws ServletException {
        startServlet = new StartServlet();

        ServletConfig config = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);
        when(config.getServletContext()).thenReturn(servletContext);
        startServlet.init(config);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(servletContext.getAttribute("root")).thenReturn(new TreeNode("Test Question", true));

        when(request.getRequestDispatcher("/greeting.jsp")).thenReturn(requestDispatcher);

        startServlet.doGet(request, response);

        verify(request).setAttribute("username", "testUser");
        verify(session).setAttribute("username", "testUser");
        verify(request).setAttribute("question", "Test Question");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(servletContext.getAttribute("root")).thenReturn(new TreeNode("Test Question", true));

        when(request.getRequestDispatcher("/greeting.jsp")).thenReturn(requestDispatcher);

        startServlet.doPost(request, response);

        verify(request).setAttribute("username", "testUser");
        verify(session).setAttribute("username", "testUser");
        verify(request).setAttribute("question", "Test Question");
        verify(requestDispatcher).forward(request, response);
    }
}