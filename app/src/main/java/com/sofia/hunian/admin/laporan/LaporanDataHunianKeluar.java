package com.sofia.hunian.admin.laporan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanHunianKeluar;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanHunianMasuk;
import com.sofia.hunian.admin.LaporanAdmin;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelHunianKeluar;
import com.sofia.hunian.model.ModelHunianMasuk;

import java.util.List;

public class LaporanDataHunianKeluar extends AppCompatActivity {

    RecyclerView rvLaporanDataHunianKeluar;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunianKeluar> listHunianKeluar;
    AdapterLaporanHunianKeluar itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_laporan_data_hunian_keluar);

        rvLaporanDataHunianKeluar = findViewById(R.id.rvLaporanDataHunianKeluar);

        dbCenter = new DataHelper(this);
        getData();
    }

    private void getData(){
        listHunianKeluar = dbCenter.getAllHunianKeluar();
        listHunian = dbCenter.getAllHunian();

        if (listHunianKeluar.size()>0){
            itemList = new AdapterLaporanHunianKeluar(listHunianKeluar, listHunian);
            rvLaporanDataHunianKeluar.setLayoutManager(new LinearLayoutManager(LaporanDataHunianKeluar.this));
            rvLaporanDataHunianKeluar.setAdapter(itemList);
        }
    }

    private void goToLaporan(){
        Intent a = new Intent(LaporanDataHunianKeluar.this, LaporanAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToLaporan();
    }
}