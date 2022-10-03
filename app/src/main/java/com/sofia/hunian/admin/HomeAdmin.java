package com.sofia.hunian.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sofia.hunian.R;
import com.sofia.hunian.starter.SignIn;

public class HomeAdmin extends AppCompatActivity {

    TextView txt_nama;
    ViewPager viewPagerKatalog;;
    ImageButton btn_laporan, btn_katalog, btn_disukai, btn_profile;
    LinearLayout btn_katalog2, btn_disukai2, btn_profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);

        txt_nama = findViewById(R.id.txt_nama);
        viewPagerKatalog = findViewById(R.id.viewPagerKatalog);
        btn_laporan = findViewById(R.id.btn_laporan);
        btn_katalog = findViewById(R.id.btn_katalog);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_profile = findViewById(R.id.btn_profile);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        btn_profile2 = findViewById(R.id.btn_profile2);

        btn_katalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToKatalog();
            }
        });
        btn_katalog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToKatalog();
            }
        });
        btn_disukai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDisukai();
            }
        });
        btn_disukai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDisukai();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
        btn_profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
        btn_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLaporan();
            }
        });

    }

    private void goToKatalog(){
        Intent a = new Intent(HomeAdmin.this, KatalogAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        // kasih alert suruh buat akun user
    }
    private void goToProfile(){
        Intent a = new Intent(HomeAdmin.this, ProfileAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToLaporan(){
        Intent a = new Intent(HomeAdmin.this, LaporanAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda Mau Menutup Aplikasi")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        HomeAdmin.super.onBackPressed();
                        finish();
                        finishAffinity();
                    }
                })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

}