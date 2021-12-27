package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import com.geekbrains.enums.CategoryType;
import com.geekbrains.utils.PrettyLogger;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductNegativeTests extends BaseTest {
    Integer id;

    @BeforeEach
    void postProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        id = response.body().getId();
    }
    @DisplayName("WrongCategoryType")
    @Test
    void modifyProductWrongCategoryTypeTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product
                        .withId(id)
                        .withTitle("Table")
                        .withCategoryTitle(CategoryType.BOOK.getTitle())
                        .withPrice(155))
                .execute();
        assertThat(response.code(), equalTo(500));
    }

    @DisplayName("WrongId")
    @Test
    void modifyProductWrongIdTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product
                        .withId(0)
                        .withTitle("Table")
                        .withCategoryTitle(CategoryType.FURNITURE.getTitle())
                        .withPrice(155))
                .execute();
        assertThat(response.code(), equalTo(400));
    }

    @DisplayName("WrongMethod")
    @Test
    void modifyProductWrongMethodTest() throws IOException {
        Response<Product> response = productService.createProduct(product
                        .withId(id)
                        .withTitle("Table")
                        .withCategoryTitle(CategoryType.FURNITURE.getTitle())
                        .withPrice(155))
                .execute();
        assertThat(response.code(), equalTo(400));
    }

    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
    }
}
