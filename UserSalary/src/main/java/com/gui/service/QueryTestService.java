package com.gui.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface QueryTestService {
	Map<String,Object>  batchAddData(List<Object> entityList);

}
