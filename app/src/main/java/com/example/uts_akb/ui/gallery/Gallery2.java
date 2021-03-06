package com.example.uts_akb.ui.gallery;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uts_akb.DataHelper;
import com.example.uts_akb.R;
import com.google.android.material.snackbar.Snackbar;

//10118353_Enrico Anderson_IF8
//dibuat 6 Juni 2021

public class Gallery2 extends DialogFragment {

    DataHelper db;
    Button simpan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gallery2, container, false);

        simpan = root.findViewById(R.id.btnSimpan);
        Log.d("inilog", "ini log");
        db = new DataHelper(getContext());
        db.getReadableDatabase();

        simpan.setOnClickListener(v -> {
            EditText dialogJudul = root.findViewById(R.id.tambahJudulDiary);
            EditText dialogKategori = root.findViewById(R.id.tambahKategoriDiary);
            EditText dialogIsi = root.findViewById(R.id.tambahIsiDiary);


            db.simpanDataDiary(dialogJudul.getText().toString() , dialogKategori.getText().toString(), dialogIsi.getText().toString());



            Snackbar bar = Snackbar.make(root,
                    "Data ditambah Refresh page terlebih dahulu!",
                    Snackbar.LENGTH_LONG);
            View snackView = bar.getView();
            TextView tv = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
            tv.setMaxLines(4);
            bar.setDuration(3000).setAction("Action", null).show();

            dismiss();

        });



        return root;
    }
}