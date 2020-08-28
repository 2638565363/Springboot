package com.gui.mapper;

import org.springframework.stereotype.Repository;

import com.gui.entity.Salary;

@Repository
public interface QueryTestMapper {
	int insertDataToTable(Salary salarys);
}
