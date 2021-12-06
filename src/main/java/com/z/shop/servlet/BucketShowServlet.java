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
import java.util.ArrayList;
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

        /**
         * synchronize session and DB buckets excluding doubling product for current user
         * (delete from session bucket absent in DB bucket product and
         * add from DB bucket to session bucket absent product)
         * counting total price of all bucket
         * */

        Map<Integer, Product> productMap = productService.readAllMap();
        request.setAttribute("productMap", productMap);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Bucket> bucketsFromSession = (List<Bucket>) session.getAttribute("buckets");
        if (user != null){
            List<Bucket> bucketsFromDB = bucketService.findByUserIdReserved(user.getId());
            if (bucketsFromDB == null || bucketsFromDB.isEmpty()) {
                bucketsFromSession = new ArrayList<>();
            }
            if(!bucketsFromSession.isEmpty() && !bucketsFromDB.isEmpty()){

                if(bucketsFromSession.size()> bucketsFromDB.size()) {
                    Iterator<Bucket> iterator = bucketsFromSession.iterator();
                    while (iterator.hasNext()) {
                        Bucket bucketFromSession = iterator.next();
                        if (!bucketsFromDB.contains(bucketFromSession)) {
                            iterator.remove();
                        }
                    }
                }

                if(bucketsFromSession.size() <= bucketsFromDB.size()) {
                    Iterator<Bucket> iterator = bucketsFromDB.iterator();
                    while (iterator.hasNext()) {
                        Bucket bucketFromDB = iterator.next();
                        if(bucketsFromSession.contains(bucketFromDB)){
                            bucketsFromSession.replaceAll(bucket -> bucket.getId().equals(bucketFromDB.getId())?bucketFromDB:bucket);
                        }else{
                            bucketsFromSession.add(bucketFromDB);
                        }
                    }
                }

            }
        }


        Double subtotal = 0.0;
        if (bucketsFromSession != null) {
            Iterator<Bucket> iterator = bucketsFromSession.iterator();
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

        /**
         * Change in bucket (in session and DB) ordered product quantity by product id
         * */

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
