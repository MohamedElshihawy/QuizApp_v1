package com.example.quizappv1.data.api;

import com.example.quizappv1.model.User.Login;
import com.example.quizappv1.model.User.Token;
import com.example.quizappv1.model.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthenticateApi {

    @POST("/generate-token")
    Call<Token> generateToken(@Body Login login);

    @GET("/current-user")
    Call<User> getCurrentUser();


}