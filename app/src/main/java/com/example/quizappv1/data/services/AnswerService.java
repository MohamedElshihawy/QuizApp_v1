package com.example.quizappv1.data.services;


import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.api.AnswerApi;
import com.example.quizappv1.model.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerService {

    public static NetworkRequestListener listener;
    private static AnswerApi api = MainActivity.retrofitService.getRetrofit().create(AnswerApi.class);


    public static List<Answer> addAnswer(Answer answer) {
        List<Answer> answers = new ArrayList<>();
        api.addAnswer(answer)
                .enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(@NonNull Call<Answer> call, @NonNull Response<Answer> response) {
                        if (response.body() != null) {
                            answers.add(response.body());
                            listener.onFinish(Constants.ADD_ANSWER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Answer> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return answers;
    }

    public static List<Answer> addAnswer(List<Answer> answers) {
        List<Answer> answerList = new ArrayList<>();
        api.addAnswer(answers)
                .enqueue(new Callback<List<Answer>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Answer>> call, @NonNull Response<List<Answer>> response) {
                        if (response.body() != null) {
                            answerList.addAll(response.body());
                            listener.onFinish(Constants.ADD_LIST_OF_ANSWER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Answer>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return answerList;
    }

    public static List<Answer> updateAnswer(Answer answer) {
        List<Answer> answers = new ArrayList<>();
        api.updateAnswer(answer)
                .enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(@NonNull Call<Answer> call, @NonNull Response<Answer> response) {
                        if (response.body() != null) {
                            answers.add(response.body());
                            listener.onFinish(Constants.UPDATE_ANSWER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Answer> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return answers;
    }


    public static List<Answer> getAnswer(int id) {
        List<Answer> answers = new ArrayList<>();
        api.getAnswer(id)
                .enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(@NonNull Call<Answer> call, @NonNull Response<Answer> response) {
                        if (response.body() != null) {
                            answers.add(response.body());
                            listener.onFinish(Constants.GET_ANSWER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Answer> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return answers;
    }


    public static List<Answer> getAnswers() {
        List<Answer> answerList = new ArrayList<>();
        api.getAnswers()
                .enqueue(new Callback<List<Answer>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Answer>> call, @NonNull Response<List<Answer>> response) {
                        if (response.body() != null) {
                            answerList.addAll(response.body());
                            listener.onFinish(Constants.GET_ALL_ANSWERS);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Answer>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return answerList;
    }

    public static void deleteAnswer(int id) {
        api.deleteAnswer(id);
    }


}
