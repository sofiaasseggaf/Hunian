package com.sofia.hunian.admin.laporan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sofia.hunian.R;
import com.sofia.hunian.admin.LaporanAdmin;

public class LaporanDataHunianMasuk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_laporan_data_hunian_masuk);
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