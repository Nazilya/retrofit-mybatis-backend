package com.geekbrains.utils;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.CategoriesExample;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

@UtilityClass
public class DbUtils {
    private static String resource = "mybatisConfig.xml";
    static Faker faker = new Faker();
    Long productId;
    int categoryId;

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }

    @SneakyThrows
    public static CategoriesMapper getCategoriesMapper() {
        return getSqlSession().getMapper(CategoriesMapper.class);
    }

    @SneakyThrows
    public static ProductsMapper getProductsMapper() {
        return getSqlSession().getMapper(ProductsMapper.class);
    }

    //Categories
    public static Categories createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());
        categoriesMapper.insert(newCategory);
        categoryId = newCategory.getId();
        return newCategory;
    }

    public static Categories updateCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setId(categoryId);
        newCategory.setTitle("Test");
        categoriesMapper.updateByPrimaryKey(newCategory);
        return newCategory;
    }
    public static void deleteCategoryByPrimaryKey() {
        getCategoriesMapper().deleteByPrimaryKey(categoryId);
    }
    public static void deleteCategoryByExample() {
        Categories newCategory = new Categories();
        getCategoriesMapper().deleteByExample(newCategory);
    }

    public static Integer countCategories(CategoriesMapper categoriesMapper) {
        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        return Math.toIntExact(categoriesCount);
    }


    //Products
    public static Integer countProducts(ProductsMapper productsMapper) {
        long products = productsMapper.countByExample(new ProductsExample());
        return Math.toIntExact(products);
    }

    public static Products createNewProduct(ProductsMapper productsMapper) {
        Products newProduct = new Products();
        newProduct.setTitle(faker.book().author());
        newProduct.setPrice((int) ((Math.random() + 1) * 100));
        newProduct.setCategory_id(3L);
        productsMapper.insertSelective(newProduct);
        productId = newProduct.getId();
        return newProduct;
    }

    public static Products updateProduct (ProductsMapper productsMapper) {
        Products newProduct = new Products();
        newProduct.setId(productId);
        newProduct.setTitle("Table");
        newProduct.setPrice(115);
        newProduct.setCategory_id(3L);
        productsMapper.updateByPrimaryKey(newProduct);
        return newProduct;
    }

    public static void deleteProductByPrimaryKey() {
        getProductsMapper().deleteByPrimaryKey(productId);
    }


}