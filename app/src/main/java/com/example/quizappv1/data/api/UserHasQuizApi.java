package com.example.quizappv1.data.api;


import com.example.quizappv1.model.Answer;
import com.example.quizappv1.model.UserHasQuiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserHasQuizApi {


    @POST("userHasQuiz/addUserHasQuiz")
    Call<UserHasQuiz> addUserHasQuiz(@Body UserHasQuiz userHasQuiz);

    @PUT("userHasQuiz/updateUserHasQuiz")
    Call<UserHasQuiz> updateUserHasQuiz(@Body UserHasQuiz userHasQuiz);

    @GET("userHasQuiz/getUserHasQuizAnswer{id}")
    Call<List<Answer>> getUserHasQuizAnswer(@Path("id") int id);


    @GET("userHasQuiz/getUserHasQuiz{id}")
    Call<UserHasQuiz> getUserHasQuiz(@Path("id") int id);

    @GET("userHasQuiz/getUserHasQuizzes")
    Call<List<UserHasQuiz>> getUserHasQuizzes();


    @DELETE("userHasQuiz/deleteUserHasQuiz{id}")
    Call<?> deleteUserHasQuiz(@Path("id") int id);



    @GET("userHasQuiz/getUserHasQuizzesByUserId{userId}")
    Call<List<UserHasQuiz>> getUserHasQuizzesByUserId(@Path("userId") int userId);

    @GET("userHasQuiz/getUserHasQuizzesByUserIdAndQuizIdAnswer/{userId}/{quizId}")
    Call<List<Answer>> getUserHasQuizzesByUserIdAndQuizIdAnswer(@Path("userId") int userId,@Path("quizId") int quizId);

    @GET("userHasQuiz/getUserHasQuizzesByUserIdAndQuizId/{userId}/{quizId}")
    Call<UserHasQuiz> getUserHasQuizzesByUserIdAndQuizId(@Path("userId") int userId,@Path("quizId") int quizId);

    @GET("userHasQuiz/getUserHasQuizzesByQuizId{quizId}")
    Call<List<UserHasQuiz>> getUserHasQuizzesByQuizId(@Path("quizId") int quizId);

    @GET("userHasQuiz/checkUserExamBefore/{userId}/{quizId}")
    Call<Boolean> checkUserExamBefore(@Path("userId") int userId,@Path("quizId") int quizId);


}
