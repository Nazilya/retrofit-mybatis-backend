package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.enums.CategoryType;
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

public class ModifyProductPositiveTests extends BaseTest {
    Integer id;

    //Содать новый продукт, получить id. Передать сохранённый id параметром для modifyProduct.
    @BeforeEach
    void postProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        id = response.body().getId();
    }

    @Test
    void modifyProductTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product
                        .withId(id)
                        .withTitle("Table")
                        .withCategoryTitle(CategoryType.FURNITURE.getTitle())
                        .withPrice(155))
                .execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo("Table"));
        assertThat(response.body().getPrice(), equalTo(155));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.code(), equalTo(200));
    }

    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
