package com.Utility;

import java.util.ArrayList;
import java.util.List;

public class TestCases  {
	int testCaseId;
	String testCaseName;
	List<TestStep> steps = new ArrayList<TestStep>();

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public void setSteps(TestStep step) {
		steps.add(step);
	}
	//----------------------------------------------------------//
	public int getTestCaseId() {
		System.out.print("testCaseId: ");
		return testCaseId;
	}

	public String getTestCaseName() {
		System.out.print("testCaseName: ");
		return testCaseName;
	}

	public List<TestStep> getSteps() {
		return steps;
	}
}
