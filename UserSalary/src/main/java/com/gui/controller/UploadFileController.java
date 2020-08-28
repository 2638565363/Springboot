package com.gui.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import com.gui.entity.Salary;
import com.gui.service.impl.QueryTestServiceImpl;
import com.gui.util.ExcelUtils;
 
@RestController
public class UploadFileController {
     @Autowired
     private QueryTestServiceImpl queryTestService;
	 @RequestMapping("/uploadFile")
	 public Map<String,Object> bathAddData(@RequestParam("file") MultipartFile file){  //@RequestParam将请求参数绑定到你控制器的方法参数上 ,  MultipartFile这个类一般是用来接受前台传过来的文件
		 InputStream is = null;  //定义is为输入流
		 Map<String,Object> map=new HashMap<String,Object>();  //HashMap基于哈希表的MAP接口实现   此实现提供所有可选的映射操作，并允许 空值和空键。定义map
		    try {
	            is = file.getInputStream();   //取到输入流到指定位置。
	            List<Object> entityList = ExcelUtils.importDataFromExcel(new Salary(),is,file.getOriginalFilename());  //得到一个List类型的返回值，即得到excel中的内容， file.getOriginalFilename()得到文件名
	            System.out.println(entityList.size()+"---------");
	            if (entityList.size() == 0){
	                map.put("status", "0");
	                map.put("msg", "导入数据不能为空");
	            }else if (entityList.size() >100){
	            	map.put("status", "1");
		            map.put("msg", "数据条数不准大于100条");
	            } else {  //导入数据可被接收时
	                Map<String,Object> Resmap= queryTestService.batchAddData(entityList);  //将数据导入到数据库中，返回map类型的值显示导入情况，并赋值给Resmap
	                //System.out.println(Resmap.get("success")+"---"+Resmap.get("data")); 
	                int totalNum = entityList.size();  //list是一个有序数列，值可重复
	                int failed = totalNum -Integer.parseInt(Resmap.get("success").toString());  //parse将字符串转化为有符号的整数
	                map.put("status", "2");
	                map.put("msg", "导入成功");
	                map.put("success",Resmap.get("success"));  //导入成功的个数
	                map.put("totalNum",totalNum);      //excel中的总行数
	                map.put("failed",failed);        //导入失败的个数
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }    finally {
	            is = null;
	        }
		    System.out.println(map.get("msg")+"--"+map.get("status"));  //输出导入数据的状态
		 return map;
	 }
}