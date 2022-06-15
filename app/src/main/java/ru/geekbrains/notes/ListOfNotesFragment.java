package ru.geekbrains.notes;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ListOfNotesFragment extends Fragment implements View.OnClickListener, InterfaceForListOfNotes {
    Button btnCreateNewNote; // кнопка "+" (создание новой заметки)
    ArrayList<CreateAndEditNoteFragment> list = new ArrayList<>();


    public static ListOfNotesFragment newInstance(ArrayList<CreateAndEditNoteFragment> list) {
        ListOfNotesFragment fragment = new ListOfNotesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false);
    }


    private void displayPreviousNotes(View view) { // отображение списка заметок (если они есть)
        Context themeForList = new ContextThemeWrapper(getActivity().getBaseContext(), R.style.ThemeForList); // создание темы для элементов списка заметок

        list = getArguments().getParcelableArrayList(InterfaceForListOfNotes.keyOfList);

        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        if (list != null) {
            for (int i = 0; i < list.toArray().length; i++) {
                if (list.get(i).getName() != null) {
                    TextView textView = new TextView(themeForList); // добавление темы к textView с заметкой

                    textView.setOnClickListener(view1 -> { // к textView с заметкой добавляется кликлисенер, при нажатии меняется фрагмент на редактирование заметки
                        list.get(textView.getId()).isEditNow = true; // так как в этот лисенер заходим только после нажатия на существующую заметку - переход в режим редактирования
                        list.get(textView.getId()).myId = textView.getId(); // передается id(index) конкретной заметки в фрагмент с редактрованием
                        CreateAndEditNoteFragment createAndEditNoteFragment = list.get(textView.getId());
                        if (getActivity() != null)
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).addToBackStack("").commit();
                    });


                    textView.setText(list.get(i).getName());
                    if (textView.getId() == -1) textView.setId(i);
                    linearLayout.addView(textView);


                    Space space = new Space(getContext()); // пустое пространство после заметки для отделения их друг от друга //todo применить что-то по типу стиля для улучшения кода
                    space.setMinimumHeight(10);
                    linearLayout.addView(space);

                }
            }
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) displayPreviousNotes(view);
        initViews(view);
    }

    private void initViews(View view) {
        btnCreateNewNote = view.findViewById(R.id.createNewNote);
        btnCreateNewNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        CreateAndEditNoteFragment createAndEditNoteFragment = CreateAndEditNoteFragment.newInstance(list);
        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).addToBackStack("").commit();
    }
}





















