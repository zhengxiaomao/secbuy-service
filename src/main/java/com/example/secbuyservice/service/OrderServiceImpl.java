package com.example.secbuyservice.service;

import com.example.secbuyservice.config.ServiceExceptionEnum;
import com.example.secbuyservice.dao.OrderMapper;
import com.example.secbuyservice.entity.Order;
import com.example.secbuyservice.excpetion.SXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lst
 * @version 1.0
 * @Description: 订单实现层
 * @date 2019-12-27 15:54
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 订单保存
     * @param order 实体
     */
    @Override
    public void saveOrder(Order order) throws SXException {
        if(orderMapper.insert(order) <= 0){
            throw new SXException(ServiceExceptionEnum.DATA_INSERT_EXCEPTION);
        }
    }
}