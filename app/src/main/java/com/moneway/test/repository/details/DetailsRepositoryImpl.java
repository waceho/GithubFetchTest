package com.moneway.test.repository.details;

import com.moneway.test.data.api.ApiService;
import com.moneway.test.data.model.Branche;
import com.moneway.test.data.model.Contributor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DetailsRepositoryImpl implements DetailsRepository {

    private final ApiService apiService;

    @Inject
    public DetailsRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Branche>> getBranches(String owner, String name) {
        return apiService.getBranches(owner, name);
    }

    @Override
    public Single<List<Contributor>> getContributors(String owner, String name) {
        return apiService.getContributors(owner, name);
    }

}
