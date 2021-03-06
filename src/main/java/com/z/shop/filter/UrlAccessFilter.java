package com.z.shop.filter;


import com.z.shop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class UrlAccessFilter implements Filter {

    /**
     * Filter access by servlet names according to roles
     * */

    private static final Logger LOGGER = LogManager.getLogger(UrlAccessFilter.class);

    Map<String, List<String>> accessMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        /**
         * Create map <role, access servlet name>
         * */
        List<String>  servletNameAccessAdmin = Arrays.asList("LanguageSwitcherServlet","CreateProduct", "DeleteProduct", "EditProduct", "EditUserServlet",
                "Home", "LogoutServlet" , "RemoveFromBucketServlet", "ShowAllBucketsServlet","default");

        List<String>  servletNameAccessUser = Arrays.asList("LanguageSwitcherServlet","BucketServlet", "BucketShowServlet", "BuyAllProductServlet", "BuyHistoryServlet", "BuyProductServlet", "EditUserServlet",
                "Home", "LogoutServlet", "RemoveFromBucketServlet", "default");

        List<String>  servletUrlAccessDefault = Arrays.asList("LanguageSwitcherServlet","BucketServlet", "BucketShowServlet", "Home", "LoginServlet", "RegistrationServlet", "RemoveFromBucketServlet","default");

        accessMap.put("DEFAULT",servletUrlAccessDefault);
        accessMap.put("USER",servletNameAccessUser);
        accessMap.put("ADMIN",servletNameAccessAdmin);

        LOGGER.trace("URL filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.trace("URL filter get down to work");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getHttpServletMapping().getServletName();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        String roleToAccess = "DEFAULT";
        if (user != null){
            roleToAccess = user.getRole().toString();
        }

        if (accessMap.get(roleToAccess).contains(requestURI)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            response.sendRedirect("/home");
        }

    }

    @Override
    public void destroy() {

    }
}