package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IntefaceWithList{
    // насчет переопределений всяких методов по типу saveInstanceState

    //todo сделать кликлисенер на текствью
    //todo сделать через newInstance (setArguments)

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CreateAndEditNoteFragment createAndEditNoteFragment = new CreateAndEditNoteFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).commit();

        if (savedInstanceState != null) listOfNotes = savedInstanceState.getParcelableArrayList("CHANGE");

        ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(listOfNotes);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listOfNotesFragment).commit();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        listOfNotes = IntefaceWithList.list;
        outState.putParcelableArrayList("CHANGE", IntefaceWithList.list);

        super.onSaveInstanceState(outState);
    }
}