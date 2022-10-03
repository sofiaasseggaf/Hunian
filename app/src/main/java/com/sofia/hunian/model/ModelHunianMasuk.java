package com.sofia.hunian.model;

public class ModelHunianMasuk {

    int id_hunian_masuk, id_hunian, id_user;
    String created_date;

    public ModelHunianMasuk(int id_hunian_masuk, int id_hunian, int id_user, String created_date) {
        this.id_hunian_masuk = id_hunian_masuk;
        this.id_hunian = id_hunian;
        this.id_user = id_user;
        this.created_date = created_date;
    }

    public int getId_hunian_masuk() {
        return id_hunian_masuk;
    }

    public void setId_hunian_masuk(int id_hunian_masuk) {
        this.id_hunian_masuk = id_hunian_masuk;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
