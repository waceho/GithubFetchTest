package com.moneway.test.repository.home;

import com.moneway.test.data.model.Repositorie;

import java.util.List;

import io.reactivex.Single;

public interface HomeRepository {

    // get repositorie list from github api
    Single<List<Repositorie>> getRepositories();
}
