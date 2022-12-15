package com.example.quizappv1.data.api;


import com.example.quizappv1.model.QuestionAdmin;
import com.example.quizappv1.model.QuestionUser;
import com.example.quizappv1.model.Result;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QuestionApi {

    @POST("question/")
    Call<QuestionAdmin> addQuestion(@Body QuestionAdmin question);

    @PUT("question/")
    Call<QuestionAdmin> updateQuestion(@Body QuestionAdmin question);

    @GET("question/quiz/{quizId}")
    Call<List<QuestionUser>> getQuestionOfQuizForTest(@Path("quizId") int quizId);

    @GET("question/quiz/all/{quizId}")
    Call<List<QuestionAdmin>> getQuestionsOfQuizAdmin(@Path("quizId") int quizId);

    @GET("question/{questionId}")
    Call<QuestionAdmin> getQuestion(@Path("questionId") int questionId);

    @GET("question/")
    Call<List<QuestionAdmin>> getQuestions();

    @DELETE("question/{questionId}")
    Call<?> deleteQuestion(@Path("questionId") int questionId);


    @POST("question/eval-quiz")
    Call<Result> evalQuiz(@Body List<QuestionUser> questions);


}

