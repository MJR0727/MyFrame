package com.MJR.mybatisplus.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author 22249
 * @version 1.0
 * @description: 封装分页结果
 * @date 2023/1/2 22:49
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    private Long current;

    private Long total;

    private Long size;

    private Long pages;

    private List<T> records = Collections.emptyList();

    public void loadData(IPage<T> pageData){
        this.current = pageData.getCurrent();
        this.total = pageData.getTotal();
        this.size = pageData.getSize();
        this.pages = pageData.getPages();
        this.records =  pageData.getRecords();
    }
}
