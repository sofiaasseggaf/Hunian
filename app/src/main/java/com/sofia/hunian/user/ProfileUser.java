package com.sofia.hunian.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.starter.SignIn;
import com.sofia.hunian.starter.SignUp;
import com.sofia.hunian.starter.SplashScreen;
import com.sofia.hunian.utility.PreferenceUtils;

public class ProfileUser extends AppCompatActivity {

    ImageButton btn_home, btn_katalog, btn_disukai, btn_sign_out, btn_simpan_perubahan;
    LinearLayout btn_home2, btn_katalog2, btn_disukai2;
    TextView nama;
    EditText txt_nama, txt_username, txt_alamat, txt_telepon;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        btn_home = findViewById(R.id.btn_home);
        btn_katalog = findViewById(R.id.btn_katalog);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_home2 = findViewById(R.id.btn_home2);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        btn_sign_out = findViewById(R.id.btn_sign_out);
        btn_simpan_perubahan = findViewById(R.id.btn_simpan_perubahan);
        nama = findViewById(R.id.nama);
        txt_nama = findViewById(R.id.txt_nama);
        txt_username = findViewById(R.id.txt_username);
        txt_alamat = findViewById(R.id.txt_alamat);
        txt_telepon = findViewById(R.id.txt_telepon);

        setData();

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
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUser.this);
                builder.setMessage("Anda Mau Sign Out")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                hapusDataUser();
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
        });
        btn_simpan_perubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = txt_nama.getText().toString();
                String alamat = txt_alamat.getText().toString();
                String tlp = txt_telepon.getText().toString();
                if(!nama.equalsIgnoreCase("")&&!alamat.equalsIgnoreCase("")&&!tlp.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUser.this);
                    builder.setMessage("Simpan Perubahan ?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    updateDataUser();
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
                } else {
                    Toast.makeText(ProfileUser.this, "Lengkapi Semua Field Terlebih Dahulu !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(){
        nama.setText(PreferenceUtils.getNama(getApplicationContext()));
        txt_nama.setText(PreferenceUtils.getNama(getApplicationContext()));
        txt_alamat.setText(PreferenceUtils.getAlamat(getApplicationContext()));
        txt_username.setText(PreferenceUtils.getUsername(getApplicationContext()));
        txt_telepon.setText(PreferenceUtils.getTelepon(getApplicationContext()));
    }
    private void hapusDataUser(){
        PreferenceUtils.saveIdUser("", getApplicationContext());
        PreferenceUtils.saveNama("", getApplicationContext());
        PreferenceUtils.saveUsername("", getApplicationContext());
        PreferenceUtils.saveAlamat("", getApplicationContext());
        PreferenceUtils.saveTelepon("", getApplicationContext());
        goToLogin();
    }
    private void updateDataUser(){

        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nama = txt_nama.getText().toString();
        String alamat = txt_alamat.getText().toString();
        int tlp = Integer.valueOf(txt_telepon.getText().toString());
        int id_user = Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));

        db.execSQL("update user set nama='"+nama+"', alamat='"+alamat+"', telepon= '"+tlp+"'" +
                "where id_user='"+ id_user +"'");

        PreferenceUtils.saveNama(nama, getApplicationContext());
        PreferenceUtils.saveAlamat(alamat, getApplicationContext());
        PreferenceUtils.saveTelepon(String.valueOf(tlp), getApplicationContext());

        Toast.makeText(getApplicationContext(), "Berhasil Update Data User", Toast.LENGTH_SHORT).show();
        goToHome();
    }

    private void goToHome(){
        Intent a = new Intent(ProfileUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }
    private void goToKatalog(){
        Intent a = new Intent(ProfileUser.this, KatalogUser.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        Intent a = new Intent(ProfileUser.this, DisukaiUser.class);
        startActivity(a);
        finish();
    }
    private void goToLogin(){
        Intent a = new Intent(ProfileUser.this, SignIn.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}