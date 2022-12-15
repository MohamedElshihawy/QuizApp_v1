package com.example.quizappv1.data.api;


import com.example.quizappv1.model.Quiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QuizApi {


    @POST("quiz/")
    Call<Quiz> addQuiz(@Body Quiz quiz);

    @PUT("quiz/")
    Call<Quiz> updateQuiz(@Body Quiz quiz);


    @GET("quiz/{quizId}")
    Call<Quiz> getQuiz(@Path("quizId") int quizId);

    @GET("quiz/")
    Call<List<Quiz>> getQuizzes();

    @DELETE("quiz/{quizId}")
    Call<?> deleteQuiz(@Path("quizId") int quizId);


    @GET("quiz/category/{id}")
    Call<List<Quiz>> getQuizzesOfCategory(@Path("id") int id);

    @GET("quiz/active")
    Call<List<Quiz>> getActiveQuizzes();

    @GET("quiz/category/active/{id}")
    Call<List<Quiz>> getActiveQuizzesOfCategory(@Path("id") int id);

}
