package com.sofia.hunian.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sofia.hunian.R;
import com.sofia.hunian.admin.HomeAdmin;
import com.sofia.hunian.admin.KatalogAdmin;
import com.sofia.hunian.admin.LaporanAdmin;
import com.sofia.hunian.admin.ProfileAdmin;

public class HomeUser extends AppCompatActivity {

    TextView txt_nama;
    HorizontalScrollView hsv_katalog;
    ImageButton btn_keranjang, btn_home, btn_katalog, btn_disukai, btn_profile;
    LinearLayout btn_home2, btn_katalog2, btn_disukai2, btn_profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);

        txt_nama = findViewById(R.id.txt_nama);
        hsv_katalog = findViewById(R.id.hsv_katalog);
        btn_keranjang = findViewById(R.id.btn_keranjang);
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
        btn_keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToKeranjang();
            }
        });

    }

    private void goToKatalog(){
        Intent a = new Intent(HomeUser.this, KatalogUser.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        Intent a = new Intent(HomeUser.this, DisukaiUser.class);
        startActivity(a);
        finish();
    }
    private void goToProfile(){
        Intent a = new Intent(HomeUser.this, ProfileUser.class);
        startActivity(a);
        finish();
    }
    private void goToKeranjang(){
        Intent a = new Intent(HomeUser.this, KeranjangUser.class);
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
                        HomeUser.super.onBackPressed();
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