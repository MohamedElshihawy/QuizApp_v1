package com.example.quizappv1.data.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.api.AuthenticateApi;

import com.example.quizappv1.model.User.Login;
import com.example.quizappv1.model.User.Token;
import com.example.quizappv1.model.User.User;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticateService {


    private static PreferenceManager manager;
    private static AuthenticateApi api = MainActivity.retrofitService.getRetrofit().create(AuthenticateApi.class);
    public static NetworkRequestListener listener;
    private static User user;

    public static void openPreferenceManger(Context context) {
        manager = new PreferenceManager(context);
    }

    public static void saveToken(Context context) {
        manager = new PreferenceManager(context);
    }

    public static void generateToken(Login login) {
        api.generateToken(login)
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                        if (response.body() != null) {
                            Log.e("TAG", "onResponse: " + response.body());
                            manager.putString(Constants.GENERATED_TOKEN_KEY, response.body().getToken());
                            listener.onFinish(Constants.GENERATE_TOKEN_KEY);
                        } else {
                            Log.e("TAG", "onResponse: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                        Log.e("TAG", "fail token: " );
                    }
                });
    }

    public static User getCurrentUser() {
        api.getCurrentUser()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body() != null) {
                            user = response.body();
                            manager.putString(Constants.CURRENT_USER_ID, response.body().getId().toString());
                            listener.onFinish(Constants.GET_CURRENT_USER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                            Log.e("TAG", "onResponse: didn't got user login ");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return user;
    }


}
