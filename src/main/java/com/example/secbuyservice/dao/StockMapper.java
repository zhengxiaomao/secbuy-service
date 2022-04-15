package com.example.secbuyservice.dao;


import com.example.secbuyservice.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper
public interface StockMapper {

   /**
    * @Author zhengyinxiang
    * @Date 2022.5.15 17:38
    * 定义商品库存操作接口
    */

   //查询商品库存
    public int getProductStocks(String productName);

    //更新商品库存
    public void updateProductStock(Stock stock);

    //增加商品库存
    public void addProductStock(Stock stock);

}
