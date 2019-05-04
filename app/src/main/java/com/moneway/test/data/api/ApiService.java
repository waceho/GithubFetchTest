package com.moneway.test.data.api;

import com.moneway.test.data.model.Repositorie;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/repositories")
    Single<List<Repositorie>> getRepositories();

    @GET("repos/{owner}/{name}")
    Single<Repositorie> getRepo(@Path("owner") String owner, @Path("name") String name);
}
