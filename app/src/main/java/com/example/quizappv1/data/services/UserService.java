package com.example.quizappv1.data.services;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.api.UserApi;
import com.example.quizappv1.model.User.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {


    public static NetworkRequestListener listener ;

    private static UserApi api = MainActivity.retrofitService.getRetrofit().create(UserApi.class);
    private static User user;


    public static List<User> createUser(User user) {
        List<User> users = new ArrayList<>();
        api.createUser(user)
            .enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.body() != null) {
                        users.add(response.body());
                        Log.e("TAG", "create user: created ");
                        listener.onFinish(Constants.CREATE_USER);
                    } else {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                        Log.e("TAG", "create user: not created " + response.code());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    listener.onFinish(Constants.REQUEST_FAILURE);
                    Log.e("TAG", "create user: fail "+t.getMessage());
                }
            });
        return users;
    }

    public static List<User> updateUser(User user) {
        List<User> users = new ArrayList<>();
        api.updateUser(user)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body() != null) {
                            users.add(response.body());
                            listener.onFinish(Constants.UPDATE_USER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return users;
    }


    public static List<User> getUser(String username) {
        List<User> users = new ArrayList<>();
        api.getUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body() != null) {
                            users.add(response.body());
                            listener.onFinish(Constants.GET_USER);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return users;
    }

    public static User getUserById(int id) {
        api.getUserById(id)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body() != null) {
                            user = response.body();
                            listener.onFinish(Constants.GET_USER_BY_ID);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return user;
    }


    public static List<User> getAllUser() {

        List<User> users = new ArrayList<>();

        api.getAllUser()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        if (response.body() != null) {
                            users.addAll(response.body());
                            listener.onFinish(Constants.GET_ALL_USERS);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });

        return users;
    }

    public static void deleteUser(int id) {
        api.deleteUser(id);
    }


}
