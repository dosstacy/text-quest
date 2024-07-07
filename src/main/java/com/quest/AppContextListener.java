package com.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.nio.file.Paths;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(StartServlet.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            TreeNode root = new TreeBuilder().buildTreeFromJson(Paths.get("src", "main", "resources", "questions.json").toString());
            sce.getServletContext().setAttribute("root", root);
        } catch (IOException e) {
            LOGGER.error("Failed to initialize context due to IOException: {}", e.getMessage(), e);
        }
    }
}

