package com.z.shop.servlet;

import com.z.shop.entity.Category;
import com.z.shop.entity.Product;
import com.z.shop.service.CategoryService;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.CategoryServiceImpl;
import com.z.shop.service.impl.ProductServiceImpl;
import com.z.shop.utils.PaginationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Home", urlPatterns = {"/home"}, loadOnStartup = 10)
public class GetAllProductsServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(GetAllProductsServlet.class);

    private final ProductService productService = ProductServiceImpl.getProductService();
    private final CategoryService categoryService = CategoryServiceImpl.getCategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Creating data for main page.
         * Search products by name, if no matches return all products.
         * Sort by category, name, price.
         * Split data to pages, not more than 8 product on page.
         * */

        List<Product> products = null;
        /**
         * searching products by name, if no matches return all products
         * */

        String searchingText = request.getParameter("searchText");
        if ( searchingText == null){
            products = productService.readAll();
        }else{
            request.setAttribute("searchingText", searchingText);
            products = productService.findByName(searchingText);
            if(products.isEmpty()){
                products = productService.readAll();
            }
        }
      /** sort by category     */
        String filterByCategory = request.getParameter("sortByCategory");

        if(filterByCategory != null && !"default".equals(filterByCategory) ) {
            request.setAttribute("sortByCategory", filterByCategory);
            products = ProductServiceImpl.filterByCategory(products,filterByCategory);
        }

        /** sort by name */
        String sortByNameMarker = request.getParameter("sortByName");
        products = ProductServiceImpl.sortByName(products,sortByNameMarker);


        /** sort by price  */

        String sortByPriceMarker = request.getParameter("sortByPrice");
        products = ProductServiceImpl.sortByPrice(products,sortByPriceMarker);

        request.setAttribute("sortByName", sortByNameMarker);
        request.setAttribute("sortByPrice", sortByPriceMarker);

        List<Category> categories = categoryService.readAll();
        request.setAttribute("categories", categories);
        
        /**
         * pagination
         * */

        String currentPageFromJsp = request.getParameter("page");
        PaginationPage paginationPage = PaginationPage.getPageByNumber(products,currentPageFromJsp);

        int totalPages = paginationPage.getTotalPageQuantity();
        int currentPage = paginationPage.getCurrentPage();
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", currentPage);

        List <Product> productsToShowOnPage = paginationPage.getProductList();
        request.setAttribute("products", productsToShowOnPage);


        request.getRequestDispatcher("home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
