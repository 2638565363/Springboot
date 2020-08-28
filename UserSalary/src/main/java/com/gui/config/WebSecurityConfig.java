package com.gui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gui.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 //SpringSecurity主要做两件事，一件是认证，一件是授权。
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new BCryptPasswordEncoder();  //注册BCrypt加密类
	}

	 @Bean
	 UserDetailsService customUserService(){ //注册UserDetailsService 的bean,实例化CustomUserDetailsService();该类实现UserDetailsServer接口，重写loadUserByUsername方法，从数据库获取用户名，密码，角色
	      return new CustomUserDetailsService();
	 }
	 
	 @Override
	public void configure(WebSecurity web) throws Exception{
          super.configure(web);  //configure(WebSecurity)用于影响全局安全性(配置资源，设置调试模式，通过实现自定义防火墙定义拒绝请求)的配置设置。
     }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {       //AuthenticationManagerBuilder身份验证管理器
	     auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder()); //把前端传来的数据加密
	 }
	
	@Override
	 protected void configure(HttpSecurity httpSecurity) throws Exception {  //HTTP请求安全处理
        httpSecurity
        	.authorizeRequests()
            	.antMatchers("/js/**").permitAll()
                .antMatchers("/register.html","/register","/login","/login.html").permitAll()
                .antMatchers("/index.html","/announcement.html").hasAnyRole("ADMIN","USER")
                .antMatchers("/UserSalary.html","/update.html","/insert.html").hasRole("ADMIN")
                .anyRequest().authenticated()  //任意匹配此url的用户需要身份验证
                .and()
            .formLogin()
                .loginPage("/login.html")          //未登录时跳转此页面
                .loginProcessingUrl("/login")
                .failureUrl("/login?error")        //登陆失败时的跳转
                .defaultSuccessUrl("/index.html")  //登陆成功后默认跳转路径
                .permitAll()
                .and()
            .rememberMe().tokenValiditySeconds(1209600).key("mykey")
            	.and()
            .logout()
                .logoutSuccessUrl("/login?logout")  //退出登录
                .permitAll()
                .and()
            .csrf().disable();
    }
}