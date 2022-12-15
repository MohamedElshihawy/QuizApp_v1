package com.example.quizappv1.data.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.api.QuestionApi;
import com.example.quizappv1.model.QuestionAdmin;
import com.example.quizappv1.model.QuestionUser;
import com.example.quizappv1.model.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionService {

    public static NetworkRequestListener listener;
    private static QuestionApi api = MainActivity.retrofitService.getRetrofit().create(QuestionApi.class);
    public static Result result;


    public static QuestionAdmin addQuestion(QuestionAdmin question) {
        List<QuestionAdmin> questions = new ArrayList<>();
        api.addQuestion(question)
                .enqueue(new Callback<QuestionAdmin>() {
                    @Override
                    public void onResponse(@NonNull Call<QuestionAdmin> call, @NonNull Response<QuestionAdmin> response) {
                        questions.add(response.body());
                        listener.onFinish(Constants.ADD_QUESTION);
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuestionAdmin> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return questions.get(0);
    }

    public static QuestionAdmin updateQuestion(QuestionAdmin question) {
        List<QuestionAdmin> questions = new ArrayList<>();
        api.updateQuestion(question)
                .enqueue(new Callback<QuestionAdmin>() {
                    @Override
                    public void onResponse(@NonNull Call<QuestionAdmin> call, @NonNull Response<QuestionAdmin> response) {
                        questions.add(response.body());
                        listener.onFinish(Constants.UPDATE_QUESTION);
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuestionAdmin> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return questions.get(0);
    }


    public static QuestionAdmin getQuestion(int id) {
        List<QuestionAdmin> questions = new ArrayList<>();
        api.getQuestion(id)
                .enqueue(new Callback<QuestionAdmin>() {
                    @Override
                    public void onResponse(@NonNull Call<QuestionAdmin> call, @NonNull Response<QuestionAdmin> response) {
                        questions.add(response.body());
                        listener.onFinish(Constants.GET_QUESTION);
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuestionAdmin> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return questions.get(0);
    }

    public static List<QuestionUser> getQuestionOfQuizForTest(int quizId) {

        List<QuestionUser> questions = new ArrayList<>();

        api.getQuestionOfQuizForTest(quizId)
                .enqueue(new Callback<List<QuestionUser>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<QuestionUser>> call, @NonNull Response<List<QuestionUser>> response) {
                        if (response.body() != null) {
                            questions.addAll(response.body());
                            listener.onFinish(Constants.GET_QUESTION_OF_QUIZ_FOR_TEST);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<QuestionUser>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return questions;
    }

    public static List<QuestionAdmin> getQuestionsOfQuizAdmin(int quizId) {

        List<QuestionAdmin> questions = new ArrayList<>();

        api.getQuestionsOfQuizAdmin(quizId)
                .enqueue(new Callback<List<QuestionAdmin>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<QuestionAdmin>> call, @NonNull Response<List<QuestionAdmin>> response) {
                        assert response.body() != null;
                        questions.addAll(response.body());
                        listener.onFinish(Constants.GET_QUESTIONS_OF_QUIZ_ADMIN);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<QuestionAdmin>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return questions;
    }

    public static List<QuestionAdmin> getQuestions() {

        List<QuestionAdmin> questions = new ArrayList<>();

        api.getQuestions()
                .enqueue(new Callback<List<QuestionAdmin>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<QuestionAdmin>> call, @NonNull Response<List<QuestionAdmin>> response) {
                        assert response.body() != null;
                        questions.addAll(response.body());
                        listener.onFinish(Constants.GET_QUESTIONS);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<QuestionAdmin>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return questions;
    }

    public static void deleteQuestion(int id) {
        api.deleteQuestion(id);
    }


    public static Result evalQuiz(List<QuestionUser> questions) {
        api.evalQuiz(questions)
                .enqueue(new Callback<Result>() {
                             @Override
                             public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                                 if (response.body() != null) {
                                     result = response.body();
                                     listener.onFinish(Constants.EVAL_QUIZ);
                                 } else {
                                         Log.e("answer", "question: " + response.code());
                                 }
                             }
                             @Override
                             public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                                 Log.e("answer", "failure: didn't get answer ");
                                 listener.onFinish(Constants.REQUEST_FAILURE);

                             }
                         }
                );
        return result;
    }


}
