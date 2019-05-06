package com.moneway.test.ui.home;

import com.moneway.test.data.model.Repo;
import com.moneway.test.repository.home.HomeRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private final HomeRepository repoRepository;
    private final MutableLiveData<List<Repo>> repositories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable;

    @Inject
    public HomeViewModel(HomeRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    LiveData<List<Repo>> getRepos() {
        return repositories;
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    /**
     * fetch all repositories from github api
     **/
    private void fetchRepos() {
        loading.setValue(true);
        disposable.add(
                repoRepository
                        .getRepositories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                            @Override
                            public void onSuccess(List<Repo> value) {
                                repoLoadError.setValue(false);
                                repositories.setValue(value);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                repoLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
