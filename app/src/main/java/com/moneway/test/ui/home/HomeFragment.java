package com.moneway.test.ui.home;

import android.os.Bundle;
import android.view.View;

import com.moneway.test.R;
import com.moneway.test.base.BaseFragment;
import com.moneway.test.data.model.Repositorie;
import com.moneway.test.databinding.FragmentHomeListBinding;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HomeFragment extends BaseFragment implements RepositorieSelectListener {

    @Inject
    ViewModelProvider.Factory factoryViewModel;

    private HomeViewModel viewModel;
    private FragmentHomeListBinding binder;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // init binding
        binder = bind(view);
        // init viewModel
        viewModel = ViewModelProviders.of(this, factoryViewModel).get(HomeViewModel.class);
        // config recyclerView
        configRecyclerView();
        // set observer
        observableViewModel();
    }

    private void configRecyclerView() {
        binder.recyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        binder.recyclerView.setAdapter(new RepositorieListAdapter(viewModel, this, this));
        binder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private FragmentHomeListBinding bind(View view) {
        return FragmentHomeListBinding.bind(view);
    }

    @Override
    public void onRepoSelected(Repositorie repositorie) {
        launchRepoDetails();
    }

    /**
     * show selected repositorie details
     **/
    private void launchRepoDetails() {
        /*
        DetailsViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.setSelectedRepo(repositorie);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainScreenContainer, new DetailsFragment())
                .addToBackStack(null).commit();
                */
    }

    /**
     * set obervable viewModel
     **/
    private void observableViewModel() {

        setRepositoriesObserver();

        setErrorObserver();

        setLoadingObserver();
    }

    private void setRepositoriesObserver() {
        viewModel.getRepos().observe(this, repos -> {
            if (repos != null) binder.recyclerView.setVisibility(View.VISIBLE);
        });
    }

    private void setErrorObserver() {
        viewModel.getError().observe(this, isError -> {
            if (isError != null)
                if (isError) {
                    binder.tvError.setVisibility(View.VISIBLE);
                    binder.recyclerView.setVisibility(View.GONE);
                    binder.tvError.setText("An Error Occurred While Loading Data!");
                } else {
                    binder.tvError.setVisibility(View.GONE);
                    binder.tvError.setText(null);
                }
        });
    }

    private void setLoadingObserver() {
        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                binder.loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    binder.tvError.setVisibility(View.GONE);
                    binder.recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}
