package com.geekbrains.DZ6;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.service.CategoryService;
import com.geekbrains.service.ProductService;
import com.geekbrains.utils.DbUtils;
import com.geekbrains.utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;

public class BaseTest {
    static Retrofit client;
    static CategoriesMapper categoriesMapper;
    static CategoryService categoryService;
    static ProductsMapper productsMapper;
    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        categoryService = client.create(CategoryService.class);
        categoriesMapper = DbUtils.getCategoriesMapper();
        productService = client.create(ProductService.class);
        productsMapper = DbUtils.getProductsMapper();
    }
}
