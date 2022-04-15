package com.example.secbuyservice.service;

import com.example.secbuyservice.config.RabbitMqConfig;
import com.example.secbuyservice.entity.Order;
import com.example.secbuyservice.excpetion.SXException;
import com.example.secbuyservice.util.IdGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lst
 * @version 1.0
 * @Description: MQ订单实现层
 * @date 2019-12-27 15:54
 */
@Service
@Slf4j
public class MQOrderServiceImpl {

    @Autowired
    private OrderService orderService;

    /**
     * MQ监听订单消息队列，并消费
     * @param order
     */
    @RabbitListener(queues = RabbitMqConfig.ORDER_QUEUE)
    public void saveOrder(Order order) throws SXException {
        log.info("收到订单消息，订单用户为：{}，商品名称为：{}", order.getOrderUser(), order.getProductName());
        /**
         * 调用数据库orderService创建订单信息
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        order.setCreateBy(order.getOrderUser());
        order.setCreateDate(date);
        order.setUpdateBy(order.getOrderUser());
        order.setUpdateDate(date);
        order.setDelFlag("0");
        order.setId(IdGenerate.generateId());
        orderService.saveOrder(order);
    }
}
