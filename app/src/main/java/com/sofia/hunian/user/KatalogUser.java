package com.sofia.hunian.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sofia.hunian.R;

public class KatalogUser extends AppCompatActivity {

    ImageButton btn_home, btn_disukai, btn_profile, btn_tambah_katalog;
    LinearLayout btn_home2, btn_disukai2, btn_profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_katalog);

        btn_home = findViewById(R.id.btn_home);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_profile = findViewById(R.id.btn_profile);
        btn_home2 = findViewById(R.id.btn_home2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        btn_profile2 = findViewById(R.id.btn_profile2);
        btn_tambah_katalog = findViewById(R.id.btn_tambah_katalog);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
        btn_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
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
        btn_tambah_katalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInputHunian();
            }
        });
    }

    private void goToHome(){
        Intent a = new Intent(KatalogUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        Intent a = new Intent(KatalogUser.this, DisukaiUser.class);
        startActivity(a);
        finish();
    }
    private void goToProfile(){
        Intent a = new Intent(KatalogUser.this, ProfileUser.class);
        startActivity(a);
        finish();
    }
    private void goToInputHunian(){
        Intent a = new Intent(KatalogUser.this, InputHunianUser.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}