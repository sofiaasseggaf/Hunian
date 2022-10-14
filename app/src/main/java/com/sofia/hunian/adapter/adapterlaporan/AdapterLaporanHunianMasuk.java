package com.sofia.hunian.adapter.adapterlaporan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDetail;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelHunianMasuk;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterLaporanHunianMasuk extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ModelHunianMasuk> dataItemList;
    List<ModelHunian> listHunian;

    public AdapterLaporanHunianMasuk(List<ModelHunianMasuk> dataItemList, List<ModelHunian> listHunian) {
        this.dataItemList = dataItemList;
        this.listHunian = listHunian;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_laporan_hunian_masuk, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        for (int i=0; i<listHunian.size(); i++){
            if (listHunian.get(i).getId_hunian()==dataItemList.get(position).getId_hunian());
            ((Penampung)holder).nama.setText(listHunian.get(position).getNama_hunian());
            String a = checkDesimal(String.valueOf(listHunian.get(position).getHarga_hunian()));
            ((Penampung)holder).harga.setText("Harga : Rp "+a);
            ((Penampung)holder).kota.setText(listHunian.get(position).getKota_hunian());
            ((Penampung)holder).tgl.setText(listHunian.get(position).getCreated_date());
            break;
        }
    }

    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama, harga, kota, tgl;
        public Penampung(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            kota = itemView.findViewById(R.id.kota);
            tgl = itemView.findViewById(R.id.tgl);
        }
        @Override
        public void onClick(View v) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + nama.getText());
        }
    }

    private String checkDesimal(String a){
        DecimalFormat formatter;
        formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator('.');
        formatter = new DecimalFormat("###,###.##", symbols);

        if(a!=null || !a.equalsIgnoreCase("")){
            if(a.length()>3){
                a = formatter.format(Double.valueOf(a));
            }
        }
        return a;
    }
}
