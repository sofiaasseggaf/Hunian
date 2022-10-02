package com.sofia.hunian.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sofia.hunian.R;

public class KatalogAdmin extends AppCompatActivity {

    ImageButton btn_home, btn_disukai, btn_profile;
    LinearLayout btn_home2, btn_disukai2, btn_profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_katalog);

        btn_home = findViewById(R.id.btn_home);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_profile = findViewById(R.id.btn_profile);
        btn_home2 = findViewById(R.id.btn_home2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        btn_profile2 = findViewById(R.id.btn_profile2);

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
    }

    private void goToHome(){
        Intent a = new Intent(KatalogAdmin.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        // kasih alert suruh buat akun user
    }
    private void goToProfile(){
        Intent a = new Intent(KatalogAdmin.this, ProfileAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}