package com.z.shop.service.impl;

import com.z.shop.dao.BucketDao;
import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.entity.Bucket;
import com.z.shop.service.BucketService;

import java.util.List;

public class BucketServiceImpl implements BucketService {

    public static BucketService bucketServiceImpl;

    private BucketDao bucketDao;

    private BucketServiceImpl() {
            bucketDao = new BucketDaoImpl();
    }

    public static BucketService getBucketService(){
        if( bucketServiceImpl == null)
            bucketServiceImpl = new BucketServiceImpl();
        return bucketServiceImpl;
    }


    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket read(Integer id) {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
