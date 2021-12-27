package com.geekbrains.DZ6;

import com.geekbrains.db.model.Products;
import com.geekbrains.utils.DbUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductPositiveTests extends BaseTest{

    @BeforeEach
    void setUp() {
        Products newProduct = DbUtils.createNewProduct(productsMapper);
    }

    @Test
    void deleteProductTest() throws IOException {
        Integer countProductsBefore = DbUtils.countProducts(productsMapper);
        DbUtils.deleteProductByPrimaryKey();
        Integer countProductsAfter = DbUtils.countProducts(productsMapper);
        assertThat(countProductsAfter, equalTo(countProductsBefore - 1));
    }

}