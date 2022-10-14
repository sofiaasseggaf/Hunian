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
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataDisukai;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelDisukai;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelKeranjang;
import com.sofia.hunian.utility.PreferenceUtils;
import com.sofia.hunian.utility.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KeranjangUser extends AppCompatActivity {

    RecyclerView rvKeranjang;
    DataHelper dbCenter;
    List<ModelHunian> listHunian;
    List<ModelHunian> listHunianKeranjang = new ArrayList<>();
    List<ModelKeranjang> listKeranjang;
    AdapterDataDisukai itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_keranjang);

        rvKeranjang = findViewById(R.id.rvKeranjang);

        dbCenter = new DataHelper(this);
        getDataHunian();

    }

    private void getDataHunian(){
        listHunian = dbCenter.getAllHunian();
        listKeranjang = dbCenter.getAllKeranjang();
        listHunianKeranjang.clear();

        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        for(int i=0; i<listKeranjang.size(); i++){
            if (listKeranjang.get(i).getId_user()==id_user){
                for (int j=0; j<listHunian.size(); j++){
                    if (listKeranjang.get(i).getId_hunian()==listHunian.get(j).getId_hunian()){
                        listHunianKeranjang.add(listHunian.get(j));
                    }
                }
            }
        }

        if (listHunianKeranjang.size()>0){
            itemList = new AdapterDataDisukai(listHunianKeranjang);
            rvKeranjang.setLayoutManager(new LinearLayoutManager(KeranjangUser.this));
            rvKeranjang.setAdapter(itemList);
            rvKeranjang.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvKeranjang,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(KeranjangUser.this);
                        builder.setMessage("HAPUS atau CHECK OUT ?")
                                .setCancelable(false)
                                .setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        hapusKeranjang(listHunianKeranjang.get(position).getId_hunian());
                                    }
                                })

                                .setNegativeButton("CHECK OUT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        sendDataHunianKeluar(listHunianKeranjang.get(position).getId_hunian(), listHunianKeranjang.get(position).getId_user());
                                    }
                                });
                        AlertDialog alertDialog =builder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
        }

    }

    private void hapusKeranjang(int idHunian){
        SQLiteDatabase db = dbCenter.getWritableDatabase();
        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        for (int i=0; i<listKeranjang.size(); i++){
            if (listKeranjang.get(i).getId_user()==id_user){
                if (listKeranjang.get(i).getId_hunian()==idHunian){
                    int idkeranjang = listKeranjang.get(i).getId_keranjang();
                    db.execSQL("delete from keranjang where id_keranjang = '"+idkeranjang+"'");
                }
            }
        }
        Toast.makeText(this, "Berhasil Hapus Keranjang", Toast.LENGTH_SHORT).show();
        goToKeranjang();
    }

    private void sendDataHunianKeluar(int idHunian, int idSupplier){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String now = formatter.format(new Date());
        Random rand = new Random();
        int upperbound = 10000;
        int idHunianKeluar = rand.nextInt(upperbound);

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("insert into huniankeluar(id_hunian_keluar, id_hunian, id_user_supplier, id_user_customer, created_date) values('" +
                idHunianKeluar + "','" +
                idHunian + "','" +
                idSupplier + "','" +
                Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext())) + "','" +
                now + "')");

        updateDataHunian(idHunian);
    }

    private void updateDataHunian(int idHunian){

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("update hunian set status='"+"tidak"+"'" +
                "where id_hunian='"+ idHunian +"'");

        // hapus keranjang

        SQLiteDatabase dba = dbCenter.getWritableDatabase();
        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        for (int i=0; i<listKeranjang.size(); i++){
            if (listKeranjang.get(i).getId_user()==id_user){
                if (listKeranjang.get(i).getId_hunian()==idHunian){
                    int idkeranjang = listKeranjang.get(i).getId_keranjang();
                    dba.execSQL("delete from keranjang where id_keranjang = '"+idkeranjang+"'");
                }
            }
        }

        Intent a = new Intent(KeranjangUser.this, CheckOutUser.class);
        a.putExtra("idHunian", idHunian);
        startActivity(a);
        finish();
    }

    private void goToHome(){
        Intent a = new Intent(KeranjangUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }

    private void goToKeranjang(){
        Intent a = new Intent(KeranjangUser.this, KeranjangUser.class);
        startActivity(a);
        finish();
    }


    @Override
    public void onBackPressed(){
        goToHome();
    }
}