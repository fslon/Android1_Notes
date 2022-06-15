package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //todo если зайти в редактирование заметки, повернуть экран и нажать "сохранить" то заметка запишется еще один раз (старая не удалится)

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>(); // массив, в котором хранятся фрагменты с заметками

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