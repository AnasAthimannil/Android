package com.vlz.eecommerce.utils;

import android.app.Application;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vlz.eecommerce.service.repository.dao.AppDatabase;
import com.vlz.eecommerce.service.repository.dao.ProductDao;
import com.vlz.eecommerce.service.repository.webservice.EcommerceAPI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObjectFactory {
    private static AppDatabase appDatabase;
    private static EcommerceAPI ecommerceAPI;

    public static AppDatabase getAppDatabaseInstance(Application application){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(
                    application,
                    AppDatabase.class,
                    "ecommerse_db").build();

        return appDatabase;
    }

    public static EcommerceAPI getEcommerceAPIInstance(){
        if(ecommerceAPI == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor).build();

            Gson gson = new GsonBuilder().setLenient().setDateFormat("dd-MM-yyyy").create();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(StringUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ecommerceAPI = retrofit.create(EcommerceAPI.class);
        }
        return ecommerceAPI;
    }

    public static String dateToDTString(Date date){
        return new SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault()).format(date);
    }

    public static void destroy(){
        appDatabase = null;
        ecommerceAPI = null;
    }
}
