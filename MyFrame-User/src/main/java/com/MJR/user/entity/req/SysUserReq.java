package com.MJR.user.entity.req;

import com.MJR.web.domain.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/1/3 22:41
 */
@Data
public class SysUserReq extends PageRequest implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer version;
}
