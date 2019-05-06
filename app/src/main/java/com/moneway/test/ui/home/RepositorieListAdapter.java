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

/**
 * repositories adapter
 */
public class RepositorieListAdapter extends RecyclerView.Adapter<RepositorieListAdapter.RepoViewHolder> implements Filterable {

    private final List<Repositorie> data = new ArrayList<>();
    private RepositorieSelectListener repositorieSelectListener;
    private List<Repositorie> repositorieList;
    private List<Repositorie> repositorieFiltered;

    RepositorieListAdapter(HomeViewModel viewModel, LifecycleOwner lifecycleOwner, RepositorieSelectListener repositorieSelectListener) {
        this.repositorieSelectListener = repositorieSelectListener;

        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
                this.repositorieList = viewModel.getRepos().getValue();
                this.repositorieFiltered = viewModel.getRepos().getValue();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    repositorieFiltered = repositorieList;
                } else {
                    List<Repositorie> filteredList = new ArrayList<>();
                    for (Repositorie row : repositorieList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    repositorieFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = repositorieFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                repositorieFiltered = (ArrayList<Repositorie>) filterResults.values;
                if (null != repositorieFiltered)
                    publishFilteredList();
            }
        };
    }

    /**
     * publish the filtered list
     */
    private void publishFilteredList() {
        data.clear();
        data.addAll(repositorieFiltered);
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
         * @param repositorie
         */
        void bindRepositorie(Repositorie repositorie) {
            this.repositorie = repositorie;
            binding.tvRepoName.setText(repositorie.getName());
            binding.tvRepoDescription.setText(repositorie.getDescription());
        }
    }
}
