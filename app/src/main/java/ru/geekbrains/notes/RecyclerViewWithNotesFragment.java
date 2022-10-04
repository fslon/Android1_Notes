package ru.geekbrains.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewWithNotesFragment extends Fragment {

    private ArrayList<CreateAndEditNoteFragment> list = new ArrayList<>();
    private CardsSource data;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;


    public static RecyclerViewWithNotesFragment newInstance(ArrayList<CreateAndEditNoteFragment> list) {
        RecyclerViewWithNotesFragment fragment = new RecyclerViewWithNotesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(InterfaceForListOfNotes.keyOfList, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view_with_notes, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        initView();
        setHasOptionsMenu(true);
        return view;
    }

    private void initView() {
        list = getArguments().getParcelableArrayList(InterfaceForListOfNotes.keyOfList);
        data = new CardsSourceImpl(getResources()).init();
        if (recyclerView != null) initRecyclerView();
    }

    private void initRecyclerView() {
//        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NotesAdapter(list, data, this);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CreateAndEditNoteFragment createAndEditNoteFragment = list.get(position);

                if (getActivity() != null)
                    startFragmentDependingOnOrientation(createAndEditNoteFragment);
            }
        });


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        Button btnCreateNewNote; // кнопка "+" (создание новой заметки)
        btnCreateNewNote = view.findViewById(R.id.createNewNote);


        btnCreateNewNote.setOnClickListener(view1 -> {
            CreateAndEditNoteFragment createAndEditNoteFragment = CreateAndEditNoteFragment.newInstance(list);
            if (getActivity() != null) startFragmentDependingOnOrientation(createAndEditNoteFragment);


        });

        recyclerView.scrollToPosition(list.size() - 1);


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_card:

                data.addCardData(new CardData(R.drawable.card1, "Созданная карточка " + data.size()));

                list.add(new CreateAndEditNoteFragment());

                adapter.notifyItemInserted(data.size() - 1);
                recyclerView.scrollToPosition(data.size() - 1);
                return true;
            case R.id.action_delete_all:
                data.clearCardData();
                list.clear();
                adapter.notifyDataSetChanged();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }


    private void startFragmentDependingOnOrientation(CreateAndEditNoteFragment createAndEditNoteFragment) { // запуск фрагмента в зависимости от ориентации экрана
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, createAndEditNoteFragment).addToBackStack("").commit();
        else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_edit_note, createAndEditNoteFragment).commit();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_for_recycler, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch (item.getItemId()) {

            case R.id.recycler_context_item_change:
                data.updateCardData(position, new CardData(R.drawable.card10,data.getCardData(position).getTitle() ));
                adapter.notifyItemChanged(position);
                return true;

            case R.id.recycler_context_item_remove:
                data.deleteCardData(position);
                list.remove(position);
                adapter.notifyItemRemoved(position);
                return true;

        }


        return super.onContextItemSelected(item);
    }
}


























