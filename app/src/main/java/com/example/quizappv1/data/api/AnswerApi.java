package com.example.quizappv1.data.api;

import com.example.quizappv1.model.Answer;

import java.util.List;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnswerApi {


    @POST("answer/addAnswer")
    Call<Answer> addAnswer(@Body Answer answer);

    @POST("answer/addAllAnswer")
    Call<List<Answer>> addAnswer(@Body List<Answer> answers);


    @PUT("answer/updateAnswer")
    Call<Answer> updateAnswer(@Body Answer answer);


    @GET("answer/getAnswer{id}")
    Call<Answer> getAnswer(@Path("id") int id);

    @GET("answer/getAnswers")
    Call<List<Answer>> getAnswers();

    @DELETE("answer/deleteAnswer{id}")
    Call<?> deleteAnswer(@Path("id") int id);

}
