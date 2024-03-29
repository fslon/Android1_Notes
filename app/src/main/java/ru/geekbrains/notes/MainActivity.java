package ru.geekbrains.notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //todo если зайти в редактирование заметки, повернуть экран и нажать "сохранить" то заметка запишется еще один раз (старая не удалится) [сделано]

    ArrayList<CreateAndEditNoteFragment> listOfNotes = new ArrayList<>(); // массив, в котором хранятся фрагменты с заметками

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        if (savedInstanceState == null) {
//            ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(listOfNotes);
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, listOfNotesFragment).commit();
            RecyclerViewWithNotesFragment recyclerViewWithNotesFragment = RecyclerViewWithNotesFragment.newInstance(listOfNotes);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, recyclerViewWithNotesFragment, "recyclerFragment").commit();
        }
    }

    //todo поменять цвет элементов тулбара (создать новый для светлой и темной темы)
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) { // инициализация бокового меню
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case (R.id.action_settings_navigation): //todo сделать настройки
                    Toast.makeText(getBaseContext(), "Настройки (soon)", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.close();
                    return true;
            }

            return false;
        });
    }

//    @Override
//    public void onBackPressed() {
//
//        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


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

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;

        }


        return super.onOptionsItemSelected(item);
    }


    private void showDialogFragmentOfExit() { // диалог "хотите ли вы выйти из приложения?". срабатывает при зактрытии приложения
        new ExitDialogFragment().show(getSupportFragmentManager(), ExitDialogFragment.TAG);
    }

    @Override
    public void finish() {
      showDialogFragmentOfExit();
//        super.finish();
    }
}
















