package com.gui.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gui.entity.SysRole;
import com.gui.entity.SysUser;
import com.gui.entity.SysUserRole;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysRoleSecvice roleSecvice;
	
	@Autowired
	private SysUserRoleService userRoleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Collection<GrantedAuthority> authorities = new ArrayList<>();
		//从数据库中读取信息
		SysUser user = userService.selectByName(username);  //获得用户信息
		
		//判断用户是否存在
		if(user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		
		//添加权限
		List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
		for(SysUserRole userRole : userRoles) {
			SysRole role = roleSecvice.selectById(userRole.getRoleId());
			authorities.add(new SimpleGrantedAuthority(role.getName()));//用authorities储存对应用户的权限
		}
		
		//返回UserDetails的实现类
		String password = user.getPassword(); //得到加密后的密码
		return new User(user.getUsername(), password, authorities);
	}
}
