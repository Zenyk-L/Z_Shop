package com.z.shop.servlet;

import com.z.shop.entity.Product;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.ProductServiceImpl;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Home", urlPatterns = {"/home"}, loadOnStartup = 10)
public class GetAllProducts extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(GetAllProducts.class);

    ProductService productService = ProductServiceImpl.getProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = productService.readAll();

        request.setAttribute("products", products);
        LOGGER.info("All product getted from DB ");
        request.getRequestDispatcher("home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
