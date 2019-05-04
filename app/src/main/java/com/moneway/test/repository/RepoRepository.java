package com.moneway.test.repository;


import com.moneway.test.data.api.ApiService;
import com.moneway.test.data.model.Repositorie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RepoRepository {

    private final ApiService apiService;

    @Inject
    public RepoRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<List<Repositorie>> getRepositories() {
        return apiService.getRepositories();
    }

    public Single<Repositorie> getRepo(String owner, String name) {
        return apiService.getRepo(owner, name);
    }
}
