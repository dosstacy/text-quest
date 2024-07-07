package com.quest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.nio.file.Paths;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            TreeNode root = new TreeBuilder().buildTreeFromJson(Paths.get("src", "main", "resources", "questions.json").toString());
            sce.getServletContext().setAttribute("root", root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

