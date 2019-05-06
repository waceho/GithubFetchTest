package com.moneway.test.data.api;

import com.moneway.test.data.model.Branche;
import com.moneway.test.data.model.Contributor;
import com.moneway.test.data.model.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/repositories")
    Single<List<Repo>> getRepositories();

    /*
    @GET("v2/5ccf1204300000ce1652c472")
    Single<List<Repo>> getRepositories();
    */

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepositorie(@Path("owner") String owner, @Path("name") String name);

    /*
    @GET("v2/5ccf1163300000141652c470")
    Single<Repo> getRepositorie();
    */

    @GET("repos/{owner}/{name}/branches")
    Single<List<Branche>> getBranches(@Path("owner") String owner, @Path("name") String name);


    @GET("repos/{owner}/{name}/contributors")
    Single<List<Contributor>> getContributors(@Path("owner") String owner, @Path("name") String name);

    /*
    @GET("v2/5ccf1104300000ce1652c46d")
    Single<List<Branche>> getBranches();
    */
}
