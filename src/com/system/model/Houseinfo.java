package com.system.model;

public class Houseinfo {
    private String H_no;
    private String S_no;
    private String H_name;
    private String reg_ad;
    private String Item_cop;
    private String dir;
    private String Stru_na;
    private String area;
    private String floor;
    private String unit_no;
    private String cararea;
    private String fitment;
    private String facticity;
    private String status;
    private String price;
    private String money;

    public Houseinfo(String h_no, String s_no, String h_name, String reg_ad, String item_cop, String dir,
                     String stru_na, String area, String floor, String unit_no, String cararea, String fitment, String facticity,
                     String status, String price, String money) {
        super();
        H_no = h_no;
        S_no = s_no;
        H_name = h_name;
        this.reg_ad = reg_ad;
        Item_cop = item_cop;
        this.dir = dir;
        Stru_na = stru_na;
        this.area = area;
        this.floor = floor;
        this.unit_no = unit_no;
        this.cararea = cararea;
        this.fitment = fitment;
        this.facticity = facticity;
        this.status = status;
        this.price = price;
        this.money = money;
    }

    public Houseinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getH_no() {
        return H_no;
    }

    public void setH_no(String h_no) {
        H_no = h_no;
    }

    public String getS_no() {
        return S_no;
    }

    public void setS_no(String s_no) {
        S_no = s_no;
    }

    public String getH_name() {
        return H_name;
    }

    public void setH_name(String h_name) {
        H_name = h_name;
    }

    public String getReg_ad() {
        return reg_ad;
    }

    public void setReg_ad(String reg_ad) {
        this.reg_ad = reg_ad;
    }

    public String getItem_cop() {
        return Item_cop;
    }

    public void setItem_cop(String item_cop) {
        Item_cop = item_cop;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getStru_na() {
        return Stru_na;
    }

    public void setStru_na(String stru_na) {
        Stru_na = stru_na;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getUnit_no() {
        return unit_no;
    }

    public void setUnit_no(String unit_no) {
        this.unit_no = unit_no;
    }

    public String getCararea() {
        return cararea;
    }

    public void setCararea(String cararea) {
        this.cararea = cararea;
    }

    public String getFitment() {
        return fitment;
    }

    public void setFitment(String fitment) {
        this.fitment = fitment;
    }

    public String getFacticity() {
        return facticity;
    }

    public void setFacticity(String facticity) {
        this.facticity = facticity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
