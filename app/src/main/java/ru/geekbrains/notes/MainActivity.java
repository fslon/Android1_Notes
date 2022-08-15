package ru.geekbrains.notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //todo если зайти в редактирование заметки, повернуть экран и нажать "сохранить" то заметка запишется еще один раз (старая не удалится) [сделано]

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>(); // массив, в котором хранятся фрагменты с заметками

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(listOfNotes);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, listOfNotesFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItemSearch = menu.findItem(R.id.action_search);
        menuItemSearch.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_about:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.findFragmentById(R.id.container_with_edit_note) != null)
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.container_with_edit_note)).commit();
                fragmentManager.beginTransaction().addToBackStack("").replace(R.id.container_with_notes, new AboutFragment()).commit();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}