package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.Product;
import com.z.shop.entity.User;
import com.z.shop.service.BucketService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.BucketServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BuyHistoryServlet", value = "/buyHistory")
public class BuyHistoryServlet extends HttpServlet {
    private BucketService bucketService = BucketServiceImpl.getBucketService();
    private static ProductService productService = ProductServiceImpl.getProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * For user show him own list of purchased products.
         * Get from DB for current user all bucket records with status "paid"
         * */

        Map<Integer, Product> productMap = productService.readAllMapWithDeleted();
        request.setAttribute("productMap", productMap);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Bucket> userBucketPaid = bucketService.findByUserIdPaid(user.getId());

                request.setAttribute("buckets", userBucketPaid);
        }

        request.getRequestDispatcher("bucketHistory.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
