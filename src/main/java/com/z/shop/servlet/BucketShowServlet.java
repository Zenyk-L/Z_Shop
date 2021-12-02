package com.z.shop.servlet;

import com.z.shop.entity.Bucket;
import com.z.shop.entity.Language;
import com.z.shop.entity.Product;
import com.z.shop.entity.User;
import com.z.shop.service.BucketService;
import com.z.shop.service.LanguageService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.BucketServiceImpl;
import com.z.shop.service.impl.LanguageServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BucketShowServlet", value = "/bucket")
public class BucketShowServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(BucketShowServlet.class);
    private static BucketService bucketService = BucketServiceImpl.getBucketService();
    private static ProductService productService = ProductServiceImpl.getProductService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<Integer, Product> productMap = productService.readAllMap();
        request.setAttribute("productMap", productMap);

        HttpSession session = request.getSession();
        List<Bucket> buckets = (List<Bucket>) session.getAttribute("buckets");

        Double subtotal = 0.0;
        if (buckets != null) {
            Iterator<Bucket> iterator = buckets.iterator();
            while (iterator.hasNext()) {
                Bucket bucket = iterator.next();
                Double price = productMap.get(bucket.getProductId()).getPrice();
                subtotal += bucket.getQuantity() * price;
            }
        }
        subtotal = Math.round(subtotal * 100.0) / 100.0;
        request.setAttribute("subtotal", subtotal);
        request.getRequestDispatcher("bucket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Bucket> buckets = (List<Bucket>) session.getAttribute("buckets");
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Integer quantity = Integer.valueOf(request.getParameter("quantity"));

        Bucket bucket = new Bucket();
        if (buckets != null) {

            Product product = productService.read(productId);
            if(product != null && product.getQuantity() >= quantity) {

                bucket = buckets.stream().filter(b -> b.getProductId().equals(productId)).findFirst().get();
                bucket.setQuantity(quantity);
//                int newProductQuantity = product.getQuantity() - quantity;
//                product.setQuantity(newProductQuantity);
//                productService.update(product);
            }
        }

        User userFromSession = (User) session.getAttribute("user");
        if (userFromSession != null) {
            bucketService.update(bucket);
        }
        session.setAttribute("buckets", buckets);
        response.sendRedirect("/bucket");
    }
}
