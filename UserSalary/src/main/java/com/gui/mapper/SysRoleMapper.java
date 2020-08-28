package com.gui.mapper;

import org.springframework.stereotype.Repository;

import com.gui.entity.SysRole;

@Repository
public interface SysRoleMapper {
	public SysRole selectById(Integer id);
	
	public Integer insertRole(String name);
	
	public Integer updateRole(String name, Integer id);
}
