package com.MJR.user.controller;

import com.MJR.web.domain.PageResponse;
import com.MJR.web.domain.R;
import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.entity.po.UserPo;
import com.MJR.user.entity.req.SysUserReq;
import com.MJR.user.service.SysUserService;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2023-01-03 17:42:26
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 分页查询
     * @param sysUserReq 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public R<PageResponse<UserPo>> queryByPage(SysUserReq sysUserReq) {
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(sysUserReq,sysUserDto);
        return R.ok(this.sysUserService.queryByPage(sysUserDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<UserPo> queryById(@PathVariable("id") Long id) {
        return R.ok(this.sysUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userPo 实体
     * @return 新增结果
     */
    @PostMapping
    public R<UserPo> add(UserPo userPo) {
        return R.ok(this.sysUserService.insert(userPo));
    }

    /**
     * 编辑数据
     *
     * @param userPo 实体
     * @return 编辑结果
     */
    @PutMapping
    public R<UserPo> edit(UserPo userPo) {
        return R.ok(this.sysUserService.update(userPo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public R<Boolean> deleteById(Long id) {
        return R.ok(this.sysUserService.deleteById(id));
    }

}

