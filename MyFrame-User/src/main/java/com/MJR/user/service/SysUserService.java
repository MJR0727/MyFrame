package com.MJR.user.service;

import com.MJR.user.entity.po.UserPo;
import com.MJR.web.domain.PageResponse;
import com.MJR.user.entity.dto.SysUserDto;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2023-01-03 17:42:35
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPo queryById(Long id);

    /**
     * 分页查询
     *
     * @param sysUserDto 分页参数对象
     * @return 查询结果
     */
    PageResponse<UserPo> queryByPage(SysUserDto sysUserDto);

    /**
     * 新增数据
     *
     * @param userPo 实例对象
     * @return 实例对象
     */
    UserPo insert(UserPo userPo);

    /**
     * 修改数据
     *
     * @param userPo 实例对象
     * @return 实例对象
     */
    UserPo update(UserPo userPo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
