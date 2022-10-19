package ru.geekbrains.notes;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class CreateAndEditNoteFragment extends Fragment implements View.OnClickListener, Parcelable, InterfaceForListOfNotes {

    Button saveButton; // кнопка "сохранить"
    EditText nameOfNote; // поле с названием заметки
    EditText textOfNote; // поле с текстом заметки
    String name; // название заметки
    boolean isEditNow = false; // редактируется ли заметка сейчас
    boolean isEditNowForSnack = false;
    int myId; // для редактирования (чтобы перезаписывалась именно эта заметка)
    String timeOfCreation; // время создания/редактирования заметки

    public String getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(String timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    ArrayList<CreateAndEditNoteFragment> list;

    protected CreateAndEditNoteFragment(Parcel in) {
    }

    public static final Creator<CreateAndEditNoteFragment> CREATOR = new Creator<CreateAndEditNoteFragment>() {
        @Override
        public CreateAndEditNoteFragment createFromParcel(Parcel in) {
            return new CreateAndEditNoteFragment(in);
        }

        @Override
        public CreateAndEditNoteFragment[] newArray(int size) {
            return new CreateAndEditNoteFragment[size];
        }
    };

    public CreateAndEditNoteFragment() {

    }

    public String getName() {
        return name;
    }

    public static CreateAndEditNoteFragment newInstance(ArrayList<CreateAndEditNoteFragment> list) {
        CreateAndEditNoteFragment fragment = new CreateAndEditNoteFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(InterfaceForListOfNotes.keyOfList, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_create_and_edit_note, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        MenuItem searchItem = menu.findItem(R.id.action_search); // кнопка "искать" в тулбаре
        if (searchItem != null & getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            searchItem.setVisible(true); // показать кнопку поиска в этом фрагменте (если ориентация альбомная)

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        saveButton = view.findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(this);
        nameOfNote = view.findViewById(R.id.nameOfNoteEditText);
        textOfNote = view.findViewById(R.id.textOfNoteEditText);
    }

    @Override
    public void onClick(View view) { // кнопка "сохранить"

        if (getArguments() != null) list = getArguments().getParcelableArrayList(InterfaceForListOfNotes.keyOfList);

        if (!nameOfNote.getText().toString().equals("")) {
            if (list != null) {
                name = nameOfNote.getText().toString();

                if (isEditNowForSnack) {
                    showSnackBar("Заметка изменена");
                    isEditNowForSnack = false;
                } else showSnackBar("Создана новая заметка");


                for (int i = 0; i < list.toArray().length; i++) {
                    if (isEditNow & list.get(i).getName().equals(list.get(myId).getName())) { // если заметка в данный момент редактируется  и в списке заметок есть заметка с таким же id, то последняя удаляется
                        list.remove(i);
                        isEditNow = false;
                    } else if (list.get(i).getName().equals(name)) // если в списке заметок есть заметка с таким же названием, то она удаляется
                        list.remove(i);
                }
                setTimeOfCreation(DateFormat.getTimeInstance().format(Calendar.getInstance().getTime())); // добавляет текущее время
                list.add(CreateAndEditNoteFragment.this);
//

            }
////            ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(list);
//            RecyclerViewWithNotesFragment recyclerViewWithNotesFragment = RecyclerViewWithNotesFragment.newInstance(list);

            if (getActivity() != null)
//                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, listOfNotesFragment).commit();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, getParentFragmentManager().findFragmentByTag("recyclerFragment")).commit();
        } else {
            Toast toast = Toast.makeText(getContext(), "Введите название заметки", Toast.LENGTH_SHORT);
            toast.show();
        }

        hideKeyboard();
    }

    private void showSnackBar(String text) {
        Snackbar.make(requireActivity().findViewById(R.id.parent_linear_layout), text, Snackbar.LENGTH_LONG).show();
    }

    private void hideKeyboard() { // спрятать клавиатуру при переходе в другой фрагмент
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}



























