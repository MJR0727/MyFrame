package com.MJR.user.convert;

import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.entity.po.UserPo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-06T00:12:22+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class SysUserConverterImpl implements SysUserConverter {

    @Override
    public UserPo convertDtoToSysUser(SysUserDto sysUserDto) {
        if ( sysUserDto == null ) {
            return null;
        }

        UserPo userPo = new UserPo();

        return userPo;
    }
}
