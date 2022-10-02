package com.sofia.hunian.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sofia.hunian.R;

public class DisukaiUser extends AppCompatActivity {

    ImageButton btn_home, btn_katalog, btn_profile;
    LinearLayout btn_home2, btn_katalog2, btn_profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_disukai);

        btn_katalog = findViewById(R.id.btn_katalog);
        btn_home = findViewById(R.id.btn_home);
        btn_profile = findViewById(R.id.btn_profile);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_home2 = findViewById(R.id.btn_home2);
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
        Intent a = new Intent(DisukaiUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }
    private void goToKatalog(){
        Intent a = new Intent(DisukaiUser.this, KatalogUser.class);
        startActivity(a);
        finish();
    }
    private void goToProfile(){
        Intent a = new Intent(DisukaiUser.this, ProfileUser.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}