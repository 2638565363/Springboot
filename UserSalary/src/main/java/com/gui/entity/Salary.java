package com.gui.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//员工信息类
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString

public class Salary {
	@Id
	private String id;
	private String name;
	private String sex;
	private String time;
	private String birth;
	private String salary;
	private String home;
	@Temporal(TemporalType.TIMESTAMP) 
	private Date createDateTime;
	
}
