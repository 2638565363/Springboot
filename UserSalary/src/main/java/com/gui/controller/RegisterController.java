package com.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import com.gui.service.SysUserRoleService;
import com.gui.service.impl.SysRoleServiceImpl;
import com.gui.service.impl.SysUserServiceImpl;
import com.gui.entity.SysUser;

@Controller
public class RegisterController {
	
	@Autowired
	private SysUserServiceImpl sysUserService;
	
	@Autowired
	private SysRoleServiceImpl sysRoleService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/register")
	public String postRegisterController(SysUser sysUser, String name) {
		
		String encodedPassword = passwordEncoder.encode(sysUser.getPassword().trim());
		sysUser.setPassword(encodedPassword);
		
		sysUserService.insertUser(sysUser);  
		sysRoleService.insertRole(name); 
		
		Integer userId = sysUserService.selectByName(sysUser.getUsername()).getId();
		sysUserRoleService.sysUserRoleInsert(userId, userId);
		return "login.html";
	}
}
