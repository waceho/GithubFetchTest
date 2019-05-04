package com.moneway.test.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneway.test.data.api.ApiService;
import com.moneway.test.repository.RepoRepository;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moneway.test.BuildConfig.BASE_URL;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    RepoRepository provideRepoRepository(ApiService apiService) {
        return new RepoRepository(apiService);
    }

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static ApiService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
