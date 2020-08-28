package com.gui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gui.entity.Salary;

@Service
public interface SalaryService {
	public List<Salary> listSalaryAll();
	public List<Salary> selectSalaryById(String id);
	public Integer deleteSalary(String id);
	public Integer insertSalary(Salary salary);
	public Integer updateSalary(Salary salary);
}
