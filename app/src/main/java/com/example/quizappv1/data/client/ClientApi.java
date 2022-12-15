package com.example.quizappv1.data.client;

import android.content.Context;
import android.util.Log;

import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientApi {

    private Retrofit retrofit;
    private static PreferenceManager manager;


    public ClientApi() {
        initializeRetrofit();
    }

    public static void openPreference(Context context) {
        manager = new PreferenceManager(context);
    }

    private void initializeRetrofit() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest;
                        if (!manager.getString(Constants.GENERATED_TOKEN_KEY).equalsIgnoreCase("not Found")) {
                            newRequest = originalRequest.newBuilder()
                                    .addHeader("Content-Type", "application/json ; charList=UTF-8")
                                    .addHeader("Authorization", "Bearer " + manager.getString(Constants.GENERATED_TOKEN_KEY))
                                    .build();
                        } else {
                            newRequest = originalRequest.newBuilder()
                                    .addHeader("Content-Type", "application/json ; charList=UTF-8")
                                    .build();
                        }
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://quiz-online-backend-production-6813.up.railway.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }


    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


}