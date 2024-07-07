package com.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class RestartServletTest {
    private RestartServlet restartServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() throws ServletException {
    restartServlet = new RestartServlet();

        ServletConfig config = mock(ServletConfig.class);
        ServletContext servletContext = mock(ServletContext.class);
        when(config.getServletContext()).thenReturn(servletContext);
        restartServlet.init(config);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getSession()).thenReturn(session);

        restartServlet.doPost(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect("/quest/");
    }

    @Test
    public void testDoPostWithIOException() throws Exception {
        when(request.getSession()).thenReturn(session);
        doThrow(new IOException()).when(response).sendRedirect("/quest/");

        assertThrows(IOException.class, () -> restartServlet.doPost(request, response));

        verify(session).invalidate();
    }

}