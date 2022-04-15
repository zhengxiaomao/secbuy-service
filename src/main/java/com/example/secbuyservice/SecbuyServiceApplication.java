package com.example.secbuyservice;

import com.example.secbuyservice.dao.StockMapper;
import com.example.secbuyservice.entity.Stock;
import com.example.secbuyservice.util.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SecbuyServiceApplication implements ApplicationRunner{

    public static void main(String[] args) {
        SpringApplication.run(SecbuyServiceApplication.class, args);
    }

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StockMapper stockMapper;

    /**
     * redis初始化商品的库存量和信息
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Stock stock = new Stock();
        stock.setProductName("imac");
        stock.setProductStocks(100);
        stockMapper.addProductStock(stock);
        redisUtil.setValue("iPhone",stockMapper.getProductStocks("iPhone"));
        redisUtil.setValue("watch", 10);
    } 

}
