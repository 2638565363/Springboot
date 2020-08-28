package com.gui.service.impl;

import com.gui.entity.Salary;
import com.gui.mapper.SalaryMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.service.SalaryService;

@Service("SalaryService")
public class SalaryServiceImpl implements SalaryService{
	
	@Autowired
	private SalaryMapper salaryMapper;
	@Override
	public List<Salary> listSalaryAll(){
		return salaryMapper.listSalaryAll();
	}
	@Override
	public List<Salary> selectSalaryById(String id) {
		return salaryMapper.selectSalaryById(id);
	}
	@Override
	public Integer deleteSalary(String id) {
		return salaryMapper.deleteSalary(id);
	}
	@Override
	public Integer insertSalary(Salary salary) {
		
		return salaryMapper.insertSalary(salary);
	}
	@Override
	public Integer updateSalary(Salary salary) {
		return salaryMapper.updateSalary(salary);
	}
	
}
