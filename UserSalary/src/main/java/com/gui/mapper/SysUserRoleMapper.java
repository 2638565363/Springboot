package com.gui.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gui.entity.SysUserRole;

@Repository
public interface SysUserRoleMapper {
	
	List<SysUserRole> listByUserId(Integer userId);
	
	public Integer sysUserRoleInsert(Integer user_id, Integer role_id);
	
}
