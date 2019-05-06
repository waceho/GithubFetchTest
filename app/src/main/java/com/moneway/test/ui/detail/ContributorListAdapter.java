package com.moneway.test.ui.detail;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moneway.test.R;
import com.moneway.test.data.model.Contributor;
import com.moneway.test.databinding.ContributorItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class ContributorListAdapter extends RecyclerView.Adapter<ContributorListAdapter.BrancheViewHolder> {

    private final List<Contributor> data = new ArrayList<>();

    private static Context mContext;

    /**
     * custom adapter for contributor recycler view
     * @param viewModel detailViewModel
     * @param lifecycleOwner current lifecycle owner
     * @param context base context
     */
    ContributorListAdapter(DetailsViewModel viewModel, LifecycleOwner lifecycleOwner, Context context) {
        mContext = context;

        viewModel.getContributors().observe(lifecycleOwner, contributors -> {
            if (contributors != null) {
                data.clear();
                data.addAll(contributors);
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public BrancheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contributor_item, parent, false);
        return new BrancheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrancheViewHolder holder, int position) {
        holder.bindRepositorie(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class BrancheViewHolder extends RecyclerView.ViewHolder {

        // bind view
        private ContributorItemBinding binding;

        BrancheViewHolder(View itemView) {
            super(itemView);
            binding = bind(itemView);
        }

        /**
         * bind repositorie view item
         *
         * @param view itemview
         * @return RepositorieItemBinding
         */
        private ContributorItemBinding bind(View view) {
            return ContributorItemBinding.bind(view);
        }

        /**
         * bind {repositorie} and set value
         *
         * @param contributor one repositorie branche
         */
        void bindRepositorie(Contributor contributor) {
            Glide.with(mContext).load(contributor.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(binding.imgContributor);
            Log.d("tag ,", contributor.getAvatarUrl());
            binding.tvContributorName.setText(contributor.getLogin());
            binding.executePendingBindings();
        }
    }
}
