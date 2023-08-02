package com.example.sehatkuyteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sehatkuyteam.R;
import com.example.sehatkuyteam.dataactivity.DataObat;

import java.util.ArrayList;
import java.util.List;

public class AdapterObat extends RecyclerView.Adapter<AdapterObat.ViewHolder> {

    private Context ctx;
    private List<DataObat> listTopik;

    public AdapterObat(Context ctx, List<DataObat> listTopik) {
        this.ctx = ctx;
        this.listTopik = listTopik;
    }

    @NonNull
    @Override
    public AdapterObat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_obat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterObat.ViewHolder holder, int position) {
        DataObat dataObat = listTopik.get(position);

        holder.tvNamaObat.setText(dataObat.getNamaObat());
        holder.tvHargaPer.setText(dataObat.getHargaPer());
        holder.tvHarga.setText(dataObat.getHarga());
        holder.tvKategori.setText(dataObat.getKategori());
        holder.tvDiskon.setText(dataObat.getDiskon());
        holder.tvDibeli.setText(dataObat.getDibeli());

        Glide.with(ctx).load(dataObat.getGambarObat()).into(holder.ivGambarObat);
    }

    @Override
    public int getItemCount() {
        return listTopik.size();
    }

    public void SearchDataList(ArrayList<DataObat> listSearch) {
        listTopik = listSearch;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGambarObat;
        private TextView tvNamaObat, tvHargaPer, tvHarga, tvKategori, tvDiskon, tvDibeli;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaObat = itemView.findViewById(R.id.tv_namaobat);
            tvHargaPer = itemView.findViewById(R.id.tv_kotak);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvDibeli = itemView.findViewById(R.id.tv_jumlah_dibeli);
            tvKategori = itemView.findViewById(R.id.tv_vitamin);
            tvDiskon = itemView.findViewById(R.id.tv_diskon);
            ivGambarObat = itemView.findViewById(R.id.iv_obat);
        }
    }
}
