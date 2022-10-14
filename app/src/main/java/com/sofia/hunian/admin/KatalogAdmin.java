package com.sofia.hunian.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.user.KatalogUser;

import java.util.ArrayList;
import java.util.List;

public class KatalogAdmin extends AppCompatActivity {

    ImageButton btn_home, btn_disukai, btn_profile;
    LinearLayout btn_home2, btn_disukai2, btn_profile2;
    RecyclerView rvKatalog;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunian> listHunianAda = new ArrayList<>();
    List<ModelDetail> listDetail;
    AdapterDataHunian itemList;

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
        rvKatalog = findViewById(R.id.rvKatalog);


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

    private void getDataHunian(){

        listHunian = dbCenter.getAllHunian();
        listDetail = dbCenter.getAllDetail();

        if(listHunian.size()>0){
            listHunianAda.clear();
            for (int i=0; i<listHunian.size(); i++){
                if (listHunian.get(i).getStatus().equalsIgnoreCase("ada")){
                    listHunianAda.add(listHunian.get(i));
                }
            }
        }

        if (listHunianAda.size()>0){
            itemList = new AdapterDataHunian(listHunianAda, listDetail, new AdapterDataHunian.AdapterDataHunianListener() {
                @Override
                public void likeOnClick(View v, int position, int id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(KatalogAdmin.this);
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

                @Override
                public void keranjangOnClick(View v, int position, int id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(KatalogAdmin.this);
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
            });
            rvKatalog.setLayoutManager(new LinearLayoutManager(KatalogAdmin.this));
            rvKatalog.setAdapter(itemList);
        }



    }

    private void goToHome(){
        Intent a = new Intent(KatalogAdmin.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        AlertDialog.Builder builder = new AlertDialog.Builder(KatalogAdmin.this);
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
        Intent a = new Intent(KatalogAdmin.this, ProfileAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}