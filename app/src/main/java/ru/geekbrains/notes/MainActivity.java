package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CreateAndEditNoteFragment createAndEditNoteFragment = new CreateAndEditNoteFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).commit();

        ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(listOfNotes);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listOfNotesFragment).commit();


    }

    public void test() {

    }

}