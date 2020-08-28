package com.gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*/
 * 登陆注册页和跳转页
 */
@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {
		return "redirect:/index.html";
	}

}