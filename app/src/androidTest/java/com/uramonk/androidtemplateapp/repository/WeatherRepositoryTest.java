package com.uramonk.androidtemplateapp.repository;

import android.support.test.runner.AndroidJUnit4;

import com.uramonk.androidtemplateapp.api.WeatherApi;
import com.uramonk.androidtemplateapp.entity.WeatherEntity;
import com.uramonk.androidtemplateapp.module.NetworkModule;
import com.uramonk.androidtemplateapp.module.WeatherModule;
import com.uramonk.androidtemplateapp.utility.TestUtility;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import retrofit2.Retrofit;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by uramonk on 2016/07/02.
 */
@RunWith(AndroidJUnit4.class)
public class WeatherRepositoryTest {
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private IWeatherRepository getWeatherRepository(MockWebServer mockWebServer) {
        Retrofit retrofit = TestUtility.getRetrofitWithMockWebServer(mockWebServer);

        WeatherModule weatherModule = new WeatherModule();
        WeatherApi weatherApi = weatherModule.provideWeatherApi(retrofit);
        return weatherModule.provideWeatherRepository(weatherApi);
    }

    @Test
    public void Weatherを取得できる() throws IOException {
        final MockWebServer mockWebServer = new MockWebServer();
        IWeatherRepository weatherRepository = getWeatherRepository(mockWebServer);
        mockWebServer.enqueue(new MockResponse().setBody(TestUtility.createJsonString("weather_normal.json")));

        WeatherEntity weatherEntity = weatherRepository.getWeather().toBlocking().single();
        assertThat(weatherEntity.base, is("stations"));
        assertThat(weatherEntity.weather.size(), is(1));
        assertThat(weatherEntity.weather.get(0).description, is("broken clouds"));
        assertThat(weatherEntity.weather.get(0).icon, is("04d"));
        assertThat(weatherEntity.weather.get(0).id, is(803));
        assertThat(weatherEntity.weather.get(0).main, is("Clouds"));

        mockWebServer.shutdown();
    }
}
