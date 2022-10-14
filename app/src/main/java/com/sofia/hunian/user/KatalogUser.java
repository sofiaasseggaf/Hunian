package com.sofia.hunian.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelDisukai;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelKeranjang;
import com.sofia.hunian.starter.SplashScreen;
import com.sofia.hunian.utility.PreferenceUtils;
import com.sofia.hunian.utility.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KatalogUser extends AppCompatActivity {

    ImageButton btn_home, btn_disukai, btn_profile, btn_tambah_katalog;
    LinearLayout btn_home2, btn_disukai2, btn_profile2;
    RecyclerView rvKatalog;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunian> listHunianAda = new ArrayList<>();
    List<ModelDetail> listDetail;
    List<ModelDisukai> listDisukai;
    List<ModelKeranjang> listKeranjang;
    AdapterDataHunian itemList;
    int like = 0;
    int keranjang = 0;
    int id_disukai=0;
    String now;
    Random rand;
    int upperbound, idlike, idKeranjang;

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
        rvKatalog = findViewById(R.id.rvKatalog);
        /*btn_search = findViewById(R.id.btn_search);
        txt_search = findViewById(R.id.txt_search);*/

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
        btn_tambah_katalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInputHunian();
            }
        });
    }

    private void getDataHunian(){

        listHunian = dbCenter.getAllHunian();
        listDetail = dbCenter.getAllDetail();
        listDisukai = dbCenter.getAllDisukai();
        listKeranjang = dbCenter.getAllKeranjang();

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
                    checkLike(listHunianAda.get(position).getId_hunian(), listHunianAda.get(position).getJumlah_like());
                }

                @Override
                public void keranjangOnClick(View v, int position, int id) {
                    checkKeranjang(listHunianAda.get(position).getId_hunian());
                }
            });
            rvKatalog.setLayoutManager(new LinearLayoutManager(KatalogUser.this));
            rvKatalog.setAdapter(itemList);
        /*rvKatalog.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvKatalog,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(KatalogUser.this);
                        builder.setMessage("Hapus Hunian ?")
                                .setCancelable(false)
                                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        hapusHunian(listHunianAda.get(position).getId_hunian());
                                    }
                                })

                                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog =builder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));*/
        }



    }

    private void checkLike(int id, int disukai){
        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        if (listDisukai.size()>0){
            for(int i=0; i<listDisukai.size(); i++){
                if (listDisukai.get(i).getId_user()==id_user){
                    int id_hunian = listDisukai.get(i).getId_hunian();
                    if (id_hunian==id){
                        id_disukai = listDisukai.get(i).getId_like();
                        like = 1;
                        break;
                    }
                }
            }
        }

        if (like==0){
            sukaiHunian(id,disukai);
            like=0;
        } else if(like==1){
            Toast.makeText(this, "Hunian Sudah DiSukai", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Batal Menyukai Hunian ?")
                    .setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            hapusLike(id, id_disukai, disukai);
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

    private void checkKeranjang(int id){
        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        if (listKeranjang.size()>0){
            for(int i=0; i<listKeranjang.size(); i++){
                if (listKeranjang.get(i).getId_user()==id_user){
                    int id_hunian = listKeranjang.get(i).getId_hunian();
                    if (id_hunian==id){
                        keranjang = 1;
                        break;
                    }
                }
            }
        }

        if (keranjang==0){
            masukkanKeranjang(id);
            keranjang=0;
        } else if(keranjang==1){
            Toast.makeText(this, "Hunian Sudah Di Dalam Keranjang", Toast.LENGTH_SHORT).show();
            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Batal Menyukai Hunian ?")
                    .setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            hapusLike(id, id_disukai, disukai);
                        }
                    })

                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog =builder.create();
            alertDialog.show();*/
        }
    }

    private void sukaiHunian(int id, int disukai){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        now = formatter.format(new Date());
        rand = new Random();
        upperbound = 10000;
        idlike = rand.nextInt(upperbound);

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("insert into disukai(id_like, id_hunian, id_user, created_by, created_date, updated_by, updated_date) values('" +
                idlike + "','" +
                id + "','" +
                Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext())) + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");

        updateHunian(id, disukai);
    }

    private void updateHunian(int id, int disukai){
        SQLiteDatabase db = dbCenter.getWritableDatabase();
        int disukainow = disukai+1;
        db.execSQL("update hunian set jumlah_like='"+disukainow+"'" +
                "where id_hunian='"+ id +"'");

        Toast.makeText(this, "Berhasil Like", Toast.LENGTH_SHORT).show();
        goToKatalog();
    }

    private void hapusLike(int id, int idlike, int disukai){
        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("delete from disukai where id_like = '"+idlike+"'");
        int disukainow = disukai-1;
        db.execSQL("update hunian set jumlah_like='"+disukainow+"'" +
                "where id_hunian='"+ id +"'");

        Toast.makeText(this, "Berhasil Hapus Like", Toast.LENGTH_SHORT).show();
        goToKatalog();
    }

    private void masukkanKeranjang(int id){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        now = formatter.format(new Date());
        rand = new Random();
        upperbound = 10000;
        idKeranjang = rand.nextInt(upperbound);

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("insert into keranjang(id_keranjang, id_hunian, id_user, created_by, created_date, updated_by, updated_date) values('" +
                idKeranjang + "','" +
                id + "','" +
                Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext())) + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");
        Toast.makeText(this, "Berhasil Masukkan Keranjang", Toast.LENGTH_SHORT).show();
    }

    private void hapusHunian(int id){
            SQLiteDatabase db = dbCenter.getWritableDatabase();
            db.execSQL("delete from hunian where id_hunian = '"+id+"'");
            for (int i=0; i<listDetail.size(); i++){
                if (listDetail.get(i).getId_hunian()==id){
                    db.execSQL("delete from detail where id_hunian = '"+id+"'");
                }
            }
        Toast.makeText(this, "Berhasil Hapus Hunian dan Detail", Toast.LENGTH_SHORT).show();
            goToHome();
    }

    private void goToHome(){
        Intent a = new Intent(KatalogUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }
    private void goToKatalog(){
        Intent a = new Intent(KatalogUser.this, KatalogUser.class);
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