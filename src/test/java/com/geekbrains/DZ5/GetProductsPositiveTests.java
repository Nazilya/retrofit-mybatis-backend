package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.utils.PrettyLogger;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductsPositiveTests extends BaseTest {
    @Disabled
    @Test
    void getProducts() throws IOException {
        Response<ArrayList<Product>> response = productService
                .getProducts()
                .execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(500));
    }
}
