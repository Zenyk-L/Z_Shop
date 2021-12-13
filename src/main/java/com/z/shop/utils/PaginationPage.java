package com.z.shop.utils;

import com.z.shop.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationPage {

    private static final int QUANTITY_SHOW_ON_PAGE = 8;
    private List<Product> productList;
    private int totalPageQuantity;
    private int currentPage;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getTotalPageQuantity() {
        return totalPageQuantity;
    }

    public void setTotalPageQuantity(int totalPageQuantity) {
        this.totalPageQuantity = totalPageQuantity;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public static PaginationPage getPageByNumber(List<Product> objectList, String currentPageNumber) {
        PaginationPage paginationPage = new PaginationPage();
        int quantityObjects = objectList.size();
        int totalPageQuantity = quantityObjects / QUANTITY_SHOW_ON_PAGE;
        if (totalPageQuantity % QUANTITY_SHOW_ON_PAGE != 0) {
            totalPageQuantity++;
        }
        paginationPage.totalPageQuantity = totalPageQuantity;

        int currentPage = 1;
        if(currentPageNumber != null){
            currentPage = Integer.valueOf(currentPageNumber);
            if (currentPage > totalPageQuantity || currentPage < 1 ){
                currentPage = 1;
            }
        }
        paginationPage.currentPage = currentPage;
        paginationPage.productList = objectList.stream().skip(QUANTITY_SHOW_ON_PAGE*(currentPage-1)).limit(QUANTITY_SHOW_ON_PAGE).collect(Collectors.toList());

        return paginationPage;
    }


}
