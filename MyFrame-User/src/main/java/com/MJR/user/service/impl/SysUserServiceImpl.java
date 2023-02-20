package com.MJR.user.service.impl;

import com.MJR.web.domain.PageResponse;
import com.MJR.user.convert.SysUserConverter;
import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.entity.po.SysUserPo;
import com.MJR.user.dao.SysUserDao;
import com.MJR.user.service.SysUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2023-01-03 17:42:35
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserPo queryById(Long id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageResponse<SysUserPo> queryByPage(SysUserDto sysUserDto) {
        SysUserPo sysUserPo = SysUserConverter.INSTANCE.convertDtoToSysUser(sysUserDto);
        PageResponse<SysUserPo> pageResponse = new PageResponse<>();
        pageResponse.setPageSize(sysUserDto.getPageSize());
        Long pageNo = sysUserDto.getPageNo();
        pageResponse.setCurrentPage(pageNo);
        Long pageStart = (pageNo >0 ? (pageNo-1)*sysUserDto.getPageSize():0)+1;
        long total = this.sysUserDao.count(sysUserPo);
        List<SysUserPo> sysUserPoList = this.sysUserDao.queryAllByLimit(sysUserPo,pageStart,sysUserDto.getPageSize());
        pageResponse.setTotal(total);
        pageResponse.setRecords(sysUserPoList);
        return pageResponse;
    }

    /**
     * 新增数据
     *
     * @param sysUserPo 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserPo insert(SysUserPo sysUserPo) {
        this.sysUserDao.insert(sysUserPo);
        return sysUserPo;
    }

    /**
     * 修改数据
     *
     * @param sysUserPo 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserPo update(SysUserPo sysUserPo) {
        this.sysUserDao.update(sysUserPo);
        return this.queryById(sysUserPo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDao.deleteById(id) > 0;
    }
}
