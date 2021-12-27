package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.utils.PrettyLogger;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProductPositiveTests extends BaseTest {
    static Integer id;

    @DisplayName("Create a new product")
    @Test
    void postProductTest() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        id =  response.body().getId();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.code(), equalTo(201));
    }

    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
