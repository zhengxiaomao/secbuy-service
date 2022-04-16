package com.example.secbuyservice.controller;

import com.example.secbuyservice.service.SecBuyService;
import com.example.secbuyservice.vo.ResultReturn;
import com.example.secbuyservice.vo.ResultReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author LST
 * @version 1.0
 * @Description: 秒杀
 * @date 2019-12-28 20:59
 */
@RestController
@Api(value = "SecKillController",  tags = "秒杀控制层")
@Slf4j
public class SecKillController {


    @Autowired
    private SecBuyService mQproductStockservice;




    /**
     * 使用redis+消息队列进行秒杀实现
     * @param userName 用户名称
     * @param productName 商品名称
     * @return String
     */
    @PostMapping(value = "sec-kill",produces = "application/json")
    @ApiOperation(value = "redis+消息队列进行秒杀实现", notes = "redis+消息队列进行秒杀实现", produces = "application/json")
    public ResultReturn secKill(@RequestParam(value = "userName") String userName, @RequestParam(value = "productName") String productName) {
        return mQproductStockservice.secKill(userName, productName);
    }


    /**
     * 实现纯数据库操作实现秒杀操作
     * @param userName 用户名称
     * @param productName 商品名称
     * @return String
     */
//    @PostMapping(value = "sec-data-base",produces = "application/json;")
//    @ApiOperation(value = "实现纯数据库操作实现秒杀操作", notes = "实现纯数据库操作实现秒杀操作", produces = "application/json")
//    public RestResponse secDataBase(@RequestParam(value = "userName") String userName, @RequestParam(value = "productName") String productName) throws SXException {
//        return ResultGenerator.genResult(productStockservice.secDataBase(userName, productName));
//    }


}
