package com.system.model;

public class SQLHouse {
    private String H_no;
    private String S_no;
    private String H_name;
    private String reg_ad;//所属地址
    private String Item_cop;//建筑单位
    private String dir;//朝向
    private int area;//面积
    private int floor;
    private String unit_no;//单元号
    private String cararea;//车库面积
    private String fitment;//装修状况
    private String serverfee;
    private int status;//房源状态
    private int money;

    public String getS_no() {
        return S_no;
    }

    public void setS_no(String s_no) {
        S_no = s_no;
    }

    public String getH_no() {
        return H_no;
    }

    public void setH_no(String h_no) {
        H_no = h_no;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
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

    public String getServerfee() {
        return serverfee;
    }

    public void setServerfee(String serverfee) {
        this.serverfee = serverfee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


}

