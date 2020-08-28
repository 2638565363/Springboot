package com.gui.vo;

import java.util.List;

import lombok.Data;

@Data
public class LayuiVO<T>{
	
	private Integer code = 0;
	String msg = "";
	private Integer count = 0;
	private List<T> data;
}
