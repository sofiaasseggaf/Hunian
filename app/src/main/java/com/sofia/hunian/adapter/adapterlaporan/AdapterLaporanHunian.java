package com.sofia.hunian.adapter.adapterlaporan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.adapter.AdapterDetail;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterLaporanHunian extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ModelHunian> dataItemList;
    List<ModelDetail> listDetail;
    Context context;

    public AdapterLaporanHunian(List<ModelHunian> dataItemList, List<ModelDetail> listDetail) {
        this.dataItemList = dataItemList;
        this.listDetail = listDetail;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_laporan_hunian, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Penampung)holder).nama.setText(dataItemList.get(position).getNama_hunian());
        String a = checkDesimal(String.valueOf(dataItemList.get(position).getHarga_hunian()));
        ((Penampung)holder).harga.setText("Harga : Rp "+a);
        ((Penampung)holder).kota.setText(dataItemList.get(position).getKota_hunian());
        ((Penampung)holder).ket.setText(dataItemList.get(position).getKeterangan_hunian());
        ((Penampung)holder).jml_like.setText(String.valueOf(dataItemList.get(position).getJumlah_like()));

        List<ModelDetail> listDetailNew = new ArrayList<>();
        for (int i=0; i<listDetail.size(); i++){
            if (listDetail.get(i).getId_hunian()==dataItemList.get(position).getId_hunian()){
                listDetailNew.add(listDetail.get(i));
            }
        }
        AdapterDetail itemList = new AdapterDetail(listDetailNew);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        ((Penampung)holder).rvDetail.setLayoutManager(layoutManager);
        ((Penampung)holder).rvDetail.setAdapter(itemList);
    }

    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama, harga, kota, jml_like, ket;
        public ImageButton btn_like;
        public RecyclerView rvDetail;
        public Penampung(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            kota = itemView.findViewById(R.id.kota);
            jml_like = itemView.findViewById(R.id.jml_like);
            ket = itemView.findViewById(R.id.ket);
            btn_like = itemView.findViewById(R.id.btn_like);
            rvDetail = itemView.findViewById(R.id.rvDetail);
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
