package com.sofia.hunian.starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sofia.hunian.R;

public class SignUp extends AppCompatActivity {

    EditText txt_nama, txt_username, txt_alamat, txt_telepon, txt_password, txt_repeat_password;
    ImageButton btn_visibility1, btn_visibility2, btn_sign_up;
    TextView btn_sign_in;

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

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SignUp.this, SignIn.class);
                startActivity(a);
                finish();
            }
        });

    }
}