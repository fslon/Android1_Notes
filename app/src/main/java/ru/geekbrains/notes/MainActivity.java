package ru.geekbrains.notes;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //todo если зайти в редактирование заметки, повернуть экран и нажать "сохранить" то заметка запишется еще один раз (старая не удалится) [сделано]

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>(); // массив, в котором хранятся фрагменты с заметками

    private View viewOfNote; // view для контекстного меню текущей заметки (для понимания, какую именно заметку удалять)


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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//        testView = info.targetView;

        viewOfNote = v; // присваивание к viewOfNote textView заметки

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.context_action_delete:
                listOfNotes = getSupportFragmentManager().findFragmentById(R.id.container_with_notes).getArguments().getParcelableArrayList(InterfaceForListOfNotes.keyOfList); // обновление массива заметок
                LinearLayout linear = findViewById(R.id.linearLayout); // поиск layout с textView заметок
                linear.removeView(viewOfNote); // удаление textView
                listOfNotes.remove(viewOfNote.getId()); // удаление фрагмента из массива
                recreate(); // пересоздание активити для обновления списка
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
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

            case R.id.action_about: // о приложении
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.findFragmentById(R.id.container_with_edit_note) != null)
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.container_with_edit_note)).commit();
                fragmentManager.beginTransaction().addToBackStack("").replace(R.id.container_with_notes, new AboutFragment()).commit();
                return true;

            case R.id.action_exit: // выход из приложения
                finish();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}