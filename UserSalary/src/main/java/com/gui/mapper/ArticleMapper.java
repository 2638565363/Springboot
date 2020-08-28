package com.gui.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gui.entity.Article;

@Repository
public interface ArticleMapper {
	public Integer insertArticle(Article article);
	
	public List<Article> selectArticle();
	
	public List<Article> selectArticleByAuthor(String articleauthor);
	
	public Integer deleteArticle(Integer id);
}
