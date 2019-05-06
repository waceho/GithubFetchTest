package com.moneway.test.ui.detail;

import android.os.Bundle;
import android.view.View;

import com.moneway.test.R;
import com.moneway.test.base.BaseFragment;
import com.moneway.test.databinding.FragmentDetailsBinding;
import com.moneway.test.ui.main.MainActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DetailsFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;

    private DetailsViewModel detailsViewModel;
    private FragmentDetailsBinding binding;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_details;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // init binding
        binding = bind(view);
        // init detailsViewModel
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(DetailsViewModel.class);
        binding.setDetailViewModel(detailsViewModel);
        binding.setLifecycleOwner(this);
        // config branch recyclerview
        configBrancheRecyclerView();
        // config contributors recyclerview
        configContributorRecyclerView();
        // display repositorie details
        displayRepo();

        MainActivity activity = (MainActivity)getActivity();
        if (activity != null) {
            activity.canDisplayHomeUp();
        }
    }

    private FragmentDetailsBinding bind(View view) {
        return FragmentDetailsBinding.bind(view);
    }

    private void displayRepo() {
        detailsViewModel.getRepositorie().observe(this, repositorie -> {
            if (repositorie != null) {
                binding.tvRepoName.setText(repositorie.getName());
                binding.tvRepoDescription.setText(repositorie.getDescription());
                // fetch Details branch and contributors
                detailsViewModel.fetchDetails(repositorie.getOwner().getLogin(), repositorie.getName());
            }
        });
    }

    private void configBrancheRecyclerView() {
        binding.branchList.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        binding.branchList.setAdapter(new BranchListAdapter(detailsViewModel, this));
        binding.branchList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void configContributorRecyclerView() {
        binding.contributorList.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.HORIZONTAL));
        binding.contributorList.setAdapter(new ContributorListAdapter(detailsViewModel, this, getContext()));
        binding.contributorList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }
}
