package com.sofia.hunian.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sofia.hunian.R;

public class KeranjangUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_keranjang);
    }

    private void goToHome(){
        Intent a = new Intent(KeranjangUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}