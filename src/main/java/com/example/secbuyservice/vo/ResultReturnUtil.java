package com.example.secbuyservice.vo;

public class ResultReturnUtil {

    /**
     * 成功 返回默认码值
     * @param msg
     * @return
     */
    public static ResultReturn success(String msg){
        return new ResultReturn("0",msg);
    }

    /**
     * 成功  返回自定义码值
     * @param code
     * @param msg
     * @return
     */
    public static ResultReturn success(String code, String msg,Object data){
        return new ResultReturn(code,msg,data);
    }

    public static ResultReturn success(String code, String msg){
        return  new ResultReturn(code,msg);
    }


    public static ResultReturn fail(String code, String msg, Object data){
        return new ResultReturn(code,msg,data);
    }


}
