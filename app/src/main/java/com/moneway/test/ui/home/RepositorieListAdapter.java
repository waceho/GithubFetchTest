package com.moneway.test.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moneway.test.R;
import com.moneway.test.data.model.Repositorie;
import com.moneway.test.databinding.RepositorieItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class RepositorieListAdapter extends RecyclerView.Adapter<RepositorieListAdapter.RepoViewHolder> {

    private final List<Repositorie> data = new ArrayList<>();
    private RepositorieSelectListener repositorieSelectListener;

    RepositorieListAdapter(HomeViewModel viewModel, LifecycleOwner lifecycleOwner, RepositorieSelectListener repositorieSelectListener) {
        this.repositorieSelectListener = repositorieSelectListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositorie_item, parent, false);
        return new RepoViewHolder(view, repositorieSelectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bindRepositorie(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        // bind view
        private RepositorieItemBinding binding;

        private Repositorie repositorie;

        RepoViewHolder(View itemView, RepositorieSelectListener repositorieSelectListener) {
            super(itemView);

            binding = bind(itemView);

            itemView.setOnClickListener(v -> {
                if (repositorie != null) {
                    repositorieSelectListener.onRepoSelected(repositorie);
                }
            });
        }

        /**
         * bind repositorie view item
         *
         * @param view itemview
         * @return RepositorieItemBinding
         */
        private RepositorieItemBinding bind(View view) {
            return RepositorieItemBinding.bind(view);
        }

        /**
         * bind {repositorie} and set value
         *
         * @param repositorie
         */
        void bindRepositorie(Repositorie repositorie) {
            this.repositorie = repositorie;
            binding.tvRepoName.setText(repositorie.getName());
            binding.tvRepoDescription.setText(repositorie.getDescription());
        }
    }
}
