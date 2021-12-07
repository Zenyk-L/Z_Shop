package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DeleteProduct", value = "/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeleteProductServlet.class);
    private ProductService productService = ProductServiceImpl.getProductService();
    private static BucketService bucketService = BucketServiceImpl.getBucketService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Delete product by ID.
         * Delete from all bucket records in DB where this product is present as "reserved".
         * But for buying history product will be available.
         * */

        String productId = request.getParameter("productId");
        if(productId != null) {
            productService.delete(Integer.valueOf(productId));
            List<Bucket> collect = bucketService.readAll().stream().filter(bucket -> bucket.getProductId().equals(Integer.valueOf(productId)) && bucket.getStatus().equals("reserved")).collect(Collectors.toList());

            collect.stream().forEach(b -> bucketService.delete(b.getId()));
            LOGGER.info("Product id = " + productId + " delete.");
        }
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
