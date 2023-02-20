package com.MJR.web.domain;

import lombok.Setter;

/**
 * @author 22249
 * @version 1.0
 * @description:  前端分页参数
 * @date 2023/1/3 18:16
 */
@Setter
public class PageRequest {

    private Long pageNo = 1L;

    private Long pageSize = 10L;

    public Long getPageNo(){
        if(pageNo==null||pageNo<1L){
            return 1L;
        }
        return this.pageNo;
    }

    public Long getPageSize(){
        if(pageSize==null||pageSize<1L||pageSize>Long.MAX_VALUE){
            return 10L;
        }
        return this.pageSize;
    }
}
