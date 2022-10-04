-- -------------------------------------------------
--   _____       _      _
--  |_   _|__ _ | |__  | |  ___
--    | | / _` || '_ \ | | / _ \
--    | || (_| || |_) || ||  __/
--    |_| \__,_||_.__/ |_| \___|
--
-- 表
-- -------------------------------------------------

drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
    id       int not null auto_increment,
    username varchar(20),
    password varchar(20),
    age      int,
    sex      char,
    email    varchar(20),
    primary key (id)
);

drop table if exists t_emp;

/*==============================================================*/
/* Table: t_emp                                                */
/*==============================================================*/
create table t_emp
(
    eid      int not null auto_increment,
    emp_name varchar(20),
    age      int,
    sex      char,
    email    varchar(20),
    did      int,
    primary key (eid)
);

drop table if exists t_dept;

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
create table t_dept
(
    did       int not null auto_increment,
    dept_name varchar(20),
    primary key (did)
);

drop table if exists mp_user;

/*==============================================================*/
/* Table: user                                                */
/*==============================================================*/
create table mp_user
(
    id      bigint(20) not null,
    name    varchar(20),
    age     int,
    sex     int,
    email   varchar(20),
    deleted int default 0,
    primary key (id)
);

drop table if exists mp_product;

/*==============================================================*/
/* Table: t_product                                                */
/*==============================================================*/
create table mp_product
(
    id      bigint(20)  not null comment '主键ID',
    name    varchar(30) null default null comment '商品名称',
    price   int(11)          default 0 comment '价格',
    version int(11)          default 0 comment '乐观锁版本号',
    primary key (id)
);