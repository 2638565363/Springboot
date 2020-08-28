package com.gui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.entity.SysUser;
import com.gui.mapper.SysUserMapper;
import com.gui.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService{

		@Autowired
		private SysUserMapper sysUserMapper;
		
		@Override
		public Integer insertUser(SysUser sysUser) {
			return sysUserMapper.insertSysUser(sysUser);
		}
		
		@Override
		public SysUser selectByName(String username) {
			return sysUserMapper.selectByName(username);
		}
		
		@Override
		public SysUser selectById(Integer id) {
			return sysUserMapper.selectById(id);
		}
		
		@Override
		public Integer deleteSysUser(String username) {
			return sysUserMapper.deleteSysUser(username);
		}
		
		@Override
		public Integer updateSysUser(SysUser sysUser) {
			return sysUserMapper.updateSysUser(sysUser);
		}
		
		@Override
		public Integer insertInfoSysUser(SysUser sysUser) {
			return sysUserMapper.insertInfoSysUser(sysUser);
		}
		
		@Override
		public List<SysUser> selectSysUser() {
			return sysUserMapper.selectSysUser();
		}
		
}