package com.sofia.hunian.starter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelUser;
import com.sofia.hunian.user.HomeUser;
import com.sofia.hunian.utility.PreferenceUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SignUp extends AppCompatActivity {

    EditText txt_nama, txt_username, txt_alamat, txt_telepon, txt_password, txt_repeat_password;
    ImageButton btn_visibility1, btn_visibility2, btn_sign_up;
    TextView btn_sign_in;
    DataHelper dbCenter;
    List<ModelUser> listUser;
    String now;
    Random rand;
    int upperbound, id_user;
    int testUsername = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        txt_nama = findViewById(R.id.txt_nama);
        txt_username = findViewById(R.id.txt_username);
        txt_alamat = findViewById(R.id.txt_alamat);
        txt_telepon = findViewById(R.id.txt_telepon);
        txt_password = findViewById(R.id.txt_password);
        txt_repeat_password = findViewById(R.id.txt_repeat_password);
        btn_visibility1 = findViewById(R.id.btn_visibility1);
        btn_visibility2 = findViewById(R.id.btn_visibility2);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in = findViewById(R.id.btn_sign_in);

        dbCenter = new DataHelper(this);
        listUser = dbCenter.getAllUser();

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = txt_username.getText().toString();
                String pw = txt_password.getText().toString();
                String pw2 = txt_repeat_password.getText().toString();
                String nama = txt_nama.getText().toString();
                String alamat = txt_alamat.getText().toString();
                String tlp = txt_telepon.getText().toString();

                if(!un.equalsIgnoreCase("")&&!pw.equalsIgnoreCase("")&&!pw2.equalsIgnoreCase("")&&
                !nama.equalsIgnoreCase("")&&!alamat.equalsIgnoreCase("")&&!tlp.equalsIgnoreCase("")){
                    checkPassword();
                } else {
                    Toast.makeText(SignUp.this, "Lengkapi Semua Field Terlebih Dahulu !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignIn();
            }
        });

    }

    private void checkPassword(){
        String pw = txt_password.getText().toString();
        String pw2 = txt_repeat_password.getText().toString();
        if (pw.equalsIgnoreCase(pw2)){
            checkusername();
        } else {
            Toast.makeText(this, "Password Tidak Sama !", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkusername(){

        String un = txt_username.getText().toString();
        if (un.equalsIgnoreCase("admin")){
            Toast.makeText(this, "Username Telah Terpakai !", Toast.LENGTH_SHORT).show();
        } else {
            if (listUser.size()>0){
                for (int i=0; i<listUser.size(); i++){
                    if(listUser.get(i).getUsername().equalsIgnoreCase(un)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Username Telah Terpakai !")
                                .setCancelable(false)
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog =builder.create();
                        alertDialog.show();
                        testUsername=1;
                        break;
                    }
                }
            } else {
                createUser();
            }
            if(testUsername!=1){
                testUsername=0;
                createUser();
            }else{
                testUsername=0;
            }

        }
    }

    private void createUser(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        now = formatter.format(new Date());
        rand = new Random();
        upperbound = 10000;
        id_user = rand.nextInt(upperbound);

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("insert into user(id_user, username, password, nama, alamat, telepon, jumlah_supply, jumlah_pembelian, created_by, created_date, updated_by, updated_date) values('" +
                id_user + "','" +
                txt_username.getText().toString() + "','" +
                txt_password.getText().toString() + "','" +
                txt_nama.getText().toString() + "','" +
                txt_alamat.getText().toString() + "','" +
                txt_telepon.getText() + "','" +
                0 + "','" +
                0 + "','" +
                txt_nama.getText().toString() + "','" +
                now + "','" +
                txt_nama.getText().toString() + "','" +
                now + "')");
        Toast.makeText(getApplicationContext(), "Berhasil Sign Up", Toast.LENGTH_SHORT).show();

        saveDataUser();

    }

    private void saveDataUser(){
        PreferenceUtils.saveIdUser(String.valueOf(id_user), getApplicationContext());
        PreferenceUtils.saveNama(txt_nama.getText().toString(), getApplicationContext());
        PreferenceUtils.saveUsername(txt_username.getText().toString(), getApplicationContext());
        PreferenceUtils.saveAlamat(txt_alamat.getText().toString(), getApplicationContext());
        PreferenceUtils.saveTelepon(String.valueOf(txt_telepon.getText()), getApplicationContext());
        goToHomeUser();
    }
    private void goToSignIn(){
        Intent a = new Intent(SignUp.this, SignIn.class);
        startActivity(a);
        finish();
    }
    private void goToHomeUser(){
        Intent a = new Intent(SignUp.this, HomeUser.class);
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
                        SignUp.super.onBackPressed();
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