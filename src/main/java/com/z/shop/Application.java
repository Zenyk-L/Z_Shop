package com.z.shop;

import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.dao.impl.ProductDaoImpl;
import com.z.shop.dao.impl.UserDaoImpl;
import com.z.shop.entity.Bucket;
import com.z.shop.entity.Product;
import com.z.shop.entity.User;
import com.z.shop.entity.UserRole;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.ProductServiceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class Application {
    BucketDaoImpl bucketDao = new BucketDaoImpl();

    public static void main(String[] args) throws SQLException {
//        User user = new User();
//        UserDaoImpl userDaoImpl = new UserDaoImpl();

//        for (int i = 0; i < 10; i++) {
//            user.setEmail("User" + i + "Email");
//            user.setName("Name" + i);
//            user.setLastName("LastName" + i);
//            user.setPassword("UserPassword" + i);
//            user.setRole(UserRole.USER);
//            user.setAmount(99.99);
//            userDaoImpl.create(user);
//        }
//        user.setEmail("UserOneEmail");
//        user.setName("OneName");
//        user.setLastName("OneLastName");
//        user.setPassword("UserPassword");
//        user.setRole(UserRole.USER);
//        user.setAmount(99.99);
//
//        User user2 = new User();
//        user2.setEmail("UserTwoEmail");
//        user2.setName("TwoName");
//        user2.setLastName("TwoLastName");
//        user2.setPassword("UserTwoPassword");
//        user2.setRole(UserRole.valueOf("ADMIN"));
//        user2.setAmount(9999.99);

//        userDaoImpl.create(user2);


//        System.out.println(userDaoImpl.readAll());
//        System.out.println("=================================");
//        System.out.println(userDaoImpl.read(2));
//        User user2 = new User();
//        user2.setId(2);
//        user2.setEmail("UserTwoEmail"+" Updated");
//        user2.setName("TwoName"+" Updated");
//        user2.setLastName("TwoLastName"+" Updated");
//        user2.setPassword("UserTwoPassword"+" Updated");
//        user2.setRole(UserRole.valueOf("USER"));
//        user2.setAmount(100.00);
//        userDaoImpl.update(user2);
//        System.out.println("=================================");
//        System.out.println(userDaoImpl.readAll());
//        System.out.println("=================================");
//
//        userDaoImpl.delete(10);
//        System.out.println(userDaoImpl.getUserByEmail("User4Email"));

//            name, image, category, description, color, scale, price, adding_date
//        ProductDaoImpl productDaoImpl = new ProductDaoImpl();
//        Product product = new Product();
//        for (int i = 0; i < 10; i++) {
//
//            product.setName("Name"+i);
//            product.setImage("Image"+i);
//            product.setCategory("Category"+i);
//            product.setDescription("Description"+i);
//            product.setColor("Color"+i);
//            product.setScale("Scale"+i);
//            product.setPrice(999.99);
//            product.setAddingDate(new Date());

//           productDaoImpl.create(product);
//        }
//        System.out.println(productDaoImpl.readAll());
//        System.out.println("=================================");
//        System.out.println(productDaoImpl.read(3));
//        System.out.println("=================================");
//        product.setId(3);
//        product.setName("Name"+" Updated");
//        product.setImage("Image"+" Updated");
//        product.setCategory("Category"+" Updated");
//        product.setDescription("Description"+" Updated");
//        product.setColor("Color"+" Updated");
//        product.setScale("Scale"+" Updated");
//        product.setPrice(10000d);
//        product.setAddingDate(new Date());
//        productDaoImpl.update(product);
//        System.out.println("=================================");
//        productDaoImpl.delete(10);
//        System.out.println(productDaoImpl.readAll());
//        System.out.println("=================================");
//    }
//        BucketDaoImpl bucketDao = new BucketDaoImpl();
//        Bucket bucket = new Bucket();
////        for (int i = 0; i < 10; i++) {
//
//        bucket.setUserId(1);
//        bucket.setProductId(1);
//        bucket.setPurchaseDate(new Date());
//        bucketDao.create(bucket);
//
//        bucket.setUserId(1);
//        bucket.setProductId(2);
//        bucket.setPurchaseDate(new Date());
//        bucketDao.create(bucket);
//
//        bucket.setUserId(1);
//        bucket.setProductId(3);
//        bucket.setPurchaseDate(new Date());
//        bucketDao.create(bucket);
//
//        bucket.setUserId(5);
//        bucket.setProductId(1);
//        bucket.setPurchaseDate(new Date());
//        bucketDao.create(bucket);
//
//        bucket.setUserId(5);
//        bucket.setProductId(2);
//        bucket.setPurchaseDate(new Date());
//        bucketDao.create(bucket);
//
//        bucket.setUserId(5);
//        bucket.setProductId(3);
//        bucket.setPurchaseDate(new Date());
//        bucketDao.create(bucket);
//
//        System.out.println(bucketDao.read(1));
//        System.out.println("=================================");
//        bucketDao.delete(2);
//        System.out.println(bucketDao.readAll());
//           productDaoImpl.create(product);
//        }
        ProductService productService = ProductServiceImpl.getProductService();
        Map<Integer, Product> integerProductMap = productService.readAllMap();

        System.out.println(integerProductMap.get(3));
        productService.delete(3);
        System.out.println(productService.readAllMap());

    }
}
