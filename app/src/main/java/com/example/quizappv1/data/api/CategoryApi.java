package com.example.quizappv1.data.api;

import com.example.quizappv1.model.Category;

import java.util.List;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryApi {


    @POST("category/")
    Call<Category> addCategory(@Body Category category);

    @PUT("category/")
    Call<Category> updateCategory(@Body Category category);

    @GET("category/{categoryId}")
    Call<Category> getCategory(@Path("categoryId") int categoryId);

    @GET("category/")
    Call<List<Category>> getCategories();

    @DELETE("category/{categoryId}")
    Call<?> deleteCategory(@Path("categoryId") int categoryId);

}
