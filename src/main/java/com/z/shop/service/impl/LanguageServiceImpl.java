package com.z.shop.service.impl;

import com.z.shop.dao.CategoryDAO;
import com.z.shop.dao.LanguageDAO;
import com.z.shop.dao.impl.CategoryDAOImpl;
import com.z.shop.dao.impl.LanguageDAOImpl;
import com.z.shop.entity.Language;
import com.z.shop.service.CategoryService;
import com.z.shop.service.LanguageService;

import java.util.List;

public class LanguageServiceImpl implements LanguageService {

    private static LanguageService languageServiceImpl;

    private LanguageDAO languageDAO = null;

    private LanguageServiceImpl(){
        languageDAO = new LanguageDAOImpl();
    }

    public static LanguageService getLanguageServiceImpl() {
        if(languageServiceImpl == null){
            languageServiceImpl= new LanguageServiceImpl();
        }
        return languageServiceImpl;
    }


    @Override
    public Language create(Language language) {
        return languageDAO.create(language);
    }

    @Override
    public Language read(Integer id) {
        return languageDAO.read(id);
    }

    @Override
    public Language update(Language language) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Language> readAll() {
        return languageDAO.readAll();
    }
}
