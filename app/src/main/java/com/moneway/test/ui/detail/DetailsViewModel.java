package com.moneway.test.ui.detail;

import android.os.Bundle;
import android.util.Log;

import com.moneway.test.data.model.Branche;
import com.moneway.test.data.model.Contributor;
import com.moneway.test.data.model.Repositorie;
import com.moneway.test.repository.details.DetailsRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {

    private static final String REPOSITORIE_DETAILS_KEY = "repo_details";
    private final DetailsRepository detailsRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Repositorie> repositorie = new MutableLiveData<>();
    private final MutableLiveData<List<Branche>> branches = new MutableLiveData<>();
    private final MutableLiveData<List<Contributor>> contributors = new MutableLiveData<>();
    private final MutableLiveData<String> repoName = new MutableLiveData<>();
    private final MutableLiveData<String> repoDescription = new MutableLiveData<>();

    // setter
    public void setRepositorie(Repositorie repositorie) {
        this.repositorie.setValue(repositorie);
    }

    // getter
    public LiveData<Repositorie> getRepositorie() {
        return repositorie;
    }

    public MutableLiveData<List<Contributor>> getContributors() {
        return contributors;
    }

    LiveData<List<Branche>> getBranches() {
        return branches;
    }

    public MutableLiveData<String> getRepoName() {
        return repoName;
    }

    public MutableLiveData<String> getRepoDescription() {
        return repoDescription;
    }

    @Inject
    public DetailsViewModel(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
        disposable = new CompositeDisposable();
    }

    /**
     * save current instance
     *
     * @param outState current bundle for instance save
     */
    public void saveToBundle(Bundle outState) {
        if (repositorie.getValue() != null) {
            repoName.setValue(repositorie.getValue().getName());
            repoDescription.setValue(repositorie.getValue().getDescription());
            outState.putStringArray(REPOSITORIE_DETAILS_KEY, new String[]{
                    repositorie.getValue().getOwner().getLogin(),
                    repositorie.getValue().getName()
            });
        }
    }

    /**
     * restore saved instance
     *
     * @param details current bundle for instance restore
     */
    public void fetchDetails(String... details) {
        // load branches
        loadBranches(details[0], details[1]);
        // load contributors
        loadContributors(details[0], details[1]);
    }

    /**
     * fetch branche list from github api
     *
     * @param details repositories details
     */
    private void loadBranches(String... details) {
        disposable.add(detailsRepository
                .getBranches(details[0], details[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Branche>>() {
                    @Override
                    public void onSuccess(List<Branche> value) {
                        branches.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //catch error
                    }
                }));
    }

    /**
     * fetch branche list from github api
     *
     * @param details repositories details
     */
    private void loadContributors(String... details) {
        disposable.add(detailsRepository
                .getContributors(details[0], details[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contributor>>() {
                    @Override
                    public void onSuccess(List<Contributor> value) {
                        contributors.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //catch error
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
