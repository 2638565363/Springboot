package com.gui.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.entity.Salary;
import com.gui.mapper.QueryTestMapper;
import com.gui.service.QueryTestService;

/*/
 * 向数据库中插入数据，
 * 返回一个map类型的数据，map中存入的是成功导入的数值，和未导入成功的信息
 */
@Service
public class QueryTestServiceImpl implements QueryTestService{
	
	@Autowired
	private QueryTestMapper queryTestMapper;
	
	@Override
	public Map<String, Object> batchAddData(List<Object> entityList) {   //传入list类型的数据
		int success=0;
        List<Salary> lis = new ArrayList<Salary>();  //lis是Salary的list类
        Map<String,Object> map=new HashMap<String,Object>();  //定义map
		for (Object object : entityList) {
			Salary salary=(Salary) object;
			int i=queryTestMapper.insertDataToTable(salary);  //向数据库插入数据，返回整数i
			if(i>0) {//插入数据库成功 
				success++;
			}else {
				//失败
				lis.add(salary);
			}
		}
		map.put("success",success);  //将字符串和对应的值传入map对应的key和value中。
		map.put("data",lis);
		return map;
	}
}
