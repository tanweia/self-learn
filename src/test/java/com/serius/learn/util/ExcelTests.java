package com.serius.learn.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.serius.learn.utils.ExcelUtil;

public class ExcelTests {
	
	@Test
	public void mergeTest() throws InvalidFormatException, IOException {
        InputStream input1 = new FileInputStream("E:/TG/test/report/temp.xlsx");
//		InputStream input1 = getClass().getResourceAsStream("E:/TG/test/report/main.xlsx");        
//		Workbook workbook1 = WorkbookFactory.create(input1);
		/*XSSFWorkbook workbook1 = new XSSFWorkbook(input1);
		InputStream input2 = new FileInputStream("E:\\TG\\test\\report\\resource.xlsx");        
		XSSFWorkbook workbook2 = new XSSFWorkbook(input2);
		InputStream input3 = new FileInputStream("E:\\TG\\test\\report\\base.xlsx");        
		XSSFWorkbook workbook3 = new XSSFWorkbook(input3);
		XSSFWorkbook[] array = new XSSFWorkbook[]{workbook1, workbook2, workbook3};
		System.out.print("开始合并");
		ExcelUtil.mergeHSSFWorkbooks(array);*/
        Workbook workbook = WorkbookFactory.create(input1);
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(lastRowNum);
       /* sheet.shiftRows(3, lastRowNum, -3);
        FileOutputStream os = new FileOutputStream("E:/TG/test/report/test_1.xlsx");  
        workbook.write(os);  
        os.close();*/
	}
	
	
}
