package com.z.shop.servlet;

import com.z.shop.entity.Language;
import com.z.shop.service.LanguageService;
import com.z.shop.service.impl.LanguageServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "LanguageSwitcherServlet", value = "/languageSwitcherServlet", loadOnStartup = 1)
public class LanguageSwitcherServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LanguageSwitcherServlet.class);
    private final LanguageService languageService = LanguageServiceImpl.getLanguageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Set localization language.
         * */
        String lang = request.getParameter("lang");
        HttpSession session = request.getSession();
        if(session.getAttribute("lang") == null){
            session.setAttribute("lang", "en");
        }

        if (lang != null){
            session.setAttribute("lang", lang);
        }

        List<Language> languages = languageService.readAll();
        session.setAttribute("languages", languages);

        LOGGER.trace("servlet session lang after settings = " + session.getAttribute("lang"));
        request.getRequestDispatcher("/home").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
