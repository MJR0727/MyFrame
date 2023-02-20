package com.MJR.user.service;

import com.MJR.web.domain.PageResponse;
import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.entity.po.SysUserPo;

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
    SysUserPo queryById(Long id);

    /**
     * 分页查询
     *
     * @param sysUserDto 分页参数对象
     * @return 查询结果
     */
    PageResponse<SysUserPo> queryByPage(SysUserDto sysUserDto);

    /**
     * 新增数据
     *
     * @param sysUserPo 实例对象
     * @return 实例对象
     */
    SysUserPo insert(SysUserPo sysUserPo);

    /**
     * 修改数据
     *
     * @param sysUserPo 实例对象
     * @return 实例对象
     */
    SysUserPo update(SysUserPo sysUserPo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
