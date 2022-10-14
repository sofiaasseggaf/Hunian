package com.sofia.hunian.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.AdapterDataHunian;
import com.sofia.hunian.adapter.AdapterDetail;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckOutUser extends AppCompatActivity {

    TextView nama, harga, kota, ket;
    ImageView img;
    RecyclerView rvDetail;
    ImageButton btn_copy1, btn_copy2, btn_copy3;
    DataHelper dbCenter;
    AdapterDetail itemList;
    ModelHunian hunian;
    List<ModelDetail> listDetail;
    List<ModelDetail> listDetailNew = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_check_out);

        nama = findViewById(R.id.nama);
        harga = findViewById(R.id.harga);
        kota = findViewById(R.id.kota);
        ket = findViewById(R.id.ket);
        img = findViewById(R.id.img);
        rvDetail = findViewById(R.id.rvDetail);
        btn_copy1 = findViewById(R.id.btn_copy1);
        btn_copy2 = findViewById(R.id.btn_copy2);
        btn_copy3 = findViewById(R.id.btn_copy3);

        Intent intent = getIntent();
        int idHunian = intent.getIntExtra("idHunian", 0);

        dbCenter = new DataHelper(this);
        getHunian(idHunian);

        btn_copy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckOutUser.this, "Text Berhasil Disalin", Toast.LENGTH_SHORT).show();
            }
        });
        btn_copy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckOutUser.this, "Text Berhasil Disalin", Toast.LENGTH_SHORT).show();
            }
        });
        btn_copy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckOutUser.this, "Text Berhasil Disalin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHunian(int id){
        hunian = dbCenter.getHunian(id);
        if (hunian!=null){
            getDetail(id);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Data Hunian Tidak Ditemukan")
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

    private void getDetail(int id){
        listDetail = dbCenter.getAllDetail();
        if (listDetail.size()>0){
            listDetailNew.clear();
            for (int i=0; i<listDetail.size(); i++){
                if (listDetail.get(i).getId_hunian()==id){
                    listDetailNew.add(listDetail.get(i));
                }
            }
        }

        nama.setText(hunian.getNama_hunian());
        String a = checkDesimal(String.valueOf(hunian.getHarga_hunian()));
        harga.setText("Harga : Rp "+a);
        kota.setText(hunian.getKota_hunian());
        ket.setText(hunian.getKeterangan_hunian());
        if (!hunian.getImage_hunian().equalsIgnoreCase("")){
            String encodedImage = hunian.getImage_hunian();
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
           img.setImageBitmap(decodedByte);
        }


        itemList = new AdapterDetail(listDetailNew);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDetail.setLayoutManager(layoutManager);
        rvDetail.setAdapter(itemList);
    }

    private String checkDesimal(String a){
        DecimalFormat formatter;
        formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator('.');
        formatter = new DecimalFormat("###,###.##", symbols);

        if(a!=null || !a.equalsIgnoreCase("")){
            if(a.length()>3){
                a = formatter.format(Double.valueOf(a));
            }
        }
        return a;
    }

    private void goToHome(){
        Intent a = new Intent(CheckOutUser.this, HomeUser.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToHome();
    }
}