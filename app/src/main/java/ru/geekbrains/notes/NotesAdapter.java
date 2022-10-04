package ru.geekbrains.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> { // 2 шаг

    ArrayList<CreateAndEditNoteFragment> list;
    private final CardsSource dataSource;
    private OnItemClickListener itemClickListener;
    private final Fragment fragment; // фрагмент для контекстного меню
    private int menuPosition; // для контекст меню

    public NotesAdapter(ArrayList<CreateAndEditNoteFragment> list, CardsSource dataSource, Fragment fragment) {

        this.dataSource = dataSource;
        this.list = list;
        this.fragment = fragment;
    }

    public int getMenuPosition() {
        return menuPosition;
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


        holder.setData(dataSource.getCardData(position));
    }


    @Override
    public int getItemCount() {
        return list.toArray().length;
        //return dataSource.size();
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder { // 1 шаг

        private final TextView nameView;
        private final TextView dateView;
        private final TextView title;
        private final AppCompatImageView avatarOfNote;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.recycler_item_name);
            dateView = itemView.findViewById(R.id.recycler_item_date);

            title = itemView.findViewById(R.id.number_of_note_for_card);
            avatarOfNote = itemView.findViewById(R.id.avatar_recycler_for_card);

            registerContextMenu(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener!=null){
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

           itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   menuPosition = getLayoutPosition();
                   itemView.showContextMenu();
                   return true;
               }
           });

        }

        private void registerContextMenu(View itemView) {
            if (fragment != null) {

                fragment.registerForContextMenu(itemView);
            }
        }

        public void setData(CardData cardData){
            title.setText(cardData.getTitle());
            avatarOfNote.setImageResource(cardData.getPicture());
        }


        public TextView getNameView() {
            return nameView;
        }

        public TextView getDateView() {
            return dateView;
        }
    }
}
