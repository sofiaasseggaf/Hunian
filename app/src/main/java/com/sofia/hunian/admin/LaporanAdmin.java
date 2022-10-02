package com.sofia.hunian.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.sofia.hunian.R;
import com.sofia.hunian.admin.laporan.LaporanDataHunian;
import com.sofia.hunian.admin.laporan.LaporanDataHunianKeluar;
import com.sofia.hunian.admin.laporan.LaporanDataHunianMasuk;
import com.sofia.hunian.admin.laporan.LaporanDataUser;

public class LaporanAdmin extends AppCompatActivity {

    ImageButton btn_laporan_data_hunian, btn_laporan_data_hunian_masuk, btn_laporan_data_hunian_keluar, btn_laporan_data_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_laporan);

        btn_laporan_data_hunian = findViewById(R.id.btn_laporan_data_hunian);
        btn_laporan_data_hunian_masuk = findViewById(R.id.btn_laporan_data_hunian_masuk);
        btn_laporan_data_hunian_keluar = findViewById(R.id.btn_laporan_data_hunian_keluar);
        btn_laporan_data_user = findViewById(R.id.btn_laporan_data_user);

        btn_laporan_data_hunian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LaporanAdmin.this, LaporanDataHunian.class);
                startActivity(a);
                finish();
            }
        });
        btn_laporan_data_hunian_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LaporanAdmin.this, LaporanDataHunianMasuk.class);
                startActivity(a);
                finish();
            }
        });
        btn_laporan_data_hunian_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LaporanAdmin.this, LaporanDataHunianKeluar.class);
                startActivity(a);
                finish();
            }
        });
        btn_laporan_data_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LaporanAdmin.this, LaporanDataUser.class);
                startActivity(a);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(LaporanAdmin.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }
}