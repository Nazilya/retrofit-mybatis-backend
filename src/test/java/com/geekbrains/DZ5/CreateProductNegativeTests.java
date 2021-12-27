package com.geekbrains.DZ5;

import com.geekbrains.dto.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProductNegativeTests extends BaseTest {
    String title = "С другой стороны  укрепление и  развитие  структуры обеспечивает участие в формировании систем массового участия.  Повседневная  практика показывает, что реализация намеченных плановых заданий в значительной  степени обуславливает создание модели развития.";

    @DisplayName("Создать продукт c несущ-щей категорией")
    @Test
    void postProductWrongCategoryNegativeTest() throws IOException {
        Response<Product> response = productService.createProduct(product.withCategoryTitle("WrongCategory")).execute();
        assertThat(response.code(), equalTo(500));
    }

    @DisplayName("Создать продукт c id=0")
    @Test
    void postProductWithIdNegativeTest() throws IOException {
        Response<Product> response = productService.createProduct(product.withId(0)).execute();
        assertThat(response.code(), equalTo(400));
    }
    @DisplayName("Создать продукт c превышающим размер Title")
    @Test
    void postProductWithLongTitleNegativeTest() throws IOException {
        Response<Product> response = productService.createProduct(product.withTitle(title)).execute();
        assertThat(response.code(), equalTo(500));
    }

}
