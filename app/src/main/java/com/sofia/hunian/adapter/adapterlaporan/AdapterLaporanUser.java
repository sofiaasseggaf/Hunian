package com.sofia.hunian.adapter.adapterlaporan;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sofia.hunian.R;
import com.sofia.hunian.model.ModelUser;

import java.util.List;

public class AdapterLaporanUser extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelUser> dataItemList;

    public AdapterLaporanUser(List<ModelUser> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_laporan_user, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Penampung)holder).nama.setText(dataItemList.get(position).getNama());
        ((Penampung)holder).alamat.setText(dataItemList.get(position).getAlamat());
        ((Penampung)holder).telepon.setText(String.valueOf(dataItemList.get(position).getTelepon()));
        ((Penampung)holder).jml_beli.setText("Jumlah Pembelian :"+dataItemList.get(position).getJumlah_pembelian());
        ((Penampung)holder).jml_supply.setText("Jumlah Supply :"+dataItemList.get(position).getJumlah_supply());
    }


    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama, alamat, telepon, jml_supply, jml_beli;
        public Penampung(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            telepon = itemView.findViewById(R.id.telepon);
            jml_supply = itemView.findViewById(R.id.jml_supply);
            jml_beli = itemView.findViewById(R.id.jml_beli);
        }
        @Override
        public void onClick(View v) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + nama.getText());
        }
    }
}
