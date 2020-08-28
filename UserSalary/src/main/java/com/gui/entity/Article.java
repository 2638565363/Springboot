package com.gui.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Article {
	@Id
	private Integer articleid;
	private String articlename;
	private String articleauthor;
	private String articletime;
}
