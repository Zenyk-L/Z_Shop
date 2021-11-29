package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.User;
import com.z.shop.service.BucketService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.BucketServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "BuyProductServlet", value = "/buyProduct")
public class BuyProductServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(BuyProductServlet.class);
    private BucketService bucketService = BucketServiceImpl.getBucketService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // one bucket item to paid status

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            List<Bucket> buckets = (List<Bucket>) session.getAttribute("buckets");
            Integer bucketId = Integer.valueOf(request.getParameter("bucketId"));
            Bucket bucket = buckets.stream().filter(b -> b.getId().equals(bucketId)).findFirst().get();
            buckets.remove(bucket);
            bucket.setStatus("paid");
            bucket.setPurchaseDate(new Date());
            bucketService.update(bucket);
        }
        response.sendRedirect("/bucket");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
