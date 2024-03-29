package com.MJR.user.dao;

import com.MJR.user.entity.po.UserPo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-03 17:42:26
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param userPo 查询条件
     * @param pageNo 页面索引 pageSize 分页总页数
     * @return 对象列表
     */
    List<UserPo> queryAllByLimit(@Param("po") UserPo userPo, @Param("pageStart") Long pageStart, @Param("pageSize")Long pageSize);

    /**
     * 统计总行数
     *
     * @param userPo 查询条件
     * @return 总行数
     */
    long count(UserPo userPo);

    /**
     * 新增数据
     *
     * @param userPo 实例对象
     * @return 影响行数
     */
    int insert(UserPo userPo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserPo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserPo> entities);

    /**
     * 修改数据
     *
     * @param userPo 实例对象
     * @return 影响行数
     */
    int update(UserPo userPo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

