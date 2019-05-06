package com.moneway.test.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneway.test.MonewayTestApp;
import com.moneway.test.data.api.ApiService;
import com.moneway.test.repository.details.DetailsRepository;
import com.moneway.test.repository.details.DetailsRepositoryImpl;
import com.moneway.test.repository.home.HomeRepository;
import com.moneway.test.repository.home.HomeRepositoryImpl;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moneway.test.BuildConfig.BASE_URL;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    HomeRepository provideHomeRepository(ApiService apiService) {
        return new HomeRepositoryImpl(apiService);
    }

    @Provides
    @Singleton
    DetailsRepository provideDetailsRepository(ApiService apiService) {
        return new DetailsRepositoryImpl(apiService);
    }

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Context provideContext() { return new MonewayTestApp().getApplicationContext(); }

    @Provides
    static Retrofit provideRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Provides
    static ApiService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
