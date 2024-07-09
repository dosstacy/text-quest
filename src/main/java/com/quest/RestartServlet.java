package com.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/restart")
public class RestartServlet extends HttpServlet {
    private static final String START_PAGE = "/start";
    private static final Logger LOGGER = LogManager.getLogger(RestartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Invalidating session and restarting game");
        try {
            req.getSession().invalidate();
            resp.sendRedirect(START_PAGE);
        } catch (Exception e) {
            LOGGER.error("Error occurred while invalidating session: ", e);
            throw new RuntimeException("Error occurred while invalidating session", e);
        }
    }
}
