package com.MJR.user.service.impl;

import com.MJR.user.entity.po.UserPo;
import com.MJR.web.domain.PageResponse;
import com.MJR.user.convert.SysUserConverter;
import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.dao.UserDao;
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
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserPo queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageResponse<UserPo> queryByPage(SysUserDto sysUserDto) {
        UserPo userPo = SysUserConverter.INSTANCE.convertDtoToSysUser(sysUserDto);
        PageResponse<UserPo> pageResponse = new PageResponse<>();
        pageResponse.setPageSize(sysUserDto.getPageSize());
        Long pageNo = sysUserDto.getPageNo();
        pageResponse.setCurrentPage(pageNo);
        Long pageStart = (pageNo >0 ? (pageNo-1)*sysUserDto.getPageSize():0)+1;
        long total = this.userDao.count(userPo);
        List<UserPo> userPoList = this.userDao.queryAllByLimit(userPo,pageStart,sysUserDto.getPageSize());
        pageResponse.setTotal(total);
        pageResponse.setRecords(userPoList);
        return pageResponse;
    }

    /**
     * 新增数据
     *
     * @param userPo 实例对象
     * @return 实例对象
     */
    @Override
    public UserPo insert(UserPo userPo) {
        this.userDao.insert(userPo);
        return userPo;
    }

    /**
     * 修改数据
     *
     * @param userPo 实例对象
     * @return 实例对象
     */
    @Override
    public UserPo update(UserPo userPo) {
        this.userDao.update(userPo);
        return this.queryById(userPo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }
}
