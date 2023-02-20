package com.MJR.web.domain;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author 22249
 * @version 1.0
 * @description: 分页响应结果
 * @date 2023/1/3 18:20
 */
@Data
public class PageResponse<T> {

    /***
     * description: 页索引
     * @author Mjr
     * @date 2023/1/3 23:01
     * @version 1.0
     */
    private Long pageNo = 1L;

    /***
     * description: 每页记录数
     * @author Mjr
     * @date 2023/1/3 23:01
     * @version 1.0
     */
    private Long pageSize = 10L;

    /***
     * description: 总记录数
     * @author Mjr
     * @date 2023/1/3 23:02
     * @version 1.0
     */
    private Long total = 0L;

    /***
     * description: 总页数
     * @author Mjr
     * @date 2023/1/3 23:03
     * @version 1.0
     */
    private Long totalPages = 0L;

    /***
     * description: 开始记录行数
     * @author Mjr
     * @date 2023/1/3 23:02
     * @version 1.0
     */
    private Long start = 1L;

    /***
     * description: 结束记录行数
     * @author Mjr
     * @date 2023/1/3 23:02
     * @version 1.0
     */
    private Long end = 0L;

    private List<T> records = Collections.emptyList();

    public PageResponse(){
    }

    public void setCurrentPage(Long pageNo){
        if(pageNo!=null && pageNo>0L){
            this.pageNo = pageNo;
        }
    }

    public void setPageSize(Long pageSize){
        if(pageSize!=null && pageSize>0L){
            this.pageSize = pageSize;
        }
    }

    public void setTotal(Long total){
        this.total = total;
        if(total<0L){
            this.total = 0L;
            return;
        }
        //进行总页数、当前开始记录和结束记录的计算

        if(this.pageSize>0){
            this.totalPages = ( total/pageSize + (total % pageSize > 0?0:1));
        }else{
            this.pageSize = 0L;
        }

        this.start = (this.pageNo > 0 ? (this.pageNo-1)*this.pageSize : 0)+1;

        this.end = (this.start-1) + (this.pageSize>0 ? this.pageSize : 0);
    }

    public void setRecords(List<T> records){
        this.records = records;
        //如果此时的总页数没有记录并且本次分页查询中有数据
        if(records!=null && records.size()>0 && this.total ==0){
            setTotal(Long.valueOf(records.size()));
        }
    }
}
