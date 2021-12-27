package com.geekbrains.DZ6;

import com.geekbrains.db.model.Products;
import com.geekbrains.utils.DbUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProductPositiveTests extends BaseTest{

    @Test
    void createNewProductTest() throws IOException {
        Integer countProductsBefore = DbUtils.countProducts(productsMapper);
        Products newProduct = DbUtils.createNewProduct(productsMapper);
        Integer countProductsAfter = DbUtils.countProducts(productsMapper);
        assertThat(countProductsAfter, equalTo(countProductsBefore + 1));
    }

    @AfterEach
    void tearDown() throws IOException {
        DbUtils.deleteProductByPrimaryKey();
    }

}