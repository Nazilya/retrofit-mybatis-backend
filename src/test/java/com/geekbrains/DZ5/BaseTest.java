package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.enums.CategoryType;
import com.geekbrains.service.CategoryService;
import com.geekbrains.service.ProductService;
import com.geekbrains.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Retrofit;

import java.io.IOException;

public class BaseTest {
    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker = new Faker();
    Product product;


    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }
    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                //     .withTitle(faker.food().dish())
                .withTitle(faker.book().author())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FURNITURE.getTitle());

    }
}
