package com.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.Utility.TestCases;
import com.Utility.TestStep;

public class ReadExcelFile  {
	public HashMap<String, String> getXpathMap(String filePath) {
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook repository = new HSSFWorkbook(fis);
			HSSFSheet workSheet = repository.getSheet("Sheet1");
			for (int i = 0; i <= workSheet.getLastRowNum(); i++) {
				Row row = workSheet.getRow(i);
				String key = row.getCell(0).getStringCellValue().trim();
				String value = (String) row.getCell(1).getStringCellValue().trim();
				map.put(key, value);
			}

		} catch (Exception ex) {
			System.out.println("<<Exception>>" + ex.getMessage());
		}
		return map;

	}
	public List<TestCases> getTestCases(){
		List<TestCases> testSet=new ArrayList<TestCases>();
		
		int tmpId=0,rowCount=1;
		try {
			File file = new File("src/main/resources/Steps.xls");
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook repository = new HSSFWorkbook(fis);
			HSSFSheet workSheet = repository.getSheet("TestSteps");
			while (rowCount<=workSheet.getLastRowNum()) {
				
				Row row = workSheet.getRow(rowCount);
				 tmpId = (int) row.getCell(0).getNumericCellValue();
				 String testCaseName = (String) row.getCell(1).getStringCellValue().trim();
				 TestCases testCases= new TestCases();
					
				 testCases.setTestCaseId(tmpId);
				 testCases.setTestCaseName(testCaseName);
				 
				 do {
					 TestStep step=new TestStep();
					 int stepNumber= (int) row.getCell(2).getNumericCellValue();
					 String action=(String) row.getCell(3).getStringCellValue().trim();
					 String fields=(String) row.getCell(4).getStringCellValue().trim();
				step.setAction(action);
				step.setStepNumber(stepNumber);
				step.setFieldNames(fields);
				testCases.setSteps(step);			
				rowCount++;
				if(rowCount>workSheet.getLastRowNum()) {
					break;
				}
				else {
					row = workSheet.getRow(rowCount);
				}
				 }while( tmpId==(int) row.getCell(0).getNumericCellValue());
				// System.out.println("Test Case Added "+testSet.size());
				 testSet.add(testCases);				
			}
			System.out.println("Test Cases Added "+testSet.size());
		} catch (Exception ex) {
			System.out.println("<<Exception>>" + ex.getMessage());
		}
		return testSet;
	}
}
