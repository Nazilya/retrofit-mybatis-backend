package com.geekbrains.DZ6;

import com.geekbrains.db.model.Categories;
import com.geekbrains.utils.DbUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UpdateCategoriesPositiveTests extends BaseTest{

    @BeforeEach
    void createCategoryPositiveTest() throws IOException {
        Categories newCategory = DbUtils.createNewCategory(categoriesMapper);
    }
    @Test
    void updateCategoryPositiveTest() throws IOException {
        Integer countCategoriesBefore = DbUtils.countCategories(categoriesMapper);
        Categories newCategory = DbUtils.updateCategory(categoriesMapper);
        Integer countCategoriesAfter = DbUtils.countCategories(categoriesMapper);
        assertThat(countCategoriesAfter, equalTo(countCategoriesBefore));
        assertThat(newCategory.getTitle(), equalTo("Test"));
    }
    @AfterEach
    void tearDown() throws IOException {
        DbUtils.deleteCategoryByPrimaryKey();
    }
}
