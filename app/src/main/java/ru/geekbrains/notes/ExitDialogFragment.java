package ru.geekbrains.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ExitDialogFragment extends DialogFragment {

    public static final String TAG = "ExitDialogFragment";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = requireActivity();
        return new AlertDialog.Builder(activity).setTitle("AlertDialog").setMessage("Вы уверены, что хотите закрыть приложение?").setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(requireContext(), "Приложение закрыто", Toast.LENGTH_SHORT).show();
                activity.finishAffinity();
//                System.exit(0);
            }
        }).setNegativeButton("Нет", null).create();
    }
}
