package com.MJR.web.domain;

import com.MJR.web.constant.ResultMessage;
import lombok.Data;

import java.io.Serializable;
import com.MJR.web.constant.HttpStatus;
/**
 * @author 22249
 * @version 1.0
 * @description: 消息返回結果
 * @date 2022/12/2 11:12
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    //方法執行結果
    private Boolean status;

    private Integer code;

    private String message;

    private T data;

    public static <T> R<T> ok(){return restResult(null,true, HttpStatus.SUCCESS, ResultMessage.SUCCESS);}

    public static <T>  R<T> ok(Integer code,String msg){return restResult(null,true,code,msg);}

    public static <T> R<T> ok(Integer code,String msg,T data){return restResult(data,true,code,msg);}

    public static <T> R<T> ok(T data){return restResult(data,true,HttpStatus.SUCCESS,ResultMessage.SUCCESS);}

    public static <T> R<T> fail(){return restResult(null,false,HttpStatus.ERROR,ResultMessage.ERROR);}

    public static <T> R<T> fail(String msg){return restResult(null,false,HttpStatus.ERROR,msg);}

    public static <T>  R<T> fail(Integer code,String msg){return restResult(null,false,code,msg);}

    public static <T> R<T> fail(Integer code,String msg,T data){return restResult(data,false,code,msg);}

    public static <T> R<T> fail(T data){return restResult(data,true,HttpStatus.ERROR,ResultMessage.ERROR);}

    private static <T> R<T> restResult(T data,Boolean status,Integer code,String message){
        R<T> r = new R<>();
        r.setStatus(status);
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}
