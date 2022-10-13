package com.sofia.hunian.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sofia.hunian.R;
import com.sofia.hunian.starter.SignIn;
import com.sofia.hunian.user.ProfileUser;
import com.sofia.hunian.utility.PreferenceUtils;

public class ProfileAdmin extends AppCompatActivity {

    ImageButton btn_home, btn_katalog, btn_disukai, btn_sign_out;
    LinearLayout btn_home2, btn_katalog2, btn_disukai2;
    EditText txt_nama, txt_username;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);

        btn_home = findViewById(R.id.btn_home);
        btn_katalog = findViewById(R.id.btn_katalog);
        btn_disukai = findViewById(R.id.btn_disukai);
        btn_home2 = findViewById(R.id.btn_home2);
        btn_katalog2 = findViewById(R.id.btn_katalog2);
        btn_disukai2 = findViewById(R.id.btn_disukai2);
        nama = findViewById(R.id.nama);
        txt_nama = findViewById(R.id.txt_nama);
        txt_username = findViewById(R.id.txt_username);
        btn_sign_out = findViewById(R.id.btn_sign_out);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileAdmin.this);
                builder.setMessage("Anda Mau Sign Out")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                goToLogin();
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
    }

    private void setData(){
        nama.setText("Admin");
        txt_nama.setText("Admin");
        txt_username.setText("admin");
    }

    private void goToHome(){
        Intent a = new Intent(ProfileAdmin.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToDisukai(){
        // kasih alert suruh buat akun user
    }
    private void goToKatalog(){
        Intent a = new Intent(ProfileAdmin.this, KatalogAdmin.class);
        startActivity(a);
        finish();
    }
    private void goToLogin(){
        Intent a = new Intent(ProfileAdmin.this, SignIn.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}