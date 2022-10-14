package com.sofia.hunian.admin.laporan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanHunian;
import com.sofia.hunian.admin.KatalogAdmin;
import com.sofia.hunian.admin.LaporanAdmin;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;

import java.util.List;

public class LaporanDataHunian extends AppCompatActivity {

    RecyclerView rvLaporanDataHunian;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelDetail> listDetail;
    AdapterLaporanHunian itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_laporan_data_hunian);

        rvLaporanDataHunian = findViewById(R.id.rvLaporanDataHunian);

        dbCenter = new DataHelper(this);
        getDataHunian();
    }

    private void getDataHunian(){

        listHunian = dbCenter.getAllHunian();
        listDetail = dbCenter.getAllDetail();

        if (listHunian.size()>0){
            itemList = new AdapterLaporanHunian(listHunian, listDetail);
            rvLaporanDataHunian.setLayoutManager(new LinearLayoutManager(LaporanDataHunian.this));
            rvLaporanDataHunian.setAdapter(itemList);
        }

    }

    private void goToLaporan(){
        Intent a = new Intent(LaporanDataHunian.this, LaporanAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToLaporan();
    }
}