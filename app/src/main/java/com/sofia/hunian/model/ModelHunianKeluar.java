package com.sofia.hunian.model;

public class ModelHunianKeluar {

    int id_hunian_keluar, id_hunian, id_user_supplier, id_user_customer;
    String created_date;

    public ModelHunianKeluar(int id_hunian_keluar, int id_hunian, int id_user_supplier, int id_user_customer, String created_date) {
        this.id_hunian_keluar = id_hunian_keluar;
        this.id_hunian = id_hunian;
        this.id_user_supplier = id_user_supplier;
        this.id_user_customer = id_user_customer;
        this.created_date = created_date;
    }

    public int getId_hunian_keluar() {
        return id_hunian_keluar;
    }

    public void setId_hunian_keluar(int id_hunian_keluar) {
        this.id_hunian_keluar = id_hunian_keluar;
    }

    public int getId_hunian() {
        return id_hunian;
    }

    public void setId_hunian(int id_hunian) {
        this.id_hunian = id_hunian;
    }

    public int getId_user_supplier() {
        return id_user_supplier;
    }

    public void setId_user_supplier(int id_user_supplier) {
        this.id_user_supplier = id_user_supplier;
    }

    public int getId_user_customer() {
        return id_user_customer;
    }

    public void setId_user_customer(int id_user_customer) {
        this.id_user_customer = id_user_customer;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
