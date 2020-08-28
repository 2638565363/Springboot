package com.gui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gui.entity.SysUserRole;


@Service
public interface SysUserRoleService {
	List<SysUserRole> listByUserId(Integer userId);
	
	public Integer sysUserRoleInsert(Integer user_id, Integer role_id);
}
