package com.gui.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    static final short borderpx = 1;


    /*****************文件输入相关************************/
    /**
     * @Title: createWorkbook
     * @Description: 判断excel文件后缀名，生成不同的workbook
     * @param @param is
     * @param @param excelFileName
     * @param @return
     * @param @throws IOException
     * @return Workbook
     * @throws
     */
    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException{
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);  //HSSFWorkbook表示以xls为后缀名的文件
        }else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);  //XSSFWorkbook表示以xlsx为后缀名的文件
        }
        return null;
    }

    /**
     * @Title: importDataFromExcel
     * @Description: 将sheet中的数据保存到list中，
     * 1、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
     * 2、在action调用此方法时，需声明
     *     private File excelFile;上传的文件
     *     private String excelFileName;原始文件的文件名
     * 3、页面的file控件name需对应File的文件名
     * @param @param vo javaBean
     * @param @param is 输入流
     * @param @param excelFileName
     * @param @return
     * @return List<Object>
     * @throws
     */
    public static List<Object> importDataFromExcel(Object vo,InputStream is,String excelFileName){ //vo是Salary类， is是输入流的数据， excelFileName是excel的文件名
        List<Object> list = new ArrayList<Object>();
        try {
            //创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);  //调用creatWorkbook方法，判断文件名以何种方式结尾，创建对应的工作簿
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);   //调用getSheet方法
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getClass().getDeclaredFields();  //新建反射，得到类中的所有数据
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                vo = vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});  //vo得到类中的构造器， newInstance通过类加载的方式创建对象
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);    //创建单元格
                    if (null == cell) {  //如果单元格中内容为空
                        cell = row.createCell(index);  //创建空的单元格
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);  //设置单元格类型为String
                    String value = (null == cell.getStringCellValue()? "" :cell.getStringCellValue());  //单元格内容为空，赋值为空，内容不为空，则将单元格内容赋值给value

                    Field field = fields[index];  //Field是一个类。在Java反射中Field类描述的是类的属性信息，fields是vo类中的所有变量名
                    String fieldName = field.getName();  //获取属性的名字
                    String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);  //fieldName.substring(0,1)将首字母转换成大写， substring(1)获得对象的相应方法
                    Method setMethod = vo.getClass().getMethod(methodName, new Class[]{String.class});  // new Class[]{String.class}将类中变量以数组类型初始化
                    setMethod.invoke(vo, new Object[]{value});  //将Object[]{value}通过反射储存到vo中。
                    index++;
                }
                if (isHasValues(vo)) {//判断对象属性是否有值，调用isHasValue方法
                    list.add(vo);  //向集合中添加对象
                    vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象  ，根据参数列表，getConstructor获得公有构造函数
                    //newInstance(new Object[]{})通过构造函数的newInstance()实例化对象
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //如果读取错误则返回空List
            list = new ArrayList<>();

            //logger.error(e);
        }finally{
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                //logger.error(e2);
            }
        }
        return list;

    }

    /**
     * @Title: getSheet
     * @Description: 根据sheet索引号获取对应的sheet
     * @param @param workbook
     * @param @param sheetIndex
     * @param @return
     * @return Sheet
     * @throws
     */
    public static Sheet getSheet(Workbook workbook,int sheetIndex){
        return workbook.getSheetAt(0);  //获得excel的第一个sheet页
    }

    /**
     * @Title: isHasValues
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     * @param @param object
     * @param @return
     * @return boolean
     * @throws
     */
    
    public static boolean isHasValues(Object object){
        Field[] fields = object.getClass().getDeclaredFields();  //反射的字段是一个静态字段或实例字段，.getclass()表示object的运行时类, 
        //getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced
        boolean flag = false;	
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);  //subString(beginIndex, endIndex):返回字符串的子字符串， toUpperCase()返回大写值， 
            Method getMethod;  //Method在程序运行过程中，动态地获得数据
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);  //invoke是一种反射机制， 调用类中方法，并且参数化
                if (null != obj && !"".equals(obj)) {  //当参数和构造方法都不为空时
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                //logger.error(e);
            }
        }
        return flag;
    }
}