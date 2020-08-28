package com.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gui.service.impl.ArticleServiceImpl;

@Controller
public class ArticleController {
	@Autowired
	private ArticleServiceImpl articleService;
	
	//删除
		@PostMapping(value= "/deleteArticle/{id}" , produces = "application/json") //produces可以设置返回值的类型和编码类型
		@ResponseBody
		public String getDelete(@PathVariable Integer id) {
			Integer i = articleService.deleteArticle(id);
			if(i == 1) {
				return "true";
			}else {
				return "false";
			}
		}
}
