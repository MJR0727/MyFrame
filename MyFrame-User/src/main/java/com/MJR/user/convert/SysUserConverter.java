package com.MJR.user.convert;

import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.entity.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: mapstuct使用setter的方式进行属性注入，提高效率
 * @author Mjr
 * @date 2023/1/4 15:22
 * @version 1.0
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    UserPo convertDtoToSysUser(SysUserDto sysUserDto);
}
