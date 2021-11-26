package com.example.AV_SOFT.servlets;

import com.example.AV_SOFT.dao.UserDAO;
import com.example.AV_SOFT.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.AV_SOFT.model.ROLE.ADMIN;
import static com.example.AV_SOFT.model.ROLE.USER;

@WebListener
public class ContextListener implements ServletContextListener {
    private AtomicReference<UserDAO> dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        dao = new AtomicReference<>(new UserDAO());
        dao.get().add(new User(1, "user1", "1", ADMIN));
        dao.get().add(new User(2, "user2", "2", USER));
        dao.get().add(new User(3, "user3", "3", ADMIN));
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
