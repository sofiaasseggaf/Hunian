package com.sofia.hunian.model;

public class ModelDetail {

    int id_detail, id_hunian, image_detail;
    String nama_detail, created_by, created_date;

    public ModelDetail(int id_detail, int id_hunian,  String nama_detail, int image_detail,String created_by, String created_date) {
        this.id_detail = id_detail;
        this.id_hunian = id_hunian;
        this.nama_detail = nama_detail;
        this.image_detail = image_detail;
        this.created_by = created_by;
        this.created_date = created_date;
    }

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getId_hunian() {
        return id_hunian;
    }

    public void setId_hunian(int id_hunian) {
        this.id_hunian = id_hunian;
    }

    public int getImage_detail() {
        return image_detail;
    }

    public void setImage_detail(int image_detail) {
        this.image_detail = image_detail;
    }

    public String getNama_detail() {
        return nama_detail;
    }

    public void setNama_detail(String nama_detail) {
        this.nama_detail = nama_detail;
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
}
