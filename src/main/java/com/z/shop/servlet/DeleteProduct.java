package com.z.shop.servlet;

import com.z.shop.service.ProductService;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/deleteProduct")
public class DeleteProduct extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeleteProduct.class);
    private ProductService productService = ProductServiceImpl.getProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        productService.delete(Integer.valueOf(productId));
        LOGGER.info("Product id = " + productId + " delete.");
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
