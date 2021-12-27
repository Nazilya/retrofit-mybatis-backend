package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductByIdNegativeTests extends BaseTest {
    @DisplayName("404 Not Found")
    @Test
    void GetProductByIdNegativeTest() throws IOException {
        Response<Product> response = productService
                .getProduct(0)
                .execute();
        assertThat(response.code(), equalTo(404));;
    }
}
