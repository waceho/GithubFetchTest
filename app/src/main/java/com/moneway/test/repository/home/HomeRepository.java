package com.moneway.test.repository.home;

import com.moneway.test.data.model.Repo;

import java.util.List;

import io.reactivex.Single;

public interface HomeRepository {

    // get repositorie list from github api
    Single<List<Repo>> getRepositories();
}
