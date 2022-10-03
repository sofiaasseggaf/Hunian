package com.sofia.hunian.model;

public class ModelUser {

    int id_user, telepon, jumlah_supply, jumlah_pembelian;
    String username, password, nama, alamat, created_by, created_date, updated_by, updated_date;

    public ModelUser(int id_user, String username, String password, String nama, String alamat, int telepon, int jumlah_supply, int jumlah_pembelian, String created_by, String created_date, String updated_by, String updated_date) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.jumlah_supply = jumlah_supply;
        this.jumlah_pembelian = jumlah_pembelian;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getTelepon() {
        return telepon;
    }

    public void setTelepon(int telepon) {
        this.telepon = telepon;
    }

    public int getJumlah_supply() {
        return jumlah_supply;
    }

    public void setJumlah_supply(int jumlah_supply) {
        this.jumlah_supply = jumlah_supply;
    }

    public int getJumlah_pembelian() {
        return jumlah_pembelian;
    }

    public void setJumlah_pembelian(int jumlah_pembelian) {
        this.jumlah_pembelian = jumlah_pembelian;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
}
