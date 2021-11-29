package com.z.shop.service.impl;

import com.z.shop.dao.BucketDAO;
import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.entity.Bucket;
import com.z.shop.service.BucketService;

import java.util.List;
import java.util.stream.Collectors;

public class BucketServiceImpl implements BucketService {

    public static BucketService bucketServiceImpl;

    private BucketDAO orderDAO;

    private BucketServiceImpl() {
            orderDAO = new BucketDaoImpl();
    }

    public static BucketService getBucketService(){
        if( bucketServiceImpl == null)
            bucketServiceImpl = new BucketServiceImpl();
        return bucketServiceImpl;
    }


    @Override
    public Bucket create(Bucket bucket) {
        return orderDAO.create(bucket);
    }

    @Override
    public Bucket read(Integer id) {
        return orderDAO.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return orderDAO.update(bucket);
    }

    @Override
    public void delete(Integer id) {
        orderDAO.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return orderDAO.readAll();
    }

    @Override
    public List<Bucket> findByUserId(Integer userId) {
        return  readAll().stream().filter(bucket -> bucket.getUserId().equals(userId)).collect(Collectors.toList());
    }
    @Override
    public List<Bucket> findByUserIdReserved(Integer userId) {
        return  readAll().stream().filter(bucket -> bucket.getUserId().equals(userId) && bucket.getStatus().equals("reserved")).collect(Collectors.toList());
    }

    @Override
    public List<Bucket> findByUserIdPaid(Integer userId) {
        return  readAll().stream().filter(bucket -> bucket.getUserId().equals(userId) && bucket.getStatus().equals("paid")).collect(Collectors.toList());
    }
}
