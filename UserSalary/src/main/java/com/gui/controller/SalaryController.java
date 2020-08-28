package com.gui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gui.entity.Salary;

import com.gui.service.impl.SalaryServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*/
 * 增删改查页面
 */

@Controller
public class SalaryController {
	@Autowired
	private SalaryServiceImpl salaryService;
	
	@PostMapping("/insert")
	public String postInsert(Salary salary) {
		salaryService.insertSalary(salary);
		return "redirect:/UserSalary.html";
	}
	
	@PostMapping("/update")
	public String postUpdate(Salary salary) {
		salaryService.updateSalary(salary);
		return "redirect:/UserSalary.html";
	}
	
	//删除
	@PostMapping(value= "/delete/{id}" , produces = "application/json") //produces可以设置返回值的类型和编码类型
	@ResponseBody
	public String getDelete(@PathVariable String id) {
		Integer i = salaryService.deleteSalary(id);
		if(i == 1) {
			return "true";
		}else {
			return "false";
		}
	}
	
}
