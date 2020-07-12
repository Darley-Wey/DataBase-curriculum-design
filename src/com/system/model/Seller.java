package com.system.model;

public class Seller {
    private String S_no;
    private String S_id;
    private String S_name;
    private String S_password;
    private String S_email;
    private String S_add;
    private String S_tel;


    public Seller() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Seller(String s_no,String s_name, String s_password, String s_email, String s_add,
                  String s_tel) {
        super();
        S_no = s_no;
        S_name = s_name;
        S_password = s_password;
        S_email = s_email;
        S_add = s_add;
        S_tel = s_tel;

    }

    public String getS_no() {
        return S_no;
    }

    public void setS_no(String s_no) {
        S_no = s_no;
    }

    public String getS_id() {
        return S_id;
    }

    public void setS_id(String s_id) {
        S_id = s_id;
    }

    public String getS_name() {
        return S_name;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }

    public String getS_password() {
        return S_password;
    }

    public void setS_password(String s_password) {
        S_password = s_password;
    }

    public String getS_email() {
        return S_email;
    }

    public void setS_email(String s_email) {
        S_email = s_email;
    }

    public String getS_add() {
        return S_add;
    }

    public void setS_add(String s_add) {
        S_add = s_add;
    }

    public String getS_tel() {
        return S_tel;
    }

    public void setS_tel(String s_tel) {
        S_tel = s_tel;
    }


}
