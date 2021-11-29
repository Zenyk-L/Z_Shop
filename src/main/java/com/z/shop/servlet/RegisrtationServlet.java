package com.z.shop.servlet;

import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.UserService;
import com.z.shop.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisrtationServlet", value = "/registration")
public class RegisrtationServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(RegisrtationServlet.class);
    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        String success = null;


        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");

        if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty()
                && firstName != null && lastName != null && email != null && password != null) {

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(UserRole.USER);
            user.setAmount(0.00);

            System.out.println("User email" + user.getEmail());
            User userFromDB = userService.getUserByEmail(user.getEmail());
            System.out.println(userFromDB);
            if (userFromDB == null) {
                userService.create(user);
                success = "registered";
                out.println("alert('Successfully  registered ');");
            } else {
                success = "fail: user already registered";
                out.println("alert('Registration fail: user already registered ');");
            }
        } else {
            success = "invalid input";
            out.println("alert('Registration fail: invalid input');");
        }

        request.setAttribute("success", success);

        LOGGER.info(email + " : " + success);

//        response.sendRedirect("/home");


        out.println("location='/home';");
        out.println("</script>");


    }
}
