package com.z.shop.service.impl;

import com.z.shop.dao.ProductDao;
import com.z.shop.dao.impl.ProductDaoImpl;
import com.z.shop.entity.Product;
import com.z.shop.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private  static ProductService productServiceImpl;

    private ProductDao productDao;

    private ProductServiceImpl() {
            productDao = new ProductDaoImpl();
    }

    public static ProductService getProductService(){
        if (productServiceImpl == null )
            productServiceImpl = new ProductServiceImpl();
        return productServiceImpl;
    }

    @Override
    public Product create(Product product) {

        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) {
        return productDao.read(id);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDao.readAll();
    }

    @Override
    public Map<Integer, Product> readAllMap() {
        return readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    @Override
    public Map<Integer, Product> readAllMapWithDeleted() {
        return readAllWithDeleted().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }

//    @Override
//    public List<Product> findByName(String searchingName) {
//        return readAll().stream().filter(product -> product.getName().equalsIgnoreCase(searchingName)).collect(Collectors.toList());
//    }
    @Override
    public List<Product> findByName(String searchingName) {
        return readAll().stream().filter(product -> product.getName().toLowerCase().matches("(.*)"+searchingName.toLowerCase()+"(.*)")).collect(Collectors.toList());
    }

    @Override
    public List<Product> readAllWithDeleted(){
        return productDao.readAllWithDeleted();
    }
}
