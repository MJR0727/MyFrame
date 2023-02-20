package com.MJR.user.convert;

import com.MJR.user.entity.dto.SysUserDto;
import com.MJR.user.entity.po.SysUserPo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-19T22:34:28+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class SysUserConverterImpl implements SysUserConverter {

    @Override
    public SysUserPo convertDtoToSysUser(SysUserDto sysUserDto) {
        if ( sysUserDto == null ) {
            return null;
        }

        SysUserPo sysUserPo = new SysUserPo();

        return sysUserPo;
    }
}
