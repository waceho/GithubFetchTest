package com.moneway.test.ui.detail;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moneway.test.R;
import com.moneway.test.data.model.Branche;
import com.moneway.test.databinding.BrancheItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class BranchListAdapter extends RecyclerView.Adapter<BranchListAdapter.BrancheViewHolder> {

    private final List<Branche> data = new ArrayList<>();

    BranchListAdapter(DetailsViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getBranches().observe(lifecycleOwner, branches -> {
            data.clear();
            if (branches != null) {
                data.addAll(branches);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public BrancheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branche_item, parent, false);
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
        private BrancheItemBinding binding;

        BrancheViewHolder(View itemView) {
            super(itemView);
            binding = bind(itemView);
        }

        /**
         * bind repositorie view item
         * @param view itemview
         * @return RepositorieItemBinding
         */
        private BrancheItemBinding bind(View view) {
            return BrancheItemBinding.bind(view);
        }

        /**
         * bind {repositorie} and set value
         * @param branche one repositorie branche
         */
        void bindRepositorie(Branche branche) {
            binding.tvBranchName.setText(branche.getName());
        }
    }
}
