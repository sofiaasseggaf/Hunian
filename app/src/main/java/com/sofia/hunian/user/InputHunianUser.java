package com.sofia.hunian.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.sofia.hunian.R;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.utility.PreferenceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class InputHunianUser extends AppCompatActivity {

    EditText txt_nama_hunian, txt_kota_hunian, txt_harga_hunian, txt_keterangan_hunian;
    EditText txt_detail_hunian1, txt_detail_hunian2, txt_detail_hunian3, txt_detail_hunian4;
    ImageButton btn_camera, btn_tambah_hunian;
    ImageView image;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    DataHelper dbCenter;
    final int CAMERA_REQUEST = 12345;
    final int GALLERY_REQUEST = 54321;
    Bitmap bitmap;
    String encodedImage;
    String now;
    Random rand;
    int upperbound, id_hunian, id_detail1, id_detail2, id_detail3, id_detail4, id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_input_hunian);

        txt_nama_hunian = findViewById(R.id.txt_nama_hunian);
        txt_kota_hunian = findViewById(R.id.txt_kota_hunian);
        txt_harga_hunian = findViewById(R.id.txt_harga_hunian);
        txt_keterangan_hunian = findViewById(R.id.txt_keterangan_hunian);
        txt_detail_hunian1 = findViewById(R.id.txt_detail_hunian1);
        txt_detail_hunian2 = findViewById(R.id.txt_detail_hunian2);
        txt_detail_hunian3 = findViewById(R.id.txt_detail_hunian3);
        txt_detail_hunian4 = findViewById(R.id.txt_detail_hunian4);
        image = findViewById(R.id.image);
        btn_camera = findViewById(R.id.btn_camera);
        btn_tambah_hunian = findViewById(R.id.btn_tambah_hunian);

        dbCenter = new DataHelper(this);
        encodedImage = "";
        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setVisibility(View.GONE);
                try{
                    startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                } catch (Exception e){
                    Toast.makeText(InputHunianUser.this, "Galeri Bermasalah", Toast.LENGTH_SHORT).show();
                }
                /*AlertDialog.Builder builder = new AlertDialog.Builder(InputHunianUser.this);
                builder.setMessage("Kamera atau Galeri ?")
                        .setCancelable(false)
                        .setPositiveButton("KAMERA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                try{
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, CAMERA_REQUEST);
                                } catch (Exception e){
                                    Toast.makeText(InputHunianUser.this, "Kamera Bermasalah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("GALERI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                try{
                                    startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                                } catch (Exception e){
                                    Toast.makeText(InputHunianUser.this, "Galeri Bermasalah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/
            }
        });
        btn_tambah_hunian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = txt_nama_hunian.getText().toString();
                String kota = txt_kota_hunian.getText().toString();
                String harga = txt_harga_hunian.getText().toString();
                String ket = txt_keterangan_hunian.getText().toString();
                String det1 = txt_detail_hunian1.getText().toString();
                String det2 = txt_detail_hunian2.getText().toString();
                String det3 = txt_detail_hunian3.getText().toString();
                String det4 = txt_detail_hunian4.getText().toString();

                if (!nama.equalsIgnoreCase("")&&!kota.equalsIgnoreCase("")&&!harga.equalsIgnoreCase("")&&
                !ket.equalsIgnoreCase("")&&!det1.equalsIgnoreCase("")&&!det2.equalsIgnoreCase("")&&
                !det3.equalsIgnoreCase("")&&!det4.equalsIgnoreCase("")){
                    if (!encodedImage.equalsIgnoreCase("")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(InputHunianUser.this);
                        builder.setMessage("Simpan Hunian ?")
                                .setCancelable(false)
                                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        saveDataHunian();
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
                        Toast.makeText(InputHunianUser.this, "Tambah Gambar Hunian !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InputHunianUser.this, "Lengkapi Field Terlebih Dahulu !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressLint("Range")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            /*if (requestCode == CAMERA_REQUEST) {
                bitmap = (Bitmap) data.getExtras().get("data");
                encodedImage = ImageBase64.encode(bitmap);

                if (!encodedImage.equalsIgnoreCase("")){
                    image.setVisibility(View.VISIBLE);
                    image.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(InputHunianUser.this, "Kamera Bermasalah Ketika Mengambil Foto", Toast.LENGTH_SHORT).show();
                }

            }*/
            if (requestCode == GALLERY_REQUEST) {
                Uri uri = data.getData();
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(uri);


                    if (bitmap != null) {
                        bitmap.recycle();
                        bitmap = null;
                    }

                    Bitmap original = BitmapFactory.decodeStream(inputStream);
                    bitmap = Bitmap.createScaledBitmap(original, 150, 150, false);
                    encodedImage = ImageBase64.encode(bitmap);
                    image.setVisibility(View.VISIBLE);
                    image.setImageBitmap(bitmap);
                    if (original != bitmap){
                        original.recycle();
                        original = null;
                    }

                    /*bitmap = BitmapFactory.decodeStream(inputStream);
                    encodedImage = ImageBase64.encode(bitmap);
                    if (!encodedImage.equalsIgnoreCase("")){
                        image.setVisibility(View.VISIBLE);
                        image.setImageBitmap(bitmap);
                    } else {
                        Toast.makeText(InputHunianUser.this, "Galeri Bermasalah Ketika Mengambil Foto", Toast.LENGTH_SHORT).show();
                    }*/



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveDataHunian(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        now = formatter.format(new Date());
        rand = new Random();
        upperbound = 10000;
        id_hunian = rand.nextInt(upperbound);
        id_user =  Integer.valueOf(PreferenceUtils.getIdUser(getApplicationContext()));
        int harga = Integer.valueOf(txt_harga_hunian.getText().toString());

        //id_hunian integer primary key, id_user integer not null, nama_hunian text not null, image_hunian BLOB not null, " +
        //"kota_hunian text not null, harga_hunian integer not null, keterangan_hunian text not null, jumlah_like integer not null, status text not null, " +
        //        "created_by text not null, created_date text not null, updated_by text not null, updated_date text not null

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("insert into hunian(id_hunian, id_user, nama_hunian, image_hunian, kota_hunian, harga_hunian, keterangan_hunian, jumlah_like, status, created_by, created_date, updated_by, updated_date) values('" +
                id_hunian + "','" +
                id_user + "','" +
                txt_nama_hunian.getText().toString() + "','" +
                encodedImage + "','" +
                txt_kota_hunian.getText().toString() + "','" +
                txt_keterangan_hunian.getText() + "','" +
                harga + "','" +
                0 + "','" +
                "ada" + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");

        saveDataDetail();

    }

    private void saveDataDetail(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        now = formatter.format(new Date());
        rand = new Random();
        upperbound = 10000;
        id_detail1 = rand.nextInt(upperbound);
        id_detail2 = rand.nextInt(upperbound);
        id_detail3 = rand.nextInt(upperbound);
        id_detail4 = rand.nextInt(upperbound);

        //detail(id_detail integer primary key, id_hunian integer not null, nama_detail text not null, " +
        //"created_by text not null, created_date text not null);";

        SQLiteDatabase db = dbCenter.getWritableDatabase();
        db.execSQL("insert into detail(id_detail, id_hunian, nama_detail, created_by, created_date) values('" +
                id_detail1 + "','" +
                id_hunian + "','" +
                txt_detail_hunian1.getText().toString() + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");
        db.execSQL("insert into detail(id_detail, id_hunian, nama_detail, created_by, created_date) values('" +
                id_detail2 + "','" +
                id_hunian + "','" +
                txt_detail_hunian2.getText().toString() + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");
        db.execSQL("insert into detail(id_detail, id_hunian, nama_detail, created_by, created_date) values('" +
                id_detail3 + "','" +
                id_hunian + "','" +
                txt_detail_hunian3.getText().toString() + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");
        db.execSQL("insert into detail(id_detail, id_hunian, nama_detail, created_by, created_date) values('" +
                id_detail4 + "','" +
                id_hunian + "','" +
                txt_detail_hunian4.getText().toString() + "','" +
                PreferenceUtils.getNama(getApplicationContext()) + "','" +
                now + "')");

        Toast.makeText(getApplicationContext(), "Berhasil Tambah Hunian", Toast.LENGTH_SHORT).show();
        goToKatalog();

    }

    private void goToKatalog(){
        Intent a = new Intent(InputHunianUser.this, KatalogUser.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Batal Input Hunian ?")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                       goToKatalog();
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