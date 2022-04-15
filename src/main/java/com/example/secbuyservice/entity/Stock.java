package com.example.secbuyservice.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author LST
 * @version 1.0
 * @Description: 商品库存表
 * @date 2019-12-27 15:54
 */

@Data
@Component
public class Stock {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */

    private String Id;

    /**
     * 产品名称
     */

    private String productName;


    /**
     * 商品库存数量
     */

    private int productStocks;

}