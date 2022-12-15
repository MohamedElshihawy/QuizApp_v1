package com.example.quizappv1.data.services;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.api.UserHasQuizApi;
import com.example.quizappv1.model.Answer;
import com.example.quizappv1.model.UserHasQuiz;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHasQuizService {

    public static NetworkRequestListener listener;
    private static UserHasQuizApi api = MainActivity.retrofitService.getRetrofit().create(UserHasQuizApi.class);
    public static Boolean hasQuiz = false;
    public static UserHasQuiz userHasQuizzes;

    public static UserHasQuiz addUserHasQuiz(UserHasQuiz userHasQuiz) {

        //  List<UserHasQuiz> userHasQuizs = new ArrayList<>();
        api.addUserHasQuiz(userHasQuiz)
                .enqueue(new Callback<UserHasQuiz>() {
                    @Override
                    public void onResponse(@NonNull Call<UserHasQuiz> call, @NonNull Response<UserHasQuiz> response) {
                        if (response.body() != null) {
                            userHasQuizzes = response.body();
                            listener.onFinish(Constants.ADD_USER_HAS_QUIZ);

                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                            // Log.e("TAG", "onResponse:here null " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserHasQuiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                        //  Log.e("TAG", "onResponse:here null " + t.getMessage());

                    }
                });
        return userHasQuizzes;
    }

    public static List<UserHasQuiz> updateUserHasQuiz(UserHasQuiz userHasQuiz) {
        List<UserHasQuiz> userHasQuizzes = new ArrayList<>();
        api.updateUserHasQuiz(userHasQuiz)
                .enqueue(new Callback<UserHasQuiz>() {
                    @Override
                    public void onResponse(@NonNull Call<UserHasQuiz> call, @NonNull Response<UserHasQuiz> response) {
                        if (response.body() != null) {
                            userHasQuizzes.add(response.body());
                            Log.e("TAG", "onResponse: update user " + response.body());
                            listener.onFinish(Constants.UPDATE_USER_HAS_QUIZ);
                        } else {
                            Log.e("TAG", "onResponse: update user " + response.code());
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserHasQuiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return userHasQuizzes;
    }


    public static List<Answer> getUserHasQuizAnswer(int id) {

        List<Answer> answers = new ArrayList<>();

        api.getUserHasQuizAnswer(id)
                .enqueue(new Callback<List<Answer>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Answer>> call, @NonNull Response<List<Answer>> response) {
                        if (response.body() != null) {
                            answers.addAll(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZ_ANSWER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Answer>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return answers;
    }

    public static List<UserHasQuiz> getUserHasQuiz(int id) {

        List<UserHasQuiz> userHasQuiz = new ArrayList<>();

        api.getUserHasQuiz(id)
                .enqueue(new Callback<UserHasQuiz>() {
                    @Override
                    public void onResponse(@NonNull Call<UserHasQuiz> call, @NonNull Response<UserHasQuiz> response) {
                        if (response.body() != null) {
                            userHasQuiz.add(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZ);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserHasQuiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return userHasQuiz;
    }

    public static List<UserHasQuiz> getUserHasQuizzes() {

        List<UserHasQuiz> userHasQuizzes = new ArrayList<>();

        api.getUserHasQuizzes()
                .enqueue(new Callback<List<UserHasQuiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserHasQuiz>> call, @NonNull Response<List<UserHasQuiz>> response) {
                        if (response.body() != null) {
                            userHasQuizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZZES);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserHasQuiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return userHasQuizzes;
    }

    public static void deleteUserHasQuiz(int id) {
        api.deleteUserHasQuiz(id);
    }

    // get quizzes history
    public static List<UserHasQuiz> getUserHasQuizzesByUserId(int id) {

        List<UserHasQuiz> userHasQuizzes = new ArrayList<>();
        api.getUserHasQuizzesByUserId(id)
                .enqueue(new Callback<List<UserHasQuiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserHasQuiz>> call, @NonNull Response<List<UserHasQuiz>> response) {
                        if (response.body() != null) {
                            userHasQuizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZZES_BY_USER_ID);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserHasQuiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return userHasQuizzes;
    }

    // get quiz answer
    public static List<Answer> getUserHasQuizzesByUserIdAndQuizIdAnswer(int userId, int quizId) {

        List<Answer> answers = new ArrayList<>();

        api.getUserHasQuizzesByUserIdAndQuizIdAnswer(userId, quizId)
                .enqueue(new Callback<List<Answer>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Answer>> call, @NonNull Response<List<Answer>> response) {

                        if (response.body() != null) {
                            answers.addAll(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZZES_BY_USER_ID_AND_QUIZ_ID_ANSWER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Answer>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return answers;
    }

    public static List<UserHasQuiz> getUserHasQuizzesByUserIdAndQuizId(int userId, int quizId) {

        List<UserHasQuiz> userHasQuiz = new ArrayList<>();

        api.getUserHasQuizzesByUserIdAndQuizId(userId, quizId)
                .enqueue(new Callback<UserHasQuiz>() {
                    @Override
                    public void onResponse(@NonNull Call<UserHasQuiz> call, @NonNull Response<UserHasQuiz> response) {

                        if (response.body() != null) {
                            userHasQuiz.add(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZZES_BY_USER_ID_AND_QUIZ_ID);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserHasQuiz> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return userHasQuiz;
    }


    public static List<UserHasQuiz> getUserHasQuizzesByQuizId(int id) {

        List<UserHasQuiz> userHasQuizzes = new ArrayList<>();

        api.getUserHasQuizzesByQuizId(id)
                .enqueue(new Callback<List<UserHasQuiz>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserHasQuiz>> call, @NonNull Response<List<UserHasQuiz>> response) {
                        if (response.body() != null) {
                            userHasQuizzes.addAll(response.body());
                            listener.onFinish(Constants.GET_USER_HAS_QUIZZES_BY_QUIZ_ID);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserHasQuiz>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return userHasQuizzes;
    }

    public static Boolean checkUserExamBefore(int userId, int quizId) {
        api.checkUserExamBefore(userId, quizId)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                        if (response.body() != null) {
                            hasQuiz = response.body();
                            listener.onFinish(Constants.CHECK_USER_EXAM_BEFORE);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.DIDNT_TAKE_QUIZ_BEFORE);
                    }
                });
        return hasQuiz;
    }

}
