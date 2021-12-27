package com.geekbrains.DZ5;

import com.geekbrains.dto.Category;
import com.geekbrains.enums.CategoryType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class getCategoryByIdNegativeTests extends BaseTest {

    Integer idWrong = CategoryType.BOOK.getId();

    @DisplayName("Wrong id")
    @Test
    void getCategoryByIdNegativeTest() throws IOException {
        Response<Category> response = categoryService
                .getCategory(idWrong)
                .execute();
        assertThat(response.body(), equalTo(null));
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(404));
    }
}