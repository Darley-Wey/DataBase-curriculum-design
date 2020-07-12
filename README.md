### 一、设计题目 

　房屋中介公司管理系统 

### 二、设计目的 

　加深对数据库基础理论和基本知识的理解，掌握基于数据库的应用软件设计基本方法,提高解决数据库应用实际问题的能力。

###  三、设计原理及方案

　房屋中介公司管理系统是一种以互联网为基础的交易模式，通过本系统能 够方便灵活地对管理房源信息、交易信息（包括合同管理）、交易状态以及客户 信息进行管理，简化和帮助房产中介的相关工作，同时方便用户查询和购买租赁房产。系统有助于扩大客户群体，为企业带来充足的资金，同时在分析稳定 客户数量和消费记录的基础上，可以更科学地决策企业的发展，减少决策的盲目性和不确定性。 

#### 	 1 使用的软件工具 

　本设计采用的操作系统是Windows 10，采用的开发语言是Java，其运行环境为 jdk1.8.0。选用 SQL Server 建立并管理数据库，通过JDBC接口来实现对数据库的访问，使用Java Swing库进行界面设计。设计时所选择的开发环境是Eclipse，这是一个全功能的开放源码Java IDE，可以帮助开发人员编写、编译、 调试和部署Java应用。

#### 2 数据库的实施页码

  定义数据对象的 SQL 语句分别如下：

`

--创建数据库

create database HousingAgency
    collate Chinese_PRC_CI_AS
go

--切换数据库

USE HousingAgency
GO

--职工表

CREATE TABLE Admin
    (
        A_id       varchar(10) NOT NULL
            CONSTRAINT Admin_pk
                PRIMARY KEY NONCLUSTERED,
        A_name     char(10)    NOT NULL,
        A_tel      char(20)    NOT NULL,
        A_password varchar(20) NOT NULL
            CONSTRAINT Admin_ck
                CHECK (len(A_password) >= 6)
    )

GO

--买家表

CREATE TABLE Buyer
    (
        B_no       varchar(10) NOT NULL
            CONSTRAINT Buyer_pk
                PRIMARY KEY NONCLUSTERED,
        B_name     char(10)    NOT NULL,
        B_password varchar(20) NOT NULL
            CONSTRAINT Buyer_ck
                CHECK (len(B_password) >= 6),
        B_email    varchar(30) NOT NULL,
        B_add      char(20)    NOT NULL,
        B_tel      char(20)    NOT NULL
    )

GO

--卖家表

CREATE TABLE Seller
    (
        S_no       varchar(10) NOT NULL
            CONSTRAINT Seller_pk
                PRIMARY KEY NONCLUSTERED,
        S_name     char(10)    NOT NULL,
        S_password varchar(20) NOT NULL
            CONSTRAINT Seller_ck
                CHECK (len(S_password) >= 6),
        S_email    varchar(30) NOT NULL,
        S_add      char(20)    NOT NULL,
        S_tel      char(20)    NOT NULL
    )

GO

--房源信息表

CREATE TABLE Houseinfo
    (
        H_no      varchar(10) NOT NULL
            CONSTRAINT Houseinfo_pk
                PRIMARY KEY NONCLUSTERED,
        S_no      varchar(10) NOT NULL
            CONSTRAINT Houseinfo_Seller_S_no_fk
                REFERENCES Seller,
        H_name    char(10),
        reg_ad    char(20)    NOT NULL,
        Item_cop  char(10),
        dir       varchar(10) NOT NULL,
        Stru_na   varchar(10) NOT NULL,
        area      int         NOT NULL,
        floor     int         NOT NULL,
        unit_no   char(10),
        cararea   char(10),
        fitment   varchar(10),
        facticity nchar(3)    NOT NULL,
        status    nchar(3)    NOT NULL,
        price     int         NOT NULL,
        money     int         NOT NULL,
        CONSTRAINT Houseinfo_ck
            CHECK ((dir = '朝阳' OR dir = '朝阴') AND
                   (Stru_na = '单居室' OR Stru_na = '两室一厅' OR Stru_na = '三室一厅' OR Stru_na = '三室两厅' OR Stru_na = '四室两厅') AND
                   (fitment = '简装' OR fitment = '毛坯' OR fitment = '精装') AND (facticity = '未审核' OR facticity = '已审核') AND
                   (status = '未交易' OR status = '交易中' OR status = '已交易'))
    )

GO

--求租表

CREATE TABLE Rent
    (
        R_no    varchar(10)  NOT NULL
            CONSTRAINT Rent_pk
                PRIMARY KEY NONCLUSTERED,
        B_no    varchar(10)  NOT NULL
            CONSTRAINT Rent_Buyer_B_no_fk
                REFERENCES Buyer,
        B_tel   char(20)     NOT NULL,
        R_ad    nvarchar(20) NOT NULL,
        R_money int          NOT NULL,
        R_sty   nvarchar(10),
        R_req   nvarchar(20),
        R_add   nvarchar(30)
    )

GO

--订单表
CREATE TABLE B_order
    (
        order_no  int IDENTITY
            CONSTRAINT B_order_pk
                PRIMARY KEY NONCLUSTERED,
        B_no      varchar(10) NOT NULL
            CONSTRAINT B_order_Buyer_B_no_fk
                REFERENCES Buyer,
        S_no      varchar(10) NOT NULL
            CONSTRAINT B_order_Seller_S_no_fk
                REFERENCES Seller,
        H_no      varchar(10) NOT NULL
            CONSTRAINT B_order_Houseinfo_H_no_fk
                REFERENCES Houseinfo,
        order_stu nvarchar(10)
            CONSTRAINT B_order_ck
                CHECK (order_stu = '交易完成' OR order_stu = '提交租房订单' OR order_stu = '提交买房订单')
    )

GO

--合同表

CREATE TABLE contract
    (
        order_no  int         NOT NULL
            CONSTRAINT contract_pk
                PRIMARY KEY NONCLUSTERED
            CONSTRAINT contract_fk
                REFERENCES B_order,
        A_id      varchar(10) NOT NULL
            CONSTRAINT contract_Admin_A_id_fk
                REFERENCES Admin,
        con_style varchar(5)  NOT NULL
            CONSTRAINT contract_ck
                CHECK (con_style = '出租' OR con_style = '出售'),
        A_name    char(10)    NOT NULL,
        con_time  date        NOT NULL,
        fee       int         NOT NULL
    )

GO

--交易视图

CREATE VIEW Deal
AS SELECT order_no,B_no,B_order.S_no,Houseinfo.H_no,price,money,order_stu
FROM B_order,Houseinfo
WHERE Houseinfo.H_no=B_order.H_no AND B_order.S_no=Houseinfo.S_no

GO

`