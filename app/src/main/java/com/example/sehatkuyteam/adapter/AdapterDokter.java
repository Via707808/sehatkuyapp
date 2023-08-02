package com.example.sehatkuyteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sehatkuyteam.DetailDokter;
import com.example.sehatkuyteam.R;
import com.example.sehatkuyteam.dataactivity.DataDokter;
import com.example.sehatkuyteam.dataactivity.DataTopik;

import java.util.ArrayList;
import java.util.List;

public class AdapterDokter extends RecyclerView.Adapter<AdapterDokter.ViewHolder> {

    private Context ctx;
    private List<DataDokter> listTopik;

    public AdapterDokter(Context ctx, List<DataDokter> listTopik) {
        this.ctx = ctx;
        this.listTopik = listTopik;
    }

    @NonNull
    @Override
    public AdapterDokter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_dokter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDokter.ViewHolder holder, int position) {
        DataDokter dataTopik = listTopik.get(position);

        holder.tvNamaDokter.setText(dataTopik.getNamaDokter());
        holder.tvBiayaDOkter.setText(dataTopik.getBiayaDokter());

        Glide.with(ctx).load(dataTopik.getGambarDokter()).into(holder.ivDokter);

        holder.rlDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.startActivity(new Intent(ctx, DetailDokter.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTopik.size();
    }

    public void SearchDataList(ArrayList<DataDokter> listSearch) {
        listTopik = listSearch;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlDokter;
        private ImageView ivDokter;
        private TextView tvNamaDokter, tvBiayaDOkter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivDokter = itemView.findViewById(R.id.civ_profil_dokter);
            tvNamaDokter = itemView.findViewById(R.id.tv_namadokter);
            tvBiayaDOkter = itemView.findViewById(R.id.tv_harga);
            rlDokter = itemView.findViewById(R.id.rl_main_dokter);
        }
    }
}
