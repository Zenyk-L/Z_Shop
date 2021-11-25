package com.z.shop.servlet;

import com.z.shop.entity.Category;
import com.z.shop.entity.Language;
import com.z.shop.entity.Product;
import com.z.shop.service.CategoryService;
import com.z.shop.service.LanguageService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.CategoryServiceImpl;
import com.z.shop.service.impl.LanguageServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Home", urlPatterns = {"/home"}, loadOnStartup = 10)
public class GetAllProductsServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(GetAllProductsServlet.class);

    private ProductService productService = ProductServiceImpl.getProductService();
    private CategoryService categoryService = CategoryServiceImpl.getCategoryServiceImpl();
    private LanguageService languageService = LanguageServiceImpl.getLanguageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String lang = request.getParameter("lang");
        HttpSession session = request.getSession();
//        if(session.getAttribute("lang") == null){
//            session.setAttribute("lang", "en");
//        };
        if (lang != null){
            session.setAttribute("lang", lang);
        }
        LOGGER.trace("servlet session lang after settings = " + session.getAttribute("lang"));

        List<Product> products = null;
//        searching products by name, if no matches return all products
        String searchingText = request.getParameter("searchText");
        if ( searchingText == null){
            products = productService.readAll();
        }else{
            products = productService.findByName(searchingText);
            if(products.size() == 0){
                products = productService.readAll();
            }
        }
//        sort by category
        String sortByCategory = request.getParameter("sortByCategory");

        if(sortByCategory != null && !"default".equals(sortByCategory) ) {

            products = products.stream().filter(product -> product.getCategory().getId().equals(Integer.valueOf(sortByCategory))).collect(Collectors.toList());
        }

        //        sort by name
        String sortByName = request.getParameter("sortByName");
        if("UP".equals(sortByName)) {
            products = products.stream().sorted((product1,product2)->product1.getName().compareTo(product2.getName())).collect(Collectors.toList());
        }
        if("DOWN".equals(sortByName)) {
            products = products.stream().sorted((product1,product2)->product2.getName().compareTo(product1.getName())).collect(Collectors.toList());
        }

        //        sort by price
        String sortByPrice = request.getParameter("sortByPrice");
        if("UP".equals(sortByPrice)) {
            products = products.stream().sorted((product1,product2)->product1.getPrice().compareTo(product2.getPrice())).collect(Collectors.toList());
        }
        if("DOWN".equals(sortByPrice)) {
            products = products.stream().sorted((product1,product2)->product2.getPrice().compareTo(product1.getPrice())).collect(Collectors.toList());
        }


        request.setAttribute("searchingText", searchingText);

        List<Category> categories = categoryService.readAll();
        List<Language> languages = languageService.readAll();
        request.setAttribute("languages", languages);
        request.setAttribute("categories", categories);



        request.setAttribute("products", products);
        LOGGER.info("All product getted from DB ");
        request.getRequestDispatcher("home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}