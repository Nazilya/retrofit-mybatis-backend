package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.utils.PrettyLogger;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductByIdPositiveTests extends BaseTest {
    static Integer id;

    //Содать новый продукт, получить id. Получить продукт по этому id
    @BeforeEach
    void postProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        id = response.body().getId();
    }

    @Test
    void GetProductByIdPositiveTest() throws IOException {
        Response<Product> response = productService
                .getProduct(id)
                .execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getId(), equalTo(id));
        assertThat(response.code(), equalTo(200));;
    }

    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
