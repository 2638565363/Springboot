package com.gui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gui.entity.Article;

@Service
public interface ArticleService {
	
	public Integer insertArticle(Article article);
	
	public List<Article> selectArticle();
	
	public List<Article> selectArticleByAuthor(String articleauthor);
	
	public Integer deleteArticle(Integer id);
	
}
