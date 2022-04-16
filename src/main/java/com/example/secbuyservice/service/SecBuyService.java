package com.example.secbuyservice.service;

import com.example.secbuyservice.config.RabbitMqConfig;
import com.example.secbuyservice.dao.StockMapper;
import com.example.secbuyservice.entity.Order;
import com.example.secbuyservice.entity.Stock;
import com.example.secbuyservice.util.RedisUtil;
import com.example.secbuyservice.vo.Constant;
import com.example.secbuyservice.vo.ResultReturn;
import com.example.secbuyservice.vo.ResultReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lst
 * @version 1.0
 * @Description: 秒杀实现逻辑
 * @date 2019-12-27 15:54
 */
@Service
@Slf4j
public class SecBuyService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisUtil redisUtil;




    /**
     * 使用redis+消息队列进行秒杀实现
     * @param userName 用户名称
     * @param productName 商品名称
     * @return String
     */
    public ResultReturn secKill(String userName, String productName) {
        log.info("参加秒杀的用户是：{}，秒杀的商品是：{}", userName, productName);
        String message = "";
        //调用redis给相应商品库存量减一
        Long decrResult = redisUtil.decrStock(productName);
        if (decrResult > 0) {
            /**
             * 说明该商品的库存量有剩余，可以进行下订单操作
             */
            log.info("用户：{}秒杀该商品：{}库存剩余{}，可以进行下订单操作", userName, productName,decrResult);
            //发消息给库存消息队列，将库存数据减一
            rabbitTemplate.convertAndSend(RabbitMqConfig.STORY_EXCHANGE, RabbitMqConfig.STORY_ROUTING_KEY, productName);

            //发消息给订单消息队列，创建订单
            Order order = new Order();
            order.setProductName(productName);
            order.setOrderUser(userName);
            rabbitTemplate.convertAndSend(RabbitMqConfig.ORDER_EXCHANGE, RabbitMqConfig.ORDER_ROUTING_KEY, order);
            message = "用户" + userName + "秒杀" + productName + "成功,"+"剩余"+stockMapper.getProductStocks(productName)+"台";
            return ResultReturnUtil.success("0", Constant.SECKILL_SUCCESS,message);
        } else {
            /**
             * 说明该商品的库存量没有剩余，直接返回秒杀失败的消息给用户
             */
            Stock stock = new Stock();
            stock.setProductStocks(0);
            stock.setProductName(productName);
            stockMapper.updateProductStock(stock);
            log.info("用户：{}秒杀时商品的库存量没有剩余,秒杀结束", userName);
            message = "商品"+productName+"的库存量没有剩余,秒杀结束";
            return ResultReturnUtil.fail("1",Constant.SECKILL_FAIL,message);

        }

    }

}