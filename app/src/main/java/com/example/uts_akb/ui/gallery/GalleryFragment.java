package com.example.uts_akb.ui.gallery;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_akb.DailyAdapter;
import com.example.uts_akb.Data;
import com.example.uts_akb.DataHelper;
import com.example.uts_akb.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    Button btn;
    private Dialog customDialog;
    DataHelper db;
    RecyclerView recycler;
    DailyAdapter adapter;
    ArrayList<Data> data;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Log.d("inilog1", "ini log1");
        customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.fragment_gallery2);
        customDialog.setCancelable(true);


        btn=root.findViewById(R.id.popup);

        db = new DataHelper(getContext());
        db.getReadableDatabase();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gallery2 dialog = new Gallery2();
                dialog.show(getActivity().getSupportFragmentManager(), "wasd");

//                customDialog.show();
            }
        });

        data = db.tampilDataDiary();
//        data = new ArrayList<Data>();
//        data.add(new Data("1","inijudul","2-10-2000","daa","wadawdaw"));
//        data.add(new Data("2","ini2judul","22-10-2000","daa2","wa2dawdaw"));
//        data.add(new Data("3","ini3judul","23-10-2000","daa3","wa3dawdaw"));


        recycler = (RecyclerView) root.findViewById(R.id.recycler_titles);
        adapter = new DailyAdapter(data, getActivity(), root);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);



        return root;
    }


}