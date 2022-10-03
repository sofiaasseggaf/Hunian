package com.sofia.hunian.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sofia.hunian.model.ModelDetail;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelHunianKeluar;
import com.sofia.hunian.model.ModelHunianMasuk;
import com.sofia.hunian.model.ModelKeranjang;
import com.sofia.hunian.model.ModelUser;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "hunian.db";
    private static final int DATABASE_VERSION = 2;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_USER = "create table user(id_user integer primary key, username text not null, password text not null, nama text not null, " +
                "alamat text not null, telepon integer not null, jumlah_supply integer not null, jumlah_pembelian integer not null, " +
                "created_by text not null, created_date text not null, updated_by text not null, updated_date text not null);";

        String CREATE_TABLE_HUNIAN = "create table hunian(id_hunian integer primary key, id_user integer not null, nama_hunian text not null, image_hunian BLOB not null, " +
                "kota_hunian text not null, harga_hunian integer not null, keterangan_hunian text not null, jumlah_like integer not null, status text not null, " +
                "created_by text not null, created_date text not null, updated_by text not null, updated_date text not null);";

        String CREATE_TABLE_KERANJANG = "create table keranjang(id_keranjang integer primary key, id_hunian integer not null, id_user integer not null, " +
                "created_by text not null, created_date text not null, updated_by text not null, updated_date text not null);";

        String CREATE_TABLE_DETAIL = "create table detail(id_detail integer primary key, id_hunian integer not null, nama_detail text not null, image_detail integer not null, " +
                "created_by text not null, created_date text not null);";

        String CREATE_TABLE_HUNIAN_MASUK = "create table hunianmasuk(id_hunian_masuk integer primary key, id_hunian integer not null, id_user integer not null, created_date text not null);";

        String CREATE_TABLE_HUNIAN_KELUAR = "create table huniankeluar(id_hunian_keluar integer primary key, id_hunian integer not null, id_user_supplier integer not null, id_user_customer integer not null, created_date text not null);";

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_HUNIAN);
        db.execSQL(CREATE_TABLE_KERANJANG);
        db.execSQL(CREATE_TABLE_DETAIL);
        db.execSQL(CREATE_TABLE_HUNIAN_MASUK);
        db.execSQL(CREATE_TABLE_HUNIAN_KELUAR);

    }

    public List<ModelUser> getAllUser() {
        List<ModelUser> listUser = new ArrayList<ModelUser>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM user", null);
        cursor.moveToFirst();
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelUser user = new ModelUser(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        return listUser;
    }
    public List<ModelHunian> getAllHunian() {
        List<ModelHunian> listHunian = new ArrayList<ModelHunian>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM hunian", null);
        cursor.moveToFirst();
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelHunian hunian = new ModelHunian(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getBlob(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12));
                listHunian.add(hunian);
            } while (cursor.moveToNext());
        }
        return listHunian;
    }
    public List<ModelDetail> getAllDetail() {
        List<ModelDetail> listDetail = new ArrayList<ModelDetail>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM detail", null);
        cursor.moveToFirst();
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelDetail detail = new ModelDetail(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5));
                listDetail.add(detail);
            } while (cursor.moveToNext());
        }
        return listDetail;
    }
    public List<ModelKeranjang> getAllKeranjang() {
        List<ModelKeranjang> listKeranjang = new ArrayList<ModelKeranjang>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM keranjang", null);
        cursor.moveToFirst();
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelKeranjang keranjang = new ModelKeranjang(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                listKeranjang.add(keranjang);
            } while (cursor.moveToNext());
        }
        return listKeranjang;
    }
    public List<ModelHunianMasuk> getAllHunianMasuk() {
        List<ModelHunianMasuk> listHunianMasuk = new ArrayList<ModelHunianMasuk>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM hunianmasuk", null);
        cursor.moveToFirst();
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelHunianMasuk hunianmasuk = new ModelHunianMasuk(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3));
                listHunianMasuk.add(hunianmasuk);
            } while (cursor.moveToNext());
        }
        return listHunianMasuk;
    }
    public List<ModelHunianKeluar> getAllHunianKeluar() {
        List<ModelHunianKeluar> listHunianKeluar = new ArrayList<ModelHunianKeluar>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM huniankeluar", null);
        cursor.moveToFirst();
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelHunianKeluar huniankeluar = new ModelHunianKeluar(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4));
                listHunianKeluar.add(huniankeluar);
            } while (cursor.moveToNext());
        }
        return listHunianKeluar;
    }

    public ModelUser getUser(int idUser) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id_user = '" + idUser + "'", null);
        String[] user = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            user[cc] = cursor.getString(0).toString();
        }
        ModelUser userLogged = new ModelUser(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getInt(6),
                cursor.getInt(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11));
        return userLogged;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
