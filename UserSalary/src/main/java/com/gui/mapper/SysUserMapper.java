package com.gui.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gui.entity.SysUser;

@Repository
//@repository则需要在Spring中配置扫描包地址，然后生成dao层的bean，之后被注入到ServiceImpl中
public interface SysUserMapper { 
	public Integer insertSysUser(SysUser sysUser);
	
	public SysUser selectByName(@Param("username") String username); //@Param的作用是在Mybatis提供的作为dao层注解，传递参数的工具
	
	public SysUser selectById(@Param("id") Integer id);
	
	public Integer deleteSysUser(String username);
	
	public Integer updateSysUser(SysUser sysUser);
	
	public Integer insertInfoSysUser(SysUser sysUser);
	
	public List<SysUser> selectSysUser();
}
