package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // насчет переопределений всяких методов по типу saveInstanceState

    //todo сделать кликлисенер на текствью
    //todo сделать через newInstance (setArguments)

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(listOfNotes);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listOfNotesFragment).commit();
        }
    }

}