package com.MJR.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2022/10/1 15:59
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createBy",String.class,"MJR");
        this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        this.strictInsertFill(metaObject,"deleteFlag",Integer.class,0);
        this.strictInsertFill(metaObject,"version",Integer.class,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updateBy",String.class,"MJR");
        this.strictInsertFill(metaObject,"updateTime", Date.class,new Date());
    }
}
