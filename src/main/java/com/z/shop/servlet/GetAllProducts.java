package com.z.shop.servlet;

import com.z.shop.entity.Product;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Home", urlPatterns = {"/home"}, loadOnStartup = 10)
public class GetAllProducts extends HttpServlet {


    ProductService productService = ProductServiceImpl.getProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = productService.readAll();

        System.out.println(products);
        request.setAttribute("products", products);
        request.getRequestDispatcher("home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
