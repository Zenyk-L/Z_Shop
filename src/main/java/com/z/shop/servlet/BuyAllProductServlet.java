package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.User;
import com.z.shop.service.BucketService;
import com.z.shop.service.impl.BucketServiceImpl;
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

@WebServlet(name = "BuyAllProductServlet", value = "/buyAllProduct")
public class BuyAllProductServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(BuyAllProductServlet.class);
    private BucketService bucketService = BucketServiceImpl.getBucketService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // all bucket item to paid status
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            List<Bucket> buckets = (List<Bucket>) session.getAttribute("buckets");
            Iterator<Bucket> iterator = buckets.iterator();
            while (iterator.hasNext()){
                Bucket bucket = iterator.next();
                bucket.setStatus("paid");
                bucketService.update(bucket);
                iterator.remove();

            }
        }
        response.sendRedirect("/home");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
