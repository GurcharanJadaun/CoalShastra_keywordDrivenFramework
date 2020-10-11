package com.TestRunner;

import java.util.ArrayList;
import java.util.List;

import com.ExcelReader.ReadExcelFile;
import com.StepReader.StepAction;
import com.Utility.TestCases;
import com.Utility.TestStep;
import deviceSetup.SetupDeviceCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;;

public class ExecuteTestSet extends ReadExcelFile {
	
	AppiumDriver<MobileElement> driver;
	public void setupTestEnvironment() {
		SetupDeviceCapabilities device= new SetupDeviceCapabilities();
		driver=device.setupDevice(driver, "Samsung");
	}

	public void createTestSuite() {
		StepAction act=new StepAction(driver);
		List<TestCases> testSuite=this.getTestCases();
		TestCases testCase=new TestCases();
		List<TestStep> testStep=new ArrayList<TestStep>();
		for(int i=0;i<testSuite.size();i++) {
			testCase=testSuite.get(i);
			System.out.println(testCase.getTestCaseId());
			System.out.println(testCase.getTestCaseName());
			testStep=testCase.getSteps();
			for(int j=0;j<testStep.size();j++) {
				Object target=(Object) testStep.get(j).getFields();
				System.out.println(testStep.get(j).getAction()+"-->"+act.performAction(testStep.get(j).getAction(), target));
				
			}
		}
	}
	public static void main(String arg[]) {
		ExecuteTestSet set=new ExecuteTestSet();
		set.setupTestEnvironment();
		set.createTestSuite();
		
	}
}
