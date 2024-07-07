package com.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/restart")
public class RestartServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(RestartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Invalidating session and redirecting to /start");
        try {
            req.getSession().invalidate();
            resp.sendRedirect("/quest/");
        }catch (IOException e){
            LOGGER.error("IOException occurred while redirecting to /start", e);
            throw e;
        }
    }
}
