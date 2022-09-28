package ru.geekbrains.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
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

    ArrayList<CreateAndEditNoteFragment> list = new ArrayList<>();

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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        list = getArguments().getParcelableArrayList(InterfaceForListOfNotes.keyOfList);
        CardsSource data = new CardsSourceImpl(getResources()).init();
        if (recyclerView != null) initRecyclerView(recyclerView, list, data);

        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, ArrayList<CreateAndEditNoteFragment> list, CardsSource data) {
//        recyclerView.setHasFixedSize(true); //todo попробовать убрать
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        NotesAdapter adapter = new NotesAdapter(list,data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button btnCreateNewNote; // кнопка "+" (создание новой заметки)
        btnCreateNewNote = view.findViewById(R.id.createNewNote);
        btnCreateNewNote.setOnClickListener(view1 -> {
            CreateAndEditNoteFragment createAndEditNoteFragment = CreateAndEditNoteFragment.newInstance(list);
            if (getActivity() != null)
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, createAndEditNoteFragment).addToBackStack("").commit();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, createAndEditNoteFragment).addToBackStack("").commit();
        });



        super.onViewCreated(view, savedInstanceState);
    }
}


























