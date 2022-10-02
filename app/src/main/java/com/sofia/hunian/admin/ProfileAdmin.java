package com.sofia.hunian.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sofia.hunian.R;

public class ProfileAdmin extends AppCompatActivity {

    ImageButton btn_home, btn_katalog, btn_disukai;
    LinearLayout btn_home2, btn_katalog2, btn_disukai2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);


        btn_home = findViewById(R.id.btn_home);
        btn_katalog = findViewById(R.id.btn_katalog);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_home2 = findViewById(R.id.btn_home2);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);

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
    }

    private void goToHome(){
        Intent a = new Intent(ProfileAdmin.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        // kasih alert suruh buat akun user
    }
    private void goToKatalog(){
        Intent a = new Intent(ProfileAdmin.this, KatalogAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}