package com.z.shop.servlet;

import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.UserService;
import com.z.shop.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Directing to user edit jsp. Needed data for jsp form will get from session user.
         */
        request.getRequestDispatcher("editUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Validating received data from form.
         * If user email isn't changed or changed to email not present in any user from DB inputted data
         * will be updated.
         * If fields for new password not filled or don't match (new password and confirm new password)
         * password won't be changed.
         * */

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConf = request.getParameter("newPasswordConfirm");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");

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
                        out.println("alert('Password and all other data updated');");
                    }else{
                        if (!newPassword.isEmpty() && !newPasswordConf.isEmpty() && newPassword != null && newPasswordConf != null && !newPasswordConf.equals(newPassword)) {
                            out.println("alert('Error: new password don`t match other data updated');");
                        }else{
                            out.println("alert('All data updated');");
                        }
                    }

                    userService.update(userFromDB);
                    userFromDB.setPassword("");
                    session.setAttribute("user", userFromDB);
//                    response.sendRedirect("/home");
                }
            }
        }else {
            out.println("alert('Error fail: invalid input data');");
//            response.sendRedirect("/home");
        }
        out.println("location='/home';");
        out.println("</script>");
    }
}
