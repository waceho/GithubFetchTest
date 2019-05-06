package com.moneway.test.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.moneway.test.R;
import com.moneway.test.data.model.Repositorie;
import com.moneway.test.databinding.RepositorieItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.RepoViewHolder> implements Filterable {

    private final List<Repositorie> data = new ArrayList<>();
    private RepositorieSelectListener repositorieSelectListener;
    private List<Repositorie> repositoriesList;
    private List<Repositorie> repositoriesFiltered;

    RepositoriesListAdapter(HomeViewModel viewModel, LifecycleOwner lifecycleOwner, RepositorieSelectListener repositorieSelectListener) {
        this.repositorieSelectListener = repositorieSelectListener;

        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
                this.repositoriesList = viewModel.getRepos().getValue();
                this.repositoriesFiltered = viewModel.getRepos().getValue();
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
        holder.bindRepo(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    repositoriesFiltered = repositoriesList;
                } else {
                    List<Repositorie> filteredList = new ArrayList<>();
                    for (Repositorie row : repositoriesList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    // set filtered list
                    repositoriesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = repositoriesFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                repositoriesFiltered = (ArrayList<Repositorie>) filterResults.values;
                // publish filtered list
                publishFilteredList(repositoriesFiltered);
            }
        };
    }

    /**
     * clear data list and publish filtered list
     *
     * @param repositorieFiltered the filtered repositorie list
     */
    private void publishFilteredList(List<Repositorie> repositorieFiltered) {
        if (null != repositorieFiltered) {
            data.clear();
            data.addAll(repositorieFiltered);
        }
        notifyDataSetChanged();
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
         * @param repo
         */
        void bindRepo(Repositorie repo) {
            this.repositorie = repo;
            binding.tvRepoName.setText(repositorie.getName());
            binding.tvRepoDescription.setText(repositorie.getDescription());
        }
    }
}
