package com.uramonk.androidtemplateapp.utility;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.uramonk.androidtemplateapp.module.NetworkModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import retrofit2.Retrofit;

/**
 * Created by kaz on 2016/07/03.
 */
public class TestUtility {
    public static String createJsonString(String path) throws IOException {
        return readJsonString(path);
    }

    private static String readJsonString(String path) throws IOException {
        InputStream in = InstrumentationRegistry.getContext().getAssets().open(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = br.readLine()) != null) {
            stringBuilder.append(inputStr);
        }
        br.close();
        return stringBuilder.toString();
    }

    public static Retrofit getRetrofitWithMockWebServer(MockWebServer mockWebServer) {
        NetworkModule networkModule = new NetworkModule(mockWebServer.url("").toString());
        OkHttpClient okHttpClient = networkModule.provideOkHttpClient();
        return networkModule.provideRetrofit(okHttpClient);
    }
}
