package com.sofia.hunian.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sofia.hunian.R;

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
        btn_home = findViewById(R.id.btn_home);
        btn_katalog = findViewById(R.id.btn_katalog);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_profile = findViewById(R.id.btn_profile);
        btn_home2 = findViewById(R.id.btn_home2);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        btn_profile2 = findViewById(R.id.btn_profile2);


    }
}