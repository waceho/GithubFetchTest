package com.moneway.test.data.local;

import com.moneway.test.data.model.Repo;

import java.util.List;

public interface RepoDao {

    List<Repo> getAllRepos();

    Repo getRepositorieByIsId(int id);

    void saveRepos(List<Repo> repositorieList);

    void deleteRepos();

}
