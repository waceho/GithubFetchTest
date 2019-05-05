package com.moneway.test.repository.details;

import com.moneway.test.data.model.Branche;
import com.moneway.test.data.model.Contributor;

import java.util.List;

import io.reactivex.Single;

public interface DetailsRepository {

    // get repositorie branche list from github api
    Single<List<Branche>> getBranches(String owner, String name);

    Single<List<Contributor>> getContributors(String owner, String name);

}
