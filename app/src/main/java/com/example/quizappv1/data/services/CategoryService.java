package com.example.quizappv1.data.services;


import androidx.annotation.NonNull;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.api.CategoryApi;
import com.example.quizappv1.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryService {

    public static NetworkRequestListener listener;
    private static CategoryApi api = MainActivity.retrofitService.getRetrofit().create(CategoryApi.class);

    public static List<Category> addCategory(Category category) {
        List<Category> categories = new ArrayList<>();
        api.addCategory(category)
                .enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                        if (response.body() != null) {
                            categories.add(response.body());
                            listener.onFinish(Constants.ADD_CATEGORY);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return categories;
    }

    public static List<Category> updateCategory(Category category) {
        List<Category> categories = new ArrayList<>();
        api.updateCategory(category)
                .enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                        if (response.body() != null) {
                            categories.add(response.body());
                            listener.onFinish(Constants.UPDATE_CATEGORY);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return categories;
    }

    public static List<Category> getCategory(int id) {
        List<Category> categories = new ArrayList<>();
        api.getCategory(id)
                .enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                        if (response.body() != null) {
                            categories.add(response.body());
                            listener.onFinish(Constants.GET_CATEGORY);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return categories;
    }

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        api.getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Category>> call, @NonNull retrofit2.Response<List<Category>> response) {
                        if (response.body() != null) {
                            categories.addAll(response.body());
                            listener.onFinish(Constants.GET_ALL_CATEGORIES);
                        } else {
                            listener.onFinish(Constants.REQUEST_FAILURE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                        listener.onFinish(Constants.REQUEST_FAILURE);
                    }
                });
        return categories;
    }

    public static void deleteAnswer(int id) {
        api.deleteCategory(id);
    }


}
