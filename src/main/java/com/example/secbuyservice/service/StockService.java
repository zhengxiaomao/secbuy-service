package com.example.secbuyservice.service;

import com.example.secbuyservice.entity.Stock;
import com.example.secbuyservice.excpetion.SXException;

/**
 * @author lst
 * @version 1.0
 * @Description: 存货服务层
 * @date 2019-12-27 15:54
 */
interface productStockservice {
    /**
     * 秒杀商品后-减少库存
     * @param name 商品名称
     */
    void decrStock(String name);

    /**
     * 秒杀商品前判断是否有库存
     * @param name 商品名称
     * @return
     */
    Boolean checkStock(String name);

    /**
     * 实现纯数据库操作实现秒杀操作
     * @param userName 用户名称
     * @param productName 商品名称
     * @return String
     */
    String secDataBase(String userName,String productName) throws SXException;
}