package com.example.sehatkuyteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sehatkuyteam.R;
import com.example.sehatkuyteam.dataactivity.DataPojokKesehatan;
import com.example.sehatkuyteam.dataactivity.DataTopik;

import java.util.ArrayList;
import java.util.List;

public class AdapterPojokKesehatan extends RecyclerView.Adapter<AdapterPojokKesehatan.ViewHolder> {

    private Context ctx;
    private List<DataPojokKesehatan> listTopik;

    public AdapterPojokKesehatan(Context ctx, List<DataPojokKesehatan> listTopik) {
        this.ctx = ctx;
        this.listTopik = listTopik;
    }

    @NonNull
    @Override
    public AdapterPojokKesehatan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_topik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPojokKesehatan.ViewHolder holder, int position) {
        DataPojokKesehatan dataTopik = listTopik.get(position);

        holder.tvJudulTopik.setText(dataTopik.getJudulTopik());
        holder.tvKategoriTopik.setText(dataTopik.getKategoriTopik());
        holder.tvDeskripsiTopik.setText(dataTopik.getDeskripsiTopik());
        holder.tvJumlahKomen.setText(dataTopik.getJumlahKomen());
        holder.tvJumlahLike.setText(dataTopik.getJumlahLike());
        holder.tvJumlahReKuy.setText(dataTopik.getJumlahReKuy());
    }

    @Override
    public int getItemCount() {
        return listTopik.size();
    }

    public void SearchDataList(ArrayList<DataPojokKesehatan> listSearch) {
        listTopik = listSearch;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJudulTopik, tvKategoriTopik, tvDeskripsiTopik, tvJumlahKomen, tvJumlahLike, tvJumlahReKuy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudulTopik = itemView.findViewById(R.id.tv_tekstopik);
            tvDeskripsiTopik = itemView.findViewById(R.id.tv_deskripsitopik);
            tvKategoriTopik = itemView.findViewById(R.id.tv_kategori);
            tvJumlahKomen = itemView.findViewById(R.id.tv_komentar);
            tvJumlahLike = itemView.findViewById(R.id.tv_hati);
            tvJumlahReKuy = itemView.findViewById(R.id.tv_reply);
        }
    }
}
