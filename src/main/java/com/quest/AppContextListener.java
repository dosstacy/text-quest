package com.quest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            TreeNode root = new TreeBuilder().buildTreeFromJson("C:\\Moje\\text-quest\\src\\main\\resources\\questions.json");
            sce.getServletContext().setAttribute("root", root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

