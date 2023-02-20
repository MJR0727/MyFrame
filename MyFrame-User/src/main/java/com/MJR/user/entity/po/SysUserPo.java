package com.MJR.user.entity.po;

import com.MJR.mybatisplus.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2023-01-03 17:42:35
 */
@TableName("sys_user")
@Data
public class SysUserPo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 131237757101470939L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    
    private String name;
    
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}

