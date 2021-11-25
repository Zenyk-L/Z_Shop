package com.z.shop;

import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.entity.Bucket;
import com.z.shop.entity.Category;
import com.z.shop.service.BucketService;
import com.z.shop.service.CategoryService;
import com.z.shop.service.impl.BucketServiceImpl;
import com.z.shop.service.impl.CategoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    BucketDaoImpl bucketDao = new BucketDaoImpl();

    public static void main(String[] args) throws SQLException {

        BucketService bucketService = BucketServiceImpl.getBucketService();
//        Bucket bucket = new Bucket();
//        bucket.setUserId(3);
//        bucket.setProductId(2);
//        bucket.setQuantity(3);
//        bucket.setPurchaseDate(new Date());
//        bucket.setStatus("reserved");
//        bucket.setDeleted(true);
////        bucketService.create(bucket);
////        bucketService.delete(2);
////
////        System.out.println(bucket);
//        System.out.println(bucketService.readAll());
//        bucket = bucketService.read(2);
//        bucket.setUserId(1);
//        bucket.setProductId(1);
//        bucket.setQuantity(1);
//        bucket.setPurchaseDate(new Date());
//        bucket.setStatus("canceled");
//        bucket.setDeleted(true);

        List<Bucket> buckets = bucketService.findByUserIdReserved(1);
        System.out.println(buckets);

    }
}
