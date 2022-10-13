package com.sofia.hunian.model;

public class ModelDisukai {

    //id_like integer primary key, id_hunian integer not null, id_user integer not null, " +
      //      "created_by text not null, created_date text not null, updated_by text not null, updated_date text not null);";

    int id_like, id_hunian, id_user;
    String created_by, created_date, updated_by, updated_date;

    public ModelDisukai(int id_like, int id_hunian, int id_user, String created_by, String created_date, String updated_by, String updated_date) {
        this.id_like = id_like;
        this.id_hunian = id_hunian;
        this.id_user = id_user;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
    }

    public int getId_like() {
        return id_like;
    }

    public void setId_like(int id_like) {
        this.id_like = id_like;
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
