package com.gui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.entity.SysUserRole;
import com.gui.mapper.SysUserRoleMapper;
import com.gui.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	public List<SysUserRole> listByUserId(Integer userId){
		return sysUserRoleMapper.listByUserId(userId);
	}
	
	public Integer sysUserRoleInsert(Integer user_id, Integer role_id) {
		return sysUserRoleMapper.sysUserRoleInsert(user_id, role_id);
	}


}
