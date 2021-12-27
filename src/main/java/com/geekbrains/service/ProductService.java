package com.geekbrains.service;

import com.geekbrains.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;

public interface ProductService {

    @GET("products")   //    Returns products
    Call<ArrayList<Product>> getProducts();

    @GET("products/{id}")  //  Returns a specific product by their identifier
    Call<Product> getProduct(@Path("id") Integer id);

    @POST("products")               //creates a new product
    Call<Product> createProduct(@Body Product product);

    @PUT("products")                //   Modify product
    Call<Product> modifyProduct(@Body Product product);

    @DELETE("products/{id}")          //Delete product
    Call<ResponseBody> deleteProduct(@Path("id") Integer id);

    //Для негативных тестов

    @POST("products/{id}")          //Delete product методом POST
    Call<ResponseBody> deleteProductWrongMethod(@Path("id") Integer id);

    @DELETE("products")   //    Returns products методом DELETE
    Call<ArrayList<Product>> getProductsWrongMethod();
}

