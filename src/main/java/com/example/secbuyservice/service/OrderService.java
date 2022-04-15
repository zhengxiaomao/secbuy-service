package com.example.secbuyservice.service;


import com.example.secbuyservice.entity.Order;
import com.example.secbuyservice.excpetion.SXException;

public interface OrderService  {
    /**
     * 订单保存
     * @param order 实体
     */
    void saveOrder(Order order) throws SXException;

}
