package com.example.secbuyservice.service;

import com.example.secbuyservice.config.RabbitMqConfig;
import com.example.secbuyservice.dao.StockMapper;
import com.example.secbuyservice.entity.Order;
import com.example.secbuyservice.entity.Stock;
import com.example.secbuyservice.excpetion.SXException;
import com.example.secbuyservice.util.IdGenerate;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lst
 * @version 1.0
 * @Description: 存货实现层
 * @date 2019-12-27 15:54
 */
@Service
@Slf4j
class productStockserviceImpl  implements productStockservice {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    Stock stock;

    /**
     * 秒杀商品后-减少库存
     * @param productName 商品名称
     */
    @Override
    @RabbitListener(queues = RabbitMqConfig.STORY_QUEUE)
    public void decrStock(String productName) {

        /**
         * 秒杀商品后-减少库存
         * @param name 商品名称
         */
            log.info("库存消息队列收到的消息商品信息是：{}", productName);
            //查询商品库存数
            if(checkStock(productName)){
                int  productStocks = stockMapper.getProductStocks(productName);
                stock.setProductName(productName);
                stock.setProductStocks(productStocks-1);
                //更新库存
                stockMapper.updateProductStock(stock);
            }

    }

    /**
     * 秒杀商品前判断是否有库存
     * @param productName 商品名称
     * @return
     */
    @Override
    public Boolean checkStock(String productName) {
        int productStocks = stockMapper.getProductStocks(productName);
        if(productStocks > 0){
           return true;
        }else {
            return false;
        }

    }

    /**
     * 实现纯数据库操作实现秒杀操作
     * @param userName 用户名称
     * @param productName 商品名称
     * @return String
     */
    @Override
    public String secDataBase(String userName, String productName) throws SXException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        log.info("参加秒杀的用户是：{}，秒杀的商品是：{}", userName, productName);
        String message = null;
        //查找该商品库存
        Boolean stockCount = checkStock(productName);
        log.info("用户：{}参加秒杀，当前商品库存量是：{}", userName, stockCount);
        if (stockCount) {
            /**
             * 还有库存，可以进行继续秒杀，库存减一,下订单
             */
            //1、库存减一
            decrStock(productName);
            //2、下订单
            Order order = new Order();
            order.setOrderUser(userName);
            order.setProductName(productName);
            order.setCreateBy(userName);
            order.setCreateDate(date);
            order.setUpdateBy(userName);
            order.setUpdateDate(date);
            order.setDelFlag("0");
            order.setId(IdGenerate.generateId());
            orderService.saveOrder(order);
            log.info("用户：{}.参加秒杀结果是：成功", userName);
            message = userName + "参加秒杀结果是：成功";
        } else {
            log.info("用户：{}.参加秒杀结果是：秒杀已经结束", userName);
            message = userName + "参加秒杀活动结果是：秒杀已经结束";
        }
        return message;
    }
}
