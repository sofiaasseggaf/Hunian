package com.sofia.hunian.admin.laporan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanHunian;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanHunianMasuk;
import com.sofia.hunian.admin.LaporanAdmin;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelHunianMasuk;

import java.util.List;

public class LaporanDataHunianMasuk extends AppCompatActivity {

    RecyclerView rvLaporanDataHunianMasuk;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunianMasuk> listHunianMasuk;
    AdapterLaporanHunianMasuk itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_laporan_data_hunian_masuk);

        rvLaporanDataHunianMasuk = findViewById(R.id.rvLaporanDataHunianMasuk);

        dbCenter = new DataHelper(this);
        getData();
    }

    private void getData(){
        listHunianMasuk = dbCenter.getAllHunianMasuk();
        listHunian = dbCenter.getAllHunian();

        if (listHunianMasuk.size()>0){
            itemList = new AdapterLaporanHunianMasuk(listHunianMasuk, listHunian);
            rvLaporanDataHunianMasuk.setLayoutManager(new LinearLayoutManager(LaporanDataHunianMasuk.this));
            rvLaporanDataHunianMasuk.setAdapter(itemList);
        }
    }

    private void goToLaporan(){
        Intent a = new Intent(LaporanDataHunianMasuk.this, LaporanAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToLaporan();
    }
}