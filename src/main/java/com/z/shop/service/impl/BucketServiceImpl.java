package com.z.shop.service.impl;

import com.z.shop.dao.OrderDAO;
import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.entity.Order;
import com.z.shop.service.BucketService;

import java.util.List;

public class BucketServiceImpl implements BucketService {

    public static BucketService bucketServiceImpl;

    private OrderDAO orderDAO;

    private BucketServiceImpl() {
            orderDAO = new BucketDaoImpl();
    }

    public static BucketService getBucketService(){
        if( bucketServiceImpl == null)
            bucketServiceImpl = new BucketServiceImpl();
        return bucketServiceImpl;
    }


    @Override
    public Order create(Order order) {
        return orderDAO.create(order);
    }

    @Override
    public Order read(Integer id) {
        return orderDAO.read(id);
    }

    @Override
    public Order update(Order order) {
        return orderDAO.update(order);
    }

    @Override
    public void delete(Integer id) {
        orderDAO.delete(id);
    }

    @Override
    public List<Order> readAll() {
        return orderDAO.readAll();
    }
}
