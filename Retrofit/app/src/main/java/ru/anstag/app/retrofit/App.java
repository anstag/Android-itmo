package ru.anstag.app.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static ApiInterface apiInterface;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://umori.li/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApi() {
        return apiInterface;
    }

}

