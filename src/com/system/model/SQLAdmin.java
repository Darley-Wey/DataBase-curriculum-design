package com.system.model;

public class SQLAdmin {
    private String A_id;
    private String name;
    private String telphone;
    private String password;

    public SQLAdmin(String a_id, String name, String telphone, String password) {
        super();
        A_id = a_id;
        this.name = name;
        this.telphone = telphone;
        this.password = password;
    }

    public String getId() {
        return A_id;
    }

    public void setId(String A_id) {
        this.A_id = A_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
