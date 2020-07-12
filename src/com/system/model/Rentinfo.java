package com.system.model;

public class Rentinfo {
    private String R_no;
    private String B_no;
    private String reg_ad;
    private String tel;
    private String sty;
    private String add;
    private String status;
    private String money;

    public Rentinfo(String r_no, String b_no, String tel, String reg_ad,
                    String money, String sty,
                    String status, String add) {
        super();
        //H_no,S_no, reg_ad, dir,area, unit_no, status,money
        R_no = r_no;
        B_no = b_no;
        this.reg_ad = reg_ad;
        this.tel = tel;
        this.sty = sty;
        this.add = add;
        this.status = status;
        this.money = money;
    }

    public Rentinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getR_no() {
        return R_no;
    }

    public void setR_no(String r_no) {
        R_no = r_no;
    }

    public String getB_no() {
        return B_no;
    }

    public void setB_no(String b_no) {
        B_no = b_no;
    }

    public String getReg_ad() {
        return reg_ad;
    }

    public void setReg_ad(String reg_ad) {
        this.reg_ad = reg_ad;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSty() {
        return sty;
    }

    public void setSty(String sty) {
        this.sty = sty;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
