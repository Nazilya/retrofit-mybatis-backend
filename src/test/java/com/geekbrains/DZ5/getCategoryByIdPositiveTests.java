package com.geekbrains.DZ5;

import com.geekbrains.dto.Category;
import com.geekbrains.enums.CategoryType;
import com.geekbrains.utils.PrettyLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class getCategoryByIdPositiveTests extends BaseTest {
    Integer id = CategoryType.FURNITURE.getId();

    @DisplayName("getCategoryById")
    @Test
    void getCategoryByIdPositiveTest() throws IOException {
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo(CategoryType.FURNITURE.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Furniture")));
    }
}