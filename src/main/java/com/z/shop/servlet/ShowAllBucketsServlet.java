package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.Product;
import com.z.shop.entity.User;
import com.z.shop.service.BucketService;
import com.z.shop.service.ProductService;
import com.z.shop.service.UserService;
import com.z.shop.service.impl.BucketServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import com.z.shop.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet(name = "ShowAllBucketsServlet", value = "/showAllBuckets")
public class ShowAllBucketsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ShowAllBucketsServlet.class);
    private static BucketService bucketService = BucketServiceImpl.getBucketService();
    private static ProductService productService = ProductServiceImpl.getProductService();
    private static UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Preparing data to show Admin all buckets of all users.
         * */

        List<Bucket> buckets = bucketService.readAll();
        buckets = buckets.stream().sorted((b1,b2)-> b2.getStatus().compareTo(b1.getStatus())).collect(Collectors.toList());
        request.setAttribute("buckets", buckets);
        Map<Integer, Product> productMap = productService.readAllMapWithDeleted();
        request.setAttribute("productMap", productMap);
        Map<Integer, String> userMap = userService.readAll().stream().collect(Collectors.toMap(User::getId, User::getEmail));

        request.setAttribute("userMap", userMap);

        request.getRequestDispatcher("adminBucket.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
