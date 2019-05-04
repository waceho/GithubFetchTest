package com.moneway.test.ui.home;

import com.moneway.test.data.model.Repositorie;
import com.moneway.test.repository.RepoRepository;

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

    private final RepoRepository repoRepository;
    private final MutableLiveData<List<Repositorie>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable;

    @Inject
    public HomeViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    LiveData<List<Repositorie>> getRepos() {
        return repos;
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
        //
        loading.setValue(true);
        disposable.add(
                repoRepository.getRepositories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Repositorie>>() {
                            @Override
                            public void onSuccess(List<Repositorie> value) {
                                repoLoadError.setValue(false);
                                repos.setValue(value);
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
