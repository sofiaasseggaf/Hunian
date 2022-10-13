package com.sofia.hunian.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sofia.hunian.R;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.user.KatalogUser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterDataHunian extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelHunian> dataItemList;
    List<ModelDetail> listDetail;
    Context context;
    public AdapterDataHunianListener clickListener;

    public AdapterDataHunian(List<ModelHunian> dataItemList, List<ModelDetail> listDetail, AdapterDataHunianListener clickListener) {
        this.dataItemList = dataItemList;
        this.listDetail = listDetail;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_katalog, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Penampung)holder).nama.setText(dataItemList.get(position).getNama_hunian());
        String a = checkDesimal(String.valueOf(dataItemList.get(position).getHarga_hunian()));
        ((Penampung)holder).harga.setText("Harga : Rp "+a);
        ((Penampung)holder).kota.setText(dataItemList.get(position).getKota_hunian());
        ((Penampung)holder).ket.setText(dataItemList.get(position).getKeterangan_hunian());
        ((Penampung)holder).jml_like.setText(String.valueOf(dataItemList.get(position).getJumlah_like()));
        if (!dataItemList.get(position).getImage_hunian().equalsIgnoreCase("")){
            String encodedImage = dataItemList.get(position).getImage_hunian();
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ((Penampung)holder).img.setImageBitmap(decodedByte);
        }
        ((Penampung)holder).btn_keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.keranjangOnClick(v, position, dataItemList.get(position).getId_hunian());
            }
        });
        ((Penampung)holder). btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.likeOnClick(v, position, dataItemList.get(position).getId_hunian());
            }
        });
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

    public interface AdapterDataHunianListener {

        void likeOnClick(View v, int pos, int id);

        void keranjangOnClick(View v, int pos, int id);

    }


    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama, harga, kota, jml_like, ket;
        public ImageView img;
        public ImageButton btn_keranjang, btn_like;
        public RecyclerView rvDetail;
        public Penampung(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            kota = itemView.findViewById(R.id.kota);
            jml_like = itemView.findViewById(R.id.jml_like);
            ket = itemView.findViewById(R.id.ket);
            img = itemView.findViewById(R.id.img);
            btn_keranjang = itemView.findViewById(R.id.btn_keranjang);
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
