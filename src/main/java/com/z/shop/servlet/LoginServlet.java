package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.BucketService;
import com.z.shop.service.UserService;
import com.z.shop.service.impl.BucketServiceImpl;
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
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private UserService userService = UserServiceImpl.getUserService();
    private static BucketService bucketService = BucketServiceImpl.getBucketService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String success = null;
        if (!email.isEmpty() && email != null) {
            User user = userService.getUserByEmail(email);

            if (user.getId() != null && user.getPassword().equals(password)) {
                user.setPassword("");

                User userFromSession = (User) session.getAttribute("user");
                if (userFromSession != null && !user.getEmail().equals(userFromSession.getEmail())){

                    if (session != null) {
                        session.invalidate();
                    }

                }
                session.setAttribute("user", user);
                success = "success";
                System.out.println((List<Bucket>) session.getAttribute("buckets"));
                List<Bucket> userBuckets = (List<Bucket>) session.getAttribute("buckets");
                System.out.println(userBuckets);
                if (userBuckets != null) {
                    Iterator<Bucket> iterator = userBuckets.iterator();
                    while (iterator.hasNext()) {
                        Bucket bucket = iterator.next();
                        bucket.setUserId(user.getId());
                        bucketService.create(bucket);
                    }
                }

                userBuckets = bucketService.findByUserIdReserved(user.getId());
                session.setAttribute("buckets", userBuckets);
            }

        } else {
            success = "invalid input";
        }

        session.setAttribute("success", success);
        LOGGER.info("User " + email + " logged in");
        response.sendRedirect("/home");

    }
}
