package com.sofia.hunian.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sofia.hunian.R;
import com.sofia.hunian.model.ModelDetail;

import java.util.List;

public class AdapterDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelDetail> dataItemList;

    public AdapterDetail(List<ModelDetail> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_detail, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Penampung)holder).detail.setText(dataItemList.get(position).getNama_detail());
    }

    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView detail;
        public Penampung(View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
        }
        @Override
        public void onClick(View v) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + detail.getText());
        }
    }
}
