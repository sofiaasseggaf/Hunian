package com.sofia.hunian.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.adapter.AdapterDataKatalogHome;
import com.sofia.hunian.admin.HomeAdmin;
import com.sofia.hunian.admin.KatalogAdmin;
import com.sofia.hunian.admin.LaporanAdmin;
import com.sofia.hunian.admin.ProfileAdmin;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.utility.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeUser extends AppCompatActivity {

    TextView nama;
    RecyclerView rvUserHome;
    ImageButton btn_keranjang, btn_home, btn_katalog, btn_disukai, btn_profile;
    LinearLayout btn_home2, btn_katalog2, btn_disukai2, btn_profile2;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunian> listHunianAda = new ArrayList<>();
    AdapterDataKatalogHome itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);

        nama = findViewById(R.id.nama);
        rvUserHome = findViewById(R.id.rvUserHome);
        btn_keranjang = findViewById(R.id.btn_keranjang);
        btn_katalog = findViewById(R.id.btn_katalog);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_profile = findViewById(R.id.btn_profile);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        btn_profile2 = findViewById(R.id.btn_profile2);

        dbCenter = new DataHelper(this);
        getandsetData();

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

    private void getandsetData(){
        listHunian = dbCenter.getAllHunian();

        if(listHunian.size()>0){
            listHunianAda.clear();
            for (int i=0; i<listHunian.size(); i++){
                if (listHunian.get(i).getStatus().equalsIgnoreCase("ada")){
                    listHunianAda.add(listHunian.get(i));
                }
            }
        }

        if (listHunianAda.size()>0){
            itemList = new AdapterDataKatalogHome(listHunian);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rvUserHome.setLayoutManager(layoutManager);
            rvUserHome.setAdapter(itemList);
        }
        nama.setText(PreferenceUtils.getNama(getApplicationContext()));
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