package com.MJR.user.entity.po;


import com.MJR.mybatisplus.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class SysUser extends BaseEntity implements UserDetails {

  //主键
  private long userId;

  //账号
  private String userName;

  //加密后的密码
  private String password;

  private String gender;

  private String phoneNumber;

  private String email;

  private String avatar;

  private String nickName;

  private String state;

  private String loginIp;

  private Date loginDate;

  /* 删除标记 0表正常 1表删除*/
  private String deleteFlag;

  //private List<Role> roles;
  //
  //private Long[] roleIds;
  //
  //private Long roleId;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
