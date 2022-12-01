package com.MJR.user.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class UserPo {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

}
