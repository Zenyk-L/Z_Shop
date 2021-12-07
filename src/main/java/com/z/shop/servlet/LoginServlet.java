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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private UserService userService = UserServiceImpl.getUserService();
    private static BucketService bucketService = BucketServiceImpl.getBucketService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Login servlet checking entered login and password.
         * And synchronize bucket from DB and bucket from session.
         * If some products was added before authorization they will be added to logged user bucket.
         * Admin couldn't have product bucket.
         * If without authorization in session bucket are present products, with admin login bucket will be cleared.
         * */
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String success = null;
        if (!email.isEmpty() && email != null && !password.isEmpty() && password != null) {
            User user = userService.getUserByEmail(email);

            if (user != null && !user.getPassword().equals(password)) {
                success = "wrong password";
            }else
            if (user != null && user.getPassword().equals(password)) {
                user.setPassword("");

                User userFromSession = (User) session.getAttribute("user");
                if (userFromSession != null && !user.getEmail().equals(userFromSession.getEmail())){

                    if (session != null) {
                        session.invalidate();
                    }

                }
                session.setAttribute("user", user);
                success = "success";



                if(user.getRole().toString().equals("USER")) {

                    /**
                     * If some products was added before authorization they will be added to logged user bucket.
                     * if some product already present in user bucket them quantity will be increased.
                     * */

                    List<Bucket> userBucketsFromSession = (List<Bucket>) session.getAttribute("buckets");
                    List<Bucket> userBucketsFromDB = bucketService.findByUserIdReserved(user.getId());
                    if (userBucketsFromSession != null) {
                        Iterator<Bucket> iterator = userBucketsFromSession.iterator();
                        while (iterator.hasNext()) {
                            Bucket bucket = iterator.next();
                            Bucket bucketFromDB = userBucketsFromDB.stream().filter(b -> b.getProductId().equals(bucket.getProductId())).findFirst().orElse(null);
                            if(bucketFromDB == null) {
                                bucket.setUserId(user.getId());
                                bucketService.create(bucket);
                            } else{
                                int newQuantity = bucketFromDB.getQuantity() + bucket.getQuantity();
                                bucketFromDB.setQuantity( newQuantity);
                                bucketService.update(bucketFromDB);
                            }
                        }
                    }

                    userBucketsFromSession = bucketService.findByUserIdReserved(user.getId());
                    session.setAttribute("buckets", userBucketsFromSession);
                }else {
                    /**
                     * Clearing session bucket for admin
                     * */
                    List<Bucket> cleanBucketForAdmin = new ArrayList<>();
                    session.setAttribute("buckets", cleanBucketForAdmin);
                }
            }else {
                success = "user doesnt exist";
            }
        } else {
            success = "invalid input";
        }

        /**
         * showing alert with mistake in inputted data
         * */

        session.setAttribute("success", success);
        LOGGER.info("User " + email + " logged in");
        if(success != null && !success.equals("success")) {
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Login fail:" + success +"');");
            out.println("location='/home';");
            out.println("</script>");
        }else {

            response.sendRedirect("/home");
        }
    }
}
