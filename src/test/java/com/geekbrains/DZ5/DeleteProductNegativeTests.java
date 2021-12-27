package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.utils.PrettyLogger;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductNegativeTests extends BaseTest {
    Integer id;

    @BeforeEach
    void postProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        id = response.body().getId();
    }
    @DisplayName("WrongId")
    @Test
    void deleteProductWrongIdNegativeTest() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(0).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(500));
    }
    @DisplayName("WrongMethod")
    @Test
    void deleteProductWrongMethodNegativeTest() throws IOException {
        Response<ResponseBody> response = productService.deleteProductWrongMethod(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(405));
    }

    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
