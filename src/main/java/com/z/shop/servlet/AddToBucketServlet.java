package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.Product;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "BucketServlet", value = "/addToBucket")
public class AddToBucketServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(AddToBucketServlet.class);
    private static BucketService bucketService = BucketServiceImpl.getBucketService();
    private static ProductService productService = ProductServiceImpl.getProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        List<Bucket> buckets = (List<Bucket>) session.getAttribute("buckets");
        if (buckets == null) {
            buckets = new ArrayList<Bucket>();
        }

        Optional<Bucket> optionalBucket = buckets.stream().filter(b -> b.getProductId().equals(productId)).findFirst();
        Bucket bucket = null;
        if (optionalBucket.isPresent()){
            bucket = optionalBucket.get();
            Product product = productService.read(productId);
            if (product.getQuantity() > bucket.getQuantity()) {
                bucket.setQuantity(bucket.getQuantity() + 1);
                bucket.setPurchaseDate(new Date());
            }
        } else {
            bucket = new Bucket();
            bucket.setProductId(productId);
            bucket.setQuantity(1);
            bucket.setPurchaseDate(new Date());
            bucket.setStatus("reserved");
            buckets.add(bucket);
        }

        User user = (User) session.getAttribute("user");
        if(user != null){
            bucket.setUserId(user.getId());
            bucketService.create(bucket);
            LOGGER.info("Product with id : " + productId + " added to DB bucket for user " + user.getEmail());

        }

        session.setAttribute("buckets", buckets);
        LOGGER.info("Product with id : " + productId + " added to session bucket ");
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
