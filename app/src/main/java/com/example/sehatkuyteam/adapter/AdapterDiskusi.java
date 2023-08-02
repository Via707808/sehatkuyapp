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
import com.example.sehatkuyteam.dataactivity.DataDiskusi;

import java.util.ArrayList;
import java.util.List;

public class AdapterDiskusi extends RecyclerView.Adapter<AdapterDiskusi.ViewHolder> {

    private Context ctx;
    private List<DataDiskusi> listDiskusi;

    public AdapterDiskusi(Context ctx, List<DataDiskusi> listDiskusi) {
        this.ctx = ctx;
        this.listDiskusi = listDiskusi;
    }

    @NonNull
    @Override
    public AdapterDiskusi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_diskusi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiskusi.ViewHolder holder, int position) {
        DataDiskusi dataDiskusi = listDiskusi.get(position);

        holder.tvUsername.setText(dataDiskusi.getUsername());
        holder.tvWaktu.setText(dataDiskusi.getWaktu());
        holder.tvTextKuy.setText(dataDiskusi.getTextKuy());
        holder.tvJumlahKomen.setText(dataDiskusi.getJumlahKomen());
        holder.tvJumlahLike.setText(dataDiskusi.getJumlahLike());
        holder.tvJumlahReKuy.setText(dataDiskusi.getJumlahReKuy());

        Glide.with(ctx).load(dataDiskusi.getImageKuy()).into(holder.ivDiskusi);
    }

    @Override
    public int getItemCount() {
        return listDiskusi.size();
    }

    public void SearchDataList(ArrayList<DataDiskusi> listSearch) {
        listDiskusi = listSearch;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfil, ivDiskusi;
        private TextView tvUsername, tvWaktu, tvTextKuy, tvJumlahKomen, tvJumlahLike, tvJumlahReKuy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfil = itemView.findViewById(R.id.civ_profil);
            ivDiskusi = itemView.findViewById(R.id.iv_diskusi_kuy);
            tvUsername = itemView.findViewById(R.id.tv_namauser);
            tvWaktu = itemView.findViewById(R.id.tv_waktu);
            tvTextKuy = itemView.findViewById(R.id.tv_text_kuy);
            tvJumlahKomen = itemView.findViewById(R.id.tv_komentar);
            tvJumlahLike = itemView.findViewById(R.id.tv_jumlah_like);
            tvJumlahReKuy = itemView.findViewById(R.id.tv_rekuy);
        }
    }
}
