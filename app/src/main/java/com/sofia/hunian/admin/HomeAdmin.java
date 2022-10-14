package com.sofia.hunian.admin;

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
import com.sofia.hunian.adapter.AdapterDataKatalogHome;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.starter.SignIn;
import com.sofia.hunian.utility.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeAdmin extends AppCompatActivity {

    RecyclerView rvAdminHome;
    ImageButton btn_laporan, btn_katalog, btn_disukai, btn_profile;
    LinearLayout btn_katalog2, btn_disukai2, btn_profile2;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunian> listHunianAda = new ArrayList<>();
    AdapterDataKatalogHome itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);

        rvAdminHome = findViewById(R.id.rvAdminHome);
        btn_laporan = findViewById(R.id.btn_laporan);
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
        btn_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLaporan();
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
            rvAdminHome.setLayoutManager(layoutManager);
            rvAdminHome.setAdapter(itemList);
        }
    }


    private void goToKatalog(){
        Intent a = new Intent(HomeAdmin.this, KatalogAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeAdmin.this);
        builder.setMessage("BUAT AKUN USER UNTUK FITUR INI")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog =builder.create();
        alertDialog.show();
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