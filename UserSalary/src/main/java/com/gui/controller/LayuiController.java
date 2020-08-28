package com.gui.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gui.entity.Article;
import com.gui.entity.Salary;
import com.gui.entity.SysUser;
import com.gui.service.ArticleService;
import com.gui.service.SalaryService;
import com.gui.service.SysUserService;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController 
//RestConreoller只能返回字符串，而Controller可以返回指定页面
//主页数据实现
public class LayuiController {
	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/sysUserLayui")
	HashMap<String, Object> sysUserLayui(int page, int limit){
		HashMap<String, Object> map = new HashMap<>();
		PageHelper.startPage(page, limit);
		List<SysUser> list = sysUserService.selectSysUser();
		PageInfo<SysUser> pageInfo = new PageInfo<>(list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/salaryLayui")
	HashMap<String, Object> getAll(HttpServletRequest request, int page, int limit){
		HashMap<String, Object> map = new HashMap<>();
		String type = request.getParameter("type");  //getParameter得到String类型，读取提交表单中的值或某个表单提交过的数据
		
		if(type == null) { //当表单提交数据为空或未提交时，展示数据库中所有数据
		PageHelper.startPage(page, limit);
		List<Salary> list = salaryService.listSalaryAll();
		PageInfo<Salary> pageInfo = new PageInfo<>(list);
		
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", list);
		
		return map;
		}else {
			List<Salary> list = salaryService.selectSalaryById(type);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count",1);
			map.put("data", list);
			return map;
		}
	}
	
	@RequestMapping("/articleLayui")
	HashMap<String, Object> getArticle(int page, int limit){
		HashMap<String, Object> map = new HashMap<>();
		PageHelper.startPage(page, limit);
		List<Article> list = articleService.selectArticle();
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count",pageInfo.getTotal());
		map.put("data", list);
		return map;
	}
	
	@RequestMapping(value = "/articleLayuiByAuthor", method = RequestMethod.GET)
    @ResponseBody
	HashMap<String, Object> currentUserName(int page, int limit,Authentication authentication){
		String username = authentication.getName();
		HashMap<String, Object> map = new HashMap<>();
		PageHelper.startPage(page, limit);
		List<Article> list = articleService.selectArticleByAuthor(username);
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count",pageInfo.getTotal());
		map.put("data", list);
		return map;
	}
}