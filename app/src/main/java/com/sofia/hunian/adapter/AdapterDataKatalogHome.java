package com.sofia.hunian.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sofia.hunian.R;
import com.sofia.hunian.model.ModelHunian;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterDataKatalogHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelHunian> dataItemList;

    public AdapterDataKatalogHome(List<ModelHunian> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_katalog_home, parent, false);
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
        if (!dataItemList.get(position).getImage_hunian().equalsIgnoreCase("")){
            String encodedImage = dataItemList.get(position).getImage_hunian();
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ((Penampung)holder).img.setImageBitmap(decodedByte);
        }
    }


    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama, harga, kota;
        public ImageView img;
        public Penampung(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            kota = itemView.findViewById(R.id.kota);
            img = itemView.findViewById(R.id.img);

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
