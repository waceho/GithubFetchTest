package com.moneway.test.data.local;

import com.moneway.test.data.model.Repositorie;

import java.util.List;

public interface RepoDao {

    List<Repositorie> getAllRepos();

    Repositorie getRepositorieByIsId(int id);

    void saveRepos(List<Repositorie> repositorieList);

    void deleteRepos();

}
