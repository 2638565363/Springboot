package com.gui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gui.entity.SysUser;

@Service
public interface SysUserService {
	
	public Integer insertUser(SysUser sysUser);
	
	public SysUser selectByName(String username);
	
	public SysUser selectById(Integer id);

	public Integer deleteSysUser(String username);
	
	public Integer updateSysUser(SysUser sysUser);
	
	public Integer insertInfoSysUser(SysUser sysUser);
	
	public List<SysUser> selectSysUser();

}
