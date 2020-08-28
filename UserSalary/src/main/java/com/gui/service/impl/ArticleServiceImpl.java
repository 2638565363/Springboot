package com.gui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.entity.Article;
import com.gui.mapper.ArticleMapper;
import com.gui.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleMapper articleMapper;
	
	public Integer insertArticle(Article article) {
		
		return articleMapper.insertArticle(article);
	}
	
	public List<Article> selectArticle() {
		return articleMapper.selectArticle();
	}
	
	public List<Article> selectArticleByAuthor(String Articleauthor) {
		return articleMapper.selectArticleByAuthor(Articleauthor);
	}
	
	public Integer deleteArticle(Integer id) {
		return articleMapper.deleteArticle(id);
	}

}
