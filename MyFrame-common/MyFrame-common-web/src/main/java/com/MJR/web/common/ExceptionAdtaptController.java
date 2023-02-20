package com.MJR.web.common;

import com.MJR.web.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 22249
 * @version 1.0
 * @description: 全局异常处理
 * @date 2022/12/3 11:18
 */
@RestControllerAdvice
public class ExceptionAdtaptController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdtaptController.class);

    @ExceptionHandler(RuntimeException.class)
    public R runTimeException(RuntimeException e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.fail(e.getMessage());
    }
}
