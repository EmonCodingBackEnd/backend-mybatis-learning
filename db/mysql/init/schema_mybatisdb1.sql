-- 创建数据库
CREATE DATABASE IF NOT EXISTS mybatisdb1 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
use mybatisdb1;

drop table if exists mp_product;

/*==============================================================*/
/* Table: mp_product                                                */
/*==============================================================*/
create table mp_product
(
    id      bigint(20)  not null comment '主键ID',
    name    varchar(30) null default null comment '商品名称',
    price   int(11)          default 0 comment '价格',
    version int(11)          default 0 comment '乐观锁版本号',
    primary key (id)
);

insert into mp_product(id, name, price)
values (1, '外星人笔记本', 100);