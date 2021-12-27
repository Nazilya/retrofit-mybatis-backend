package com.geekbrains;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.CategoriesExample;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
@Slf4j
public class Main {
    static Faker faker = new Faker();
    private static String resource = "mybatisConfig.xml";

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();
        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);

        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        log.info(String.valueOf(categoriesCount));

       // createNewCategory(categoriesMapper);

    }

    private static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());
        categoriesMapper.insert(newCategory);
    }


    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }
}