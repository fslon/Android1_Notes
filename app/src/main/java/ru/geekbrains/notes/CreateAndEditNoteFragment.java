package ru.geekbrains.notes;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class CreateAndEditNoteFragment extends Fragment implements View.OnClickListener, Parcelable, InterfaceForListOfNotes {

    Button saveButton; // кнопка "сохранить"
    EditText nameOfNote; // поле с названием заметки
    EditText textOfNote; // поле с текстом заметки
    String name; // название заметки
    boolean isEditNow = false; // редактируется ли заметка сейчас
    int myId; // для редактирования (чтобы перезаписывалась именно эта заметка)

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
        return inflater.inflate(R.layout.fragment_create_and_edit_note, container, false);
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
                for (int i = 0; i < list.toArray().length; i++) {

                    if (isEditNow & list.get(i).getName().equals(list.get(myId).getName())) {
                        list.remove(i);
                        isEditNow = false;
                    } else if (list.get(i).getName().equals(name))
                        list.remove(i); //todo
                }
                list.add(CreateAndEditNoteFragment.this);
            }
            ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(list);
            if (getActivity() != null)
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_with_notes, listOfNotesFragment).commit();
        } else {
            Toast toast = Toast.makeText(getContext(), "Введите название заметки", Toast.LENGTH_SHORT);
            toast.show();
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



























