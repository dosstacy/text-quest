package com.quest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GreetingServletTest {
    private final GreetingServlet greetingServlet = new GreetingServlet();
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private final RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

    @Test
    public void testDoGet() throws Exception {
        when(request.getRequestDispatcher("/greeting.jsp")).thenReturn(requestDispatcher);
        greetingServlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

}