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


public class CreateAndEditNoteFragment extends Fragment implements View.OnClickListener, Parcelable {

    Button saveButton;
    EditText nameOfNote;
    EditText textOfNote;
    String name;
    boolean isEditNow = false;
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

        args.putParcelableArrayList("CHANGE_THIS", list); // todo поменять ключ
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View view) {

        if (getArguments() != null) list = getArguments().getParcelableArrayList("CHANGE_THIS");

        if (!nameOfNote.getText().toString().equals("")) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("CHANGE_THIS", CreateAndEditNoteFragment.this); // TODO: 03.06.2022 изменить ключ

            if (list != null) {
                name = nameOfNote.getText().toString();
                if (isEditNow) { list.remove(myId);
                    isEditNow = false;}
                list.add(CreateAndEditNoteFragment.this);
            }

            ListOfNotesFragment listOfNotesFragment = ListOfNotesFragment.newInstance(list);
            if (getActivity() != null)
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listOfNotesFragment).commit();
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



























