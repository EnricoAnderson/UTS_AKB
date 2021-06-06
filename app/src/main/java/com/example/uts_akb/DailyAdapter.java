package com.example.uts_akb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyVew> {

    ArrayList<Data> list;

    public DailyAdapter(ArrayList<Data> list){
        this.list = list;
    }

    @NonNull
    @Override
    public DailyVew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gallery2,parent,false);
        return new DailyVew(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DailyVew holder, int position) {
        Data data = list.get(position);
        holder.title.setText(data.judul);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class DailyVew extends RecyclerView.ViewHolder {

        TextView title;

        DailyVew(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.judul);
        }
    }
}
