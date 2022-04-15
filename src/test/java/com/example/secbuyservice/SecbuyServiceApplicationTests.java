package com.example.secbuyservice;

import com.example.secbuyservice.dao.OrderMapper;
import com.example.secbuyservice.dao.StockMapper;
import com.example.secbuyservice.entity.Order;
import com.example.secbuyservice.entity.Stock;
import com.example.secbuyservice.util.IdGenerate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class SecbuyServiceApplicationTests {


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateTtime = simpleDateFormat.format(new Date());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    StockMapper stockMapper;

    @Autowired
    Order order;
    @Test
    void contextLoads() {

//       order.setId(IdGenerate.generateId());
//       order.setCreateBy("zyx");
//       order.setCreateDate(new Date());
//       order.setDelFlag("1");
//       order.setproductName("watch");
//       order.setUpdateBy("zyx");
//       order.setOrderUser("zyx");
//       order.setUpdateDate(new Date());
////        System.out.println(redisTemplate.opsForValue().decrement("watch"));
//        orderMapper.insert(order);
//        stockMapper.getproductStocks("watch");

//        System.out.println(simpleDateFormat.format(new Date()));
        Stock  stock = new Stock();
        stock.setProductStocks(82);
        stock.setProductName("iPhone");
        stockMapper.updateProductStock(stock);
        System.out.println(stockMapper.getProductStocks("iPhone"));
    }

}
