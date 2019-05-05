package com.moneway.test.repository.home;

import com.moneway.test.data.api.ApiService;
import com.moneway.test.data.model.Repositorie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class HomeRepositoryImpl implements HomeRepository {

    private final ApiService apiService;

    @Inject
    public HomeRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Repositorie>> getRepositories() {
        return apiService.getRepositories();
    }
}
