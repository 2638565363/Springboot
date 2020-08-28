package com.gui.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole implements Serializable{
	static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	
	private Integer userId;
	private Integer roleId;
}
