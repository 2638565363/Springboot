package com.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gui.entity.SysUser;
import com.gui.service.SysRoleSecvice;
import com.gui.service.SysUserRoleService;
import com.gui.service.SysUserService;

@Controller
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleSecvice sysRoleService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@PostMapping(value= "/sysUserDelete/{username}" , produces = "application/json") //produces可以设置返回值的类型和编码类型
	@ResponseBody
	public String getDelete(@PathVariable String username) {
		Integer i = sysUserService.deleteSysUser(username);
		if(i == 1) {
			return "true";
		}else {
			return "false";
		}
	}
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/sysUserInsert")
	public String postInsert(SysUser sysuser,String name) {
		sysuser.setPassword1("123456");
		sysuser.setPassword(passwordEncoder.encode("123456"));
		
		sysUserService.insertInfoSysUser(sysuser);
		sysRoleService.insertRole(name);
		
		Integer userId = sysUserService.selectByName(sysuser.getUsername()).getId();
		sysUserRoleService.sysUserRoleInsert(userId, userId);
		return "redirect:/userInfo.html";
	}
	
	@PostMapping("/sysUserUpdate")
	public String postUpdate(SysUser sysUser, String name) {
		
		sysUserService.updateSysUser(sysUser);
		
		Integer id = sysUserService.selectByName(sysUser.getUsername()).getId();
		sysRoleService.updateRole(name,id);
		
		return "redirect:/userInfo.html";
		
	}
}
