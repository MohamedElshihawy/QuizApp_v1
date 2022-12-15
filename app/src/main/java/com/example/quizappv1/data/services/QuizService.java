package com.example.quizappv1.data.services;

import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.api.CategoryApi;
import com.example.quizappv1.data.api.QuizApi;
import com.example.quizappv1.model.Category;
import com.example.quizappv1.model.Quiz;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizService {

    public static NetworkRequestListener listener;

    private static QuizApi api = MainActivity.retrofitService.getRetrofit().create(QuizApi.class);


    public static List<Quiz> addQuiz(Quiz quiz) {
        List<Quiz> quizzes = new ArrayList<>();
        api.addQuiz(quiz)
                .enqueue(new Callback<Quiz>() {
                    @Override
                    public void onResponse(@NonNull Call<Quiz> call, @NonNull Response<Quiz> response) {
                        if (response.body() != null) {
                            quizzes.add(response.body());
                            listener.onFinish(Constants.ADD_QUIZ);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Quiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return quizzes;
    }

    public static List<Quiz> updateQuiz(Quiz quiz) {
        List<Quiz> quizzes = new ArrayList<>();
        api.updateQuiz(quiz)
                .enqueue(new Callback<Quiz>() {
                    @Override
                    public void onResponse(@NonNull Call<Quiz> call, @NonNull Response<Quiz> response) {
                        if (response.body() != null) {
                            quizzes.add(response.body());
                            listener.onFinish(Constants.UPDATE_QUIZ);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Quiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return quizzes;
    }

    public static List<Quiz> getQuiz(int quizId) {
        List<Quiz> quizzes = new ArrayList<>();
        api.getQuiz(quizId)
                .enqueue(new Callback<Quiz>() {
                    @Override
                    public void onResponse(@NonNull Call<Quiz> call, @NonNull Response<Quiz> response) {
                        if (response.body() != null) {
                            quizzes.add(response.body());
                            listener.onFinish(Constants.GET_QUIZ);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Quiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return quizzes;
    }


    public static List<Quiz> getQuizzes() {

        List<Quiz> quizzes = new ArrayList<>();
        api.getQuizzes()
                .enqueue(new Callback<List<Quiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Quiz>> call, @NonNull Response<List<Quiz>> response) {
                        if (response.body() != null) {
                            quizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_QUIZZES);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Quiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return quizzes;
    }

    public static void deleteQuiz(int id) {
        api.deleteQuiz(id);
    }

    public static List<Quiz> getQuizzesOfCategory(int id) {

        List<Quiz> quizzes = new ArrayList<>();

        api.getQuizzesOfCategory(id)
                .enqueue(new Callback<List<Quiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Quiz>> call, @NonNull Response<List<Quiz>> response) {
                        if (response.body() != null) {
                            quizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_QUIZZES_OF_CATEGORY);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Quiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return quizzes;
    }

    public static List<Quiz> getActiveQuizzes() {

        List<Quiz> quizzes = new ArrayList<>();

        api.getActiveQuizzes()
                .enqueue(new Callback<List<Quiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Quiz>> call, @NonNull Response<List<Quiz>> response) {
                        if (response.body() != null) {
                            quizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_ACTIVE_QUIZZES);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Quiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return quizzes;
    }

    public static List<Quiz> getActiveQuizzesOfCategory(int id) {

        List<Quiz> quizzes = new ArrayList<>();

        api.getActiveQuizzesOfCategory(id)
                .enqueue(new Callback<List<Quiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Quiz>> call, @NonNull Response<List<Quiz>> response) {
                        if (response.body() != null) {
                            quizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_ACTIVE_QUIZZES_OF_CATEGORY);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Quiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return quizzes;
    }

}
