package com.example.secbuyservice.entity;


import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @author LST
 * @version 1.0
 * @Description: 订单
 * @date 2019-12-27 15:54
 */

@Data
@Component
public class Order {

    private static final long serialVersionUID = 1L;


    /**
     * 订单id
     */

    private String id;

    /**
     * 订单名称
     */

    private String productName;


    /**
     * 订单用户
     */

    private String orderUser;

    /**
     * 订单创建人
     */


    private String createBy;

    /**
     * 订单更新人
     */

    private String updateBy;

    /**
     * 订单更新时间
     */

    private String updateDate;

    /**
     * 订单创建时间
     */

    private String createDate;

    /**
     * 删除标志
     */
    private String delFlag;

}