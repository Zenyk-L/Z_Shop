package com.z.shop.servlet;

import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.UserService;
import com.z.shop.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("editUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConf = request.getParameter("newPasswordConfirm");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !oldPassword.isEmpty()
                && firstName != null && lastName != null && email != null && oldPassword != null) {

            User userWithNewEmail = userService.getUserByEmail(email);
            if(userWithNewEmail == null || user.getEmail().equals(email) ) {
                User userFromDB = userService.getUserByEmail(user.getEmail());

                if (userFromDB.getPassword().equals(oldPassword)) {
                    userFromDB.setFirstName(firstName);
                    userFromDB.setLastName(lastName);
                    userFromDB.setEmail(email);

                    if (!newPassword.isEmpty() && !newPasswordConf.isEmpty() && newPassword != null && newPasswordConf != null && newPasswordConf.equals(newPassword)) {
                        userFromDB.setPassword(newPassword);
                    }

                    userService.update(userFromDB);
                    userFromDB.setPassword("");
                    session.setAttribute("user", userFromDB);
                }
            }
        }

        response.sendRedirect("/home");
    }
}
