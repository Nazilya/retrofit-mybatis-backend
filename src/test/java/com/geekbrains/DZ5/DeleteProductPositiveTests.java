package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.utils.PrettyLogger;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductPositiveTests extends BaseTest {
    Integer id;

    //Содать новый продукт, получить id. Передать сохранённый id как Path-параметр метода deleteProduct.
    @BeforeEach
    void postProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        id = response.body().getId();
    }

    @Test
    void deleteProductTest() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }
}
