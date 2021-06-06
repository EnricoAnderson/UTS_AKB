package com.example.uts_akb;
//10118353_Enrico Anderson_IF8
//dibuat 6 Juni 2021

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyVew> {

    ArrayList<Data> list;
    Dialog customDialog;
    Activity ac1;
    View view;
    DataHelper db;

    public DailyAdapter(ArrayList<Data> list, Activity ac, View view1){
        this.list = list;
        this.ac1 = ac;
        this.view = view1;
        db = new DataHelper(ac1.getApplicationContext());
        db.getReadableDatabase();
    }

    @NonNull
    @Override
    public DailyVew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music,parent,false);

        customDialog = new Dialog(ac1);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.fragment_gallery3);
        customDialog.setCancelable(true);



        return new DailyVew(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DailyVew holder, int position) {
        Data data = list.get(position);
        holder.title.setText(data.judul);

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

        String inputDateStr="2011-01-11";
        Date date = null;
        try {
            date = inputFormat.parse(data.tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);

        holder.tanggal.setText(outputDateStr);

        holder.layout.setOnClickListener(v -> {
            EditText dialogJudul = customDialog.findViewById(R.id.tambahJudulDiary);
            EditText dialogKategori = customDialog.findViewById(R.id.tambahKategoriDiary);
            EditText dialogIsi = customDialog.findViewById(R.id.tambahIsiDiary);

            dialogJudul.setText(data.judul);
            dialogKategori.setText(data.kategori);
            dialogIsi.setText(data.isi);

            Button delete = customDialog.findViewById(R.id.btnDelete);
            delete.setOnClickListener(t -> {
                db.hapusDataDiary(data.id);

                Snackbar bar = Snackbar.make(view,
                        "Data Dihapus Refresh page terlebih dahulu!",
                        Snackbar.LENGTH_LONG);
                View snackView = bar.getView();
                TextView tv = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
                tv.setMaxLines(4);
                bar.setDuration(3000).setAction("Action", null).show();

                customDialog.dismiss();

            });

            Button edit = customDialog.findViewById(R.id.btnEdit);
            edit.setOnClickListener(t -> {
                db.editDataDiary(data.id, dialogJudul.getText().toString(),dialogKategori.getText().toString(), dialogIsi.getText().toString() );


                Snackbar bar = Snackbar.make(view,
                        "Data Diedit Refresh page terlebih dahulu!",
                        Snackbar.LENGTH_LONG);
                View snackView = bar.getView();
                TextView tv = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
                tv.setMaxLines(4);
                bar.setDuration(3000).setAction("Action", null).show();

                customDialog.dismiss();
            });

            customDialog.show();

        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class DailyVew extends RecyclerView.ViewHolder {

        TextView title,tanggal;
        LinearLayout layout;

        DailyVew(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.judul);
            tanggal = itemView.findViewById(R.id.tanggal);
            layout = itemView.findViewById(R.id.btnTanggal);
        }
    }
}
