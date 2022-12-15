package com.example.quizappv1.data.api;


import com.example.quizappv1.model.User.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {


    @POST("user/addUser")
    Call<User> createUser(@Body User user);

    @PUT("user/updateUser")
    Call<User> updateUser(@Body User user);

    @GET("user/searchByUsernameOrEmail{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("user/searchById{id}")
    Call<User> getUserById(@Path("id") int id);

    @GET("user/allUser")
    Call<List<User>> getAllUser();

    @DELETE("user/deleteUserById{userId}")
    Call<?> deleteUser(@Path("userId") int userId);



}
