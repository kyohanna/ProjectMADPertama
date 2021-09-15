package com.example.projectpertamamad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectpertamamad.databinding.ItemTaskBinding;



public class TaskAdapter extends ListAdapter<User, TaskAdapter.ViewHolder> {

    private Context context;
    private UserListener listener;

    public TaskAdapter(Context context) {
        super(new DiffCallback());
        this.context = context;
    }

    public TaskAdapter(Context context, UserListener listener) {
        super(new DiffCallback());
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(getItem(position), listener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemTaskBinding binding;

        public ViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(User user, UserListener listener) {
            binding.nama.setText(user.getNama());
            binding.alamat.setText(user.getAlamat());
            binding.umur.setText(user.getUmur());
            binding.cardRoot.setOnClickListener(v -> {
                listener.onTaskClicked(binding.getRoot(), user, getAdapterPosition());
            });
        }
    }
}

class DiffCallback extends DiffUtil.ItemCallback<User> {
    @Override
    public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem.getNama().equals(newItem.getNama());
    }
}