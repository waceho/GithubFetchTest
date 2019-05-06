package com.moneway.test.data.local;

import com.moneway.test.data.model.Repositorie;

import java.util.List;

import io.realm.Realm;

/**
 * Repositories DAO implementation
 */
public class RepoDaoImpl implements RepoDao {


    private static RepoDaoImpl INSTANCE = null;
    private Realm mRealm;

    private RepoDaoImpl() {
        mRealm = Realm.getDefaultInstance();
    }

    public static RepoDaoImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RepoDaoImpl();

        return INSTANCE;
    }

    @Override
    public List<Repositorie> getAllRepos() {
        return mRealm.where(Repositorie.class).findAll();
    }

    @Override
    public Repositorie getRepositorieByIsId(int id) {
        return mRealm.where(Repositorie.class).equalTo("id", id).findFirst();
    }

    @Override
    public void saveRepos(List<Repositorie> repositorieList) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(repositorieList);
        });
    }

    @Override
    public void deleteRepos() {
        mRealm.executeTransaction(realm -> mRealm.deleteAll());
    }
}
