package com.z.shop;

import com.z.shop.dao.impl.BucketDaoImpl;
import com.z.shop.entity.Product;
import com.z.shop.service.ProductService;
import com.z.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Date;
import java.sql.SQLException;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    BucketDaoImpl bucketDao = new BucketDaoImpl();

    public static void main(String[] args) throws SQLException {
        ProductService productService = ProductServiceImpl.getProductService();
        System.out.println(productService.readAll());
        Product product = new Product(1,"авто", "bmv.jpg", "Car", 1, "спорт авто", "red", "big", 9999.99, new Date(new java.util.Date().getTime()), false);

        productService.create(product);

        System.out.println(productService.readAll());

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
//        ProductDaoImpl productDao = new ProductDaoImpl();

//        productDao.createTest();
//            name, image, category, description, color, scale, price, adding_date
//        ProductDaoImpl productDao = new ProductDaoImpl();
//        ProductService productService = ProductServiceImpl.getProductService();
//////        System.out.println(productService.readAll());
//        Product product = new Product();
//        product.setName("Test_Create");
//        product.setImage("Test Create.jpg");
//        product.setCategory("Car");
//
//        product.setQuantity(99);
//        product.setDescription("Test Description");
//        product.setColor("test Green");
//        product.setScale("test Large");
//        product.setPrice(999.99);
//        product.setAddingDate(new Date());
//
//        System.out.println(productService.readAll());
////        productService.create(product);
////        System.out.println("Read 6" + productService.read(6));
//
//        product.setId(1);
//        product.setName("NEW Test_Create");
//        product.setImage("NEW Test Create.jpg");
//        product.setCategory("Phone");
//
//        product.setQuantity(99);
//        product.setDescription("NEW Test Description");
//        product.setColor("NEW test Green");
//        product.setScale("NEW test Large");
//        product.setPrice(999.99);
//        product.setAddingDate(new Date());
////        productService.update(product);
////        productService.delete(3);
//        System.out.println(productService.readAll());
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
//
//           productService.create(product);
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
//////        for (int i = 0; i < 10; i++) {
////
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
//        ProductService productService = ProductServiceImpl.getProductService();
//        Map<Integer, Product> integerProductMap = productService.readAllMap();
//
//        System.out.println(integerProductMap.get(3));
//        productService.delete(3);
//        System.out.println(productService.readAllMap());
//        User user = new User();
//        user.setEmail("Один");
//        user.setName("Один");
//        user.setLastName("Один");
//        user.setPassword("один");
//        user.setRole(UserRole.USER);
//        user.setAmount(99.99);
//        UserService userService = UserServiceImpl.getUserService();
//        userService.create(user);
//        LOGGER.info("User added");


    }
}
