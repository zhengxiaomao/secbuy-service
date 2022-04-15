package com.example.secbuyservice.dao;

import com.example.secbuyservice.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper {

//    @Insert("insert into order(order_name,order_user,create_by,update_by,update_date,create_date,del_flag) values (#{productName},#{orderUser},#{createBy},#{updateBy},#{updateDate},#{createDate},#{delFlag})")
    public int  insert(Order order);
}
