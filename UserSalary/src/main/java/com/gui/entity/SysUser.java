package com.gui.entity;

import java.io.Serializable;

/*
 * 登陆账号密码类
 * 定义用户
 */
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SysUser implements Serializable{
	static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String username;  //账号
	private String password;
	private String password1;  //用户的账号信息
	
	
	private String uname;   //用户姓名
	private String sex;
	private String birth;
	private String home;  //用户信息

}
