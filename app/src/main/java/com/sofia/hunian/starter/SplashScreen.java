package com.sofia.hunian.starter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sofia.hunian.R;
import com.sofia.hunian.admin.HomeAdmin;
import com.sofia.hunian.user.HomeUser;
import com.sofia.hunian.utility.PreferenceUtils;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        if (PreferenceUtils.getUsername(getApplicationContext()).equalsIgnoreCase("")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(SplashScreen.this, SignIn.class);
                    startActivity(homeIntent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else if (PreferenceUtils.getUsername(getApplicationContext()).equalsIgnoreCase("admin")){
            goToAdminHome();
        } else {
            goToUserHome();
        }



    }

    private void goToAdminHome(){
        Intent a = new Intent(SplashScreen.this, HomeAdmin.class);
        startActivity(a);
        finish();
    }

    private void goToUserHome(){
        Intent a = new Intent(SplashScreen.this, HomeUser.class);
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
                        SplashScreen.super.onBackPressed();
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