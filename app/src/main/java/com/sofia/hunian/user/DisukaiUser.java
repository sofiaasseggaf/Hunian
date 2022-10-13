package com.sofia.hunian.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataDisukai;
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelDisukai;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.utility.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class DisukaiUser extends AppCompatActivity {

    ImageButton btn_home, btn_katalog, btn_profile;
    LinearLayout btn_home2, btn_katalog2, btn_profile2;
    RecyclerView rvDisukai;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunian> listHunianDisukai = new ArrayList<>();
    List<ModelDisukai> listDisukai;
    AdapterDataDisukai itemList;

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
        rvDisukai = findViewById(R.id.rvDisukai);

        dbCenter = new DataHelper(this);
        getDataHunian();

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

    private void getDataHunian(){
        listHunian = dbCenter.getAllHunian();
        listDisukai = dbCenter.getAllDisukai();
        listHunianDisukai.clear();

        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        for(int i=0; i<listDisukai.size(); i++){
            if (listDisukai.get(i).getId_user()==id_user){
                for (int j=0; j<listHunian.size(); j++){
                    if (listDisukai.get(i).getId_hunian()==listHunian.get(j).getId_hunian()){
                        listHunianDisukai.add(listHunian.get(j));
                    }
                }
            }
        }

        if (listHunianDisukai.size()>0){
            itemList = new AdapterDataDisukai(listHunianDisukai);
            rvDisukai.setLayoutManager(new LinearLayoutManager(DisukaiUser.this));
            rvDisukai.setAdapter(itemList);
        }

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