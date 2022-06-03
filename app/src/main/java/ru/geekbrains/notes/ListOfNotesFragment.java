package ru.geekbrains.notes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ListOfNotesFragment extends Fragment implements View.OnClickListener {
    Button btnCreateNewNote;
    ArrayList<CreateAndEditNoteFragment> listOfNotes;

    public static ListOfNotesFragment newInstance(ArrayList<CreateAndEditNoteFragment> list) {
        ListOfNotesFragment fragment = new ListOfNotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setListOfNotes(list);
        return fragment;
    }

    public void setListOfNotes(ArrayList<CreateAndEditNoteFragment> listOfNotes) {
        this.listOfNotes = listOfNotes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (listOfNotes != null) {
//            listOfNotes.add(getArguments().getParcelable("CHANGE_THIS"));
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false);
    }

    private void displayPreviousNotes(View view) {

        Context themeForList = new ContextThemeWrapper(getActivity().getBaseContext(), R.style.ThemeForList);

        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        if (listOfNotes != null) {
            for (int i = 0; i < listOfNotes.toArray().length; i++) {
                if (listOfNotes.get(i).getName() != null) {
                    TextView textView = new TextView(themeForList);
                    Log.d("*****   ", String.valueOf(textView.getId()));
                    textView.setText(listOfNotes.get(i).getName());
                    if (textView.getId() == -1)textView.setId(i);
                    Log.d("99999999   ", String.valueOf(textView.getId()));
//                    textView.setTextSize(30);

//                    textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.greyForList));
                    linearLayout.addView(textView);
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayPreviousNotes(view);
        initViews(view);
    }

    private void initViews(View view) {
        btnCreateNewNote = view.findViewById(R.id.createNewNote);
        btnCreateNewNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {     //TODO 1

        CreateAndEditNoteFragment createAndEditNoteFragment = CreateAndEditNoteFragment.newInstance(listOfNotes);
        if (getActivity() != null)
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).commit();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).addToBackStack("").commit();
    }
}





















