package com.geekbrains.DZ6;

import com.geekbrains.db.model.Categories;
import com.geekbrains.utils.DbUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class CreateCategoriesPositiveTests extends BaseTest{

    @Test
    void createCategoryPositiveTest() throws IOException {
        Integer countCategoriesBefore = DbUtils.countCategories(categoriesMapper);
        Categories newCategory = DbUtils.createNewCategory(categoriesMapper);
        Integer countCategoriesAfter = DbUtils.countCategories(categoriesMapper);
        assertThat(countCategoriesAfter, equalTo(countCategoriesBefore + 1));
    }

    @AfterEach
    void tearDown() throws IOException {
        DbUtils.deleteCategoryByPrimaryKey();
    }
}
