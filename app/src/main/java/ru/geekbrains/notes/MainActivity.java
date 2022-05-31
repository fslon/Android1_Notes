package ru.geekbrains.notes;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CreateAndEditNoteFragment createAndEditNoteFragment = new CreateAndEditNoteFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createAndEditNoteFragment).commit();

        ListOfNotesFragment listOfNotesFragment = new ListOfNotesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listOfNotesFragment).commit();

        Log.d("-", "test branches");

    }

    public void test(){

    }

}