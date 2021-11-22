package com.z.shop.servlet;

import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.UserService;
import com.z.shop.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String success = null;
        if(!email.isEmpty() && email != null) {
            User user = userService.getUserByEmail(email);

            if (user.getId() != null && user.getPassword().equals(password)) {
                user.setPassword("");
                session = request.getSession();
                session.setAttribute("user", user);
                success = "success";

            }

        } else {
            success = "invalid input";
        }

        session.setAttribute("success", success);
        LOGGER.info("User " + email + " logged in");
        response.sendRedirect("/home");

    }
}
