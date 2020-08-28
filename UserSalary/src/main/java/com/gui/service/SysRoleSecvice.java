package com.gui.service;

import org.springframework.stereotype.Service;

import com.gui.entity.SysRole;

@Service
public interface SysRoleSecvice {
	public SysRole selectById(Integer id); 
	
	public Integer insertRole(String name);
	
	public Integer updateRole(String name, Integer id);

}
