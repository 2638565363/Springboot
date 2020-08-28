package com.gui.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.gui.config.intercepors.LoginInterceptor;


//实现.html类的访问

@Configuration
//表示配置类
public class MyMvcConfig implements WebMvcConfigurer{  //WebMvcConfigurer中包含几个方法，addInterceptors拦截器、addViewController页面跳转、addResourceHandlers静态资源
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {  //对视图控制器的快速配置  ViewControllerRegistry是视图控制器
		registry.addViewController("/insert.html").setViewName("insert");
		registry.addViewController("/UserSalary.html").setViewName("UserSalary");
		registry.addViewController("/update.html").setViewName("update");
		registry.addViewController("/register.html").setViewName("register");
		registry.addViewController("/login.html").setViewName("login");
		registry.addViewController("/announcement.html").setViewName("announcement");
		registry.addViewController("/myAnnouncement.html").setViewName("myAnnouncement");
		registry.addViewController("/userInfo.html").setViewName("userInfo");
		registry.addViewController("/index.html").setViewName("index");
		registry.addViewController("/sysUserInsert.html").setViewName("sysUserInsert");
		registry.addViewController("/sysUserUpdate.html").setViewName("sysUserUpdate");
	}
	
}
