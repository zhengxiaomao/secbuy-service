package com.example.secbuyservice.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultReturn implements Serializable {

    private static final long serialVersionUID = 5805792987639183304L;
    /**
     * 0成功  -1失败
     */
    private String code;

    private String msg;

    private Object data;

    public ResultReturn(){
        super();
    }

    public ResultReturn(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultReturn(String code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}