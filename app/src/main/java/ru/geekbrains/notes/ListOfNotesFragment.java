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
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ListOfNotesFragment extends Fragment implements View.OnClickListener, IntefaceWithList {
    Button btnCreateNewNote;
    //    ArrayList<CreateAndEditNoteFragment> listOfNotes;
    ArrayList<CreateAndEditNoteFragment> list = new ArrayList<>();

    public static ListOfNotesFragment newInstance(ArrayList<CreateAndEditNoteFragment> list) {
        ListOfNotesFragment fragment = new ListOfNotesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("CHANGE_THIS", list);
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


    private void displayPreviousNotes(View view) {
        Context themeForList = new ContextThemeWrapper(getActivity().getBaseContext(), R.style.ThemeForList);


         list = getArguments().getParcelableArrayList("CHANGE_THIS");

//         Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("CHANGE_THIS", list);
//         onSaveInstanceState(bundle);

        Log.d("000000",  list.toString());
        Log.d("111111",  IntefaceWithList.list.toString());
        IntefaceWithList.list.clear();

        Log.d("------",  list.toString());
        IntefaceWithList.list.addAll(list);



        Log.d("22222",  IntefaceWithList.list.toString());

//        getActivity().onSaveInstanceState(new Bundle());


        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        if (list != null) {
            for (int i = 0; i < list.toArray().length; i++) {
                if (list.get(i).getName() != null) {
                    TextView textView = new TextView(themeForList);
                    textView.setText(list.get(i).getName());
                    if (textView.getId() == -1) textView.setId(i);

//                    textView.setTextSize(30);
//                    textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.greyForList));
                    linearLayout.addView(textView);


                    Space space = new Space(getContext()); //todo сделать с этим что то
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
    public void onClick(View view) {     //TODO 1



        CreateAndEditNoteFragment createAndEditNoteFragment = CreateAndEditNoteFragment.newInstance(list);
        if (getActivity() != null)
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).commit();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).addToBackStack("").commit();
    }
}





















