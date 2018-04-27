package com.example.dizzer.gittesttask.util;

import android.app.Application;

import com.example.dizzer.gittesttask.model.GitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dizzer on 4/27/2018.
 */

public class App extends Application{

    public static final String BASE_URL = "https://api.github.com/";
    public static GitApi gitApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitApi = retrofit.create(GitApi.class);
    }

    public static GitApi getApi(){
        return gitApi;
    }
}
