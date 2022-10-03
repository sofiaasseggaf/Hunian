package com.sofia.hunian.model;

import java.sql.Blob;

public class ModelHunian {

    int id_hunian, id_user, harga_hunian, jumlah_like;
    String nama_hunian, kota_hunian, keterangan_hunian, status, created_by, created_date, updated_by, updated_date;
    byte[] image_hunian;

    public ModelHunian(int id_hunian, int id_user, String nama_hunian, byte[] image_hunian, String kota_hunian, int harga_hunian, String keterangan_hunian, int jumlah_like, String status, String created_by, String created_date, String updated_by, String updated_date) {
        this.id_hunian = id_hunian;
        this.id_user = id_user;
        this.nama_hunian = nama_hunian;
        this.image_hunian = image_hunian;
        this.kota_hunian = kota_hunian;
        this.harga_hunian = harga_hunian;
        this.keterangan_hunian = keterangan_hunian;
        this.jumlah_like = jumlah_like;
        this.status = status;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
    }

    public int getId_hunian() {
        return id_hunian;
    }

    public void setId_hunian(int id_hunian) {
        this.id_hunian = id_hunian;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getHarga_hunian() {
        return harga_hunian;
    }

    public void setHarga_hunian(int harga_hunian) {
        this.harga_hunian = harga_hunian;
    }

    public int getJumlah_like() {
        return jumlah_like;
    }

    public void setJumlah_like(int jumlah_like) {
        this.jumlah_like = jumlah_like;
    }

    public String getNama_hunian() {
        return nama_hunian;
    }

    public void setNama_hunian(String nama_hunian) {
        this.nama_hunian = nama_hunian;
    }

    public String getKota_hunian() {
        return kota_hunian;
    }

    public void setKota_hunian(String kota_hunian) {
        this.kota_hunian = kota_hunian;
    }

    public String getKeterangan_hunian() {
        return keterangan_hunian;
    }

    public void setKeterangan_hunian(String keterangan_hunian) {
        this.keterangan_hunian = keterangan_hunian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public byte[] getImage_hunian() {
        return image_hunian;
    }

    public void setImage_hunian(byte[] image_hunian) {
        this.image_hunian = image_hunian;
    }
}
