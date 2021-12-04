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
import java.util.stream.Collectors;

@WebServlet(name = "RemoveFromBucketServlet", value = "/removeFromBucket")
public class RemoveFromBucketServlet extends HttpServlet {
    private static BucketService bucketService = BucketServiceImpl.getBucketService();
    private static ProductService productService = ProductServiceImpl.getProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer productId = Integer.valueOf(request.getParameter("productId"));
        List<Bucket> buckets = (List<Bucket>) session.getAttribute("buckets");


        User user = (User) session.getAttribute("user");
        System.out.println(user);
        String bucketIdFromRequest = request.getParameter("bucketId");
        System.out.println(bucketIdFromRequest);
        if(bucketIdFromRequest != null && !bucketIdFromRequest.isEmpty()){
            System.out.println("inside == null");
            Integer bucketId = Integer.valueOf(bucketIdFromRequest);
            System.out.println( user.getRole().toString().equals("ADMIN"));
            if (user != null && user.getRole().toString().equals("ADMIN")){
                bucketService.delete(bucketId);
                System.out.println("Admin bucket");
                response.sendRedirect("/showAllBuckets");
            }
            if (user != null && user.getRole().toString().equals("USER")){
                Bucket bucket = bucketService.read(bucketId);
                if (bucket.getUserId().equals(user.getId())) {
                    bucketService.delete(bucketId);
                    buckets = buckets.stream().filter(buck -> buck.getId().intValue() != bucketId.intValue()).collect(Collectors.toList());
                    session.setAttribute("buckets", buckets);
                    response.sendRedirect("/bucket");
                }
            }

        }else {
            buckets = buckets.stream().filter(buck -> buck.getProductId().intValue() != productId.intValue()).collect(Collectors.toList());
            session.setAttribute("buckets", buckets);
            response.sendRedirect("/bucket");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
