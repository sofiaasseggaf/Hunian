package com.sofia.hunian.starter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.admin.HomeAdmin;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelUser;
import com.sofia.hunian.user.HomeUser;
import com.sofia.hunian.utility.PreferenceUtils;

import java.util.List;

public class SignIn extends AppCompatActivity {

    EditText txt_username, txt_password;
    ImageButton btn_visibility, btn_sign_in;
    TextView btn_sign_up;
    DataHelper dbCenter;
    List<ModelUser> listUser;
    ModelUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_visibility = findViewById(R.id.btn_visibility);

        dbCenter = new DataHelper(this);
        listUser = dbCenter.getAllUser();

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = txt_username.getText().toString();
                String pw = txt_password.getText().toString();
                if(!un.equalsIgnoreCase("")&&!pw.equalsIgnoreCase("")){
                    checkAdmin();
                } else if (!un.equalsIgnoreCase("")){
                    Toast.makeText(SignIn.this, "Isi Username Terlebih Dahulu !", Toast.LENGTH_SHORT).show();
                } else if (!pw.equalsIgnoreCase("")){
                    Toast.makeText(SignIn.this, "Isi Password Terlebih Dahulu !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignIn.this, "Isi Username dan Password Terlebih Dahulu !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
            }
        });

    }

    private void checkAdmin(){
        if (txt_username.getText().toString().equalsIgnoreCase("admin")&&
        txt_password.getText().toString().equalsIgnoreCase("12345678")){
            goToHomeAdmin();
        } else {
            checkUser();
        }
    }

    private void checkUser(){
        int bener = 0;
        if(listUser.size()>0){
            try {
                for (int i = 0; i < listUser.size(); i++) {
                    String username = listUser.get(i).getUsername();
                    String password = listUser.get(i).getPassword();
                    int idUser = listUser.get(i).getId_user();
                    if (username.equalsIgnoreCase(txt_username.getText().toString()) && password.equalsIgnoreCase(txt_password.getText().toString())) {
                        bener = 1;
                        getDataProfileLogged(idUser);
                        break;
                    }
                }
            } catch (Exception e){ }
            if (bener!=1){
                Toast.makeText(SignIn.this, "Akun Belum Terdaftar", Toast.LENGTH_SHORT).show();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Data User Tidak Ditemukan")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog =builder.create();
            alertDialog.show();
        }
    }

    private void getDataProfileLogged(int id){
        user = dbCenter.getUser(id);
        if (user!=null){
            saveDataUser();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Data User Tidak Ditemukan")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog =builder.create();
            alertDialog.show();
        }

    }

    private void saveDataUser(){
        PreferenceUtils.saveIdUser(String.valueOf(user.getId_user()), getApplicationContext());
        PreferenceUtils.saveNama(user.getNama(), getApplicationContext());
        PreferenceUtils.saveUsername(user.getUsername(), getApplicationContext());
        PreferenceUtils.saveAlamat(user.getAlamat(), getApplicationContext());
        PreferenceUtils.saveTelepon(String.valueOf(user.getTelepon()), getApplicationContext());
        goToHomeUser();
    }


    private void goToHomeAdmin(){
        Intent a = new Intent(SignIn.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToHomeUser(){
        Intent a = new Intent(SignIn.this, HomeUser.class);
        startActivity(a);
        finish();
    }
    private void goToSignUp(){
        Intent a = new Intent(SignIn.this, SignUp.class);
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
                        SignIn.super.onBackPressed();
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