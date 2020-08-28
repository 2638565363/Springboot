package com.gui.mapper;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.gui.entity.Salary;

@Repository
public interface SalaryMapper {
	public List<Salary> listSalaryAll();
	public List<Salary> selectSalaryById(String id);
	public Integer insertSalary(Salary salary);
	public Integer updateSalary(Salary salary);
	public Integer deleteSalary(String id);
	
}
