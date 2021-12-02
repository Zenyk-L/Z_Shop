package com.z.shop.filter;


import com.sun.deploy.net.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class UrlFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(UrlFilter.class);
    private List<String> servletUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletUrl = Arrays.asList("BucketServlet", "BucketShowServlet", "BuyAllProductServlet", "BuyHistoryServlet", "BuyProductServlet", "CreateProduct",
                "DeleteProduct", "EditProduct", "EditUserServlet", "Home", "LoginServlet", "LogoutServlet", "RegistrationServlet", "RemoveFromBucketServlet", "default");
        LOGGER.trace("URL filter init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.trace("URL filter get down to work");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getHttpServletMapping().getServletName();

        if (servletUrl.contains(requestURI)){
            filterChain.doFilter(servletRequest, servletResponse);

        }else {
            response.sendRedirect("/home");
        }



    }

    @Override
    public void destroy() {

    }
}