package com.incallup.backend.admin;

public class AdminModel {

    private int idAdmin;

    private String admin_username;

    private String admin_password;

    private String admin_type;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_type() {
        return admin_type;
    }

    public void setAdmin_type(String admin_type) {
        this.admin_type = admin_type;
    }

    public AdminModel(int idAdmin, String admin_username, String admin_password, String admin_type) {
        this.idAdmin = idAdmin;
        this.admin_username = admin_username;
        this.admin_password = admin_password;
        this.admin_type = admin_type;
    }
}
