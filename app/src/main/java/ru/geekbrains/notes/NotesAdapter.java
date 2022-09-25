package ru.geekbrains.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> { // 2 шаг
    ArrayList<CreateAndEditNoteFragment> list;

    public NotesAdapter(ArrayList<CreateAndEditNoteFragment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getNameView().setText(list.get(position).getName());
        holder.getDateView().setText(list.get(position).getTimeOfCreation());

    }


    @Override
    public int getItemCount() {
        return list.toArray().length;
    }


    class ViewHolder extends RecyclerView.ViewHolder { // 1 шаг

        private final TextView nameView;
        private final TextView dateView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.recycler_item_name);
            dateView = itemView.findViewById(R.id.recycler_item_date);
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getDateView() {
            return dateView;
        }
    }
}
