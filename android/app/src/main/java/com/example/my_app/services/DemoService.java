package com.example.my_app.services;

import com.example.my_app.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DemoService {
    @GET("products")
    Call<List<Product>> fetchAllProducts();
}