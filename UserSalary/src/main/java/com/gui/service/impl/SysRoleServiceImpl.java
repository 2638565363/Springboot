package com.gui.service.impl;

import com.gui.entity.SysRole;

import com.gui.mapper.SysRoleMapper;
import com.gui.service.SysRoleSecvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysRoleServiceImpl implements SysRoleSecvice{
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public SysRole selectById(Integer id) {
		return sysRoleMapper.selectById(id);
	}
	
	@Override
	public Integer insertRole(String name) {
		return sysRoleMapper.insertRole(name);
	}
	
	@Override
	public Integer updateRole(String name,Integer id) {
		return sysRoleMapper.updateRole(name,id);
	}
}
