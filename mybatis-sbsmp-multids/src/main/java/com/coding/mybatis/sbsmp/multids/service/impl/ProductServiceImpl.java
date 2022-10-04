package com.coding.mybatis.sbsmp.multids.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.mybatis.sbsmp.multids.mapper.ProductMapper;
import com.coding.mybatis.sbsmp.multids.pojo.Product;
import com.coding.mybatis.sbsmp.multids.service.ProductService;

@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {}
