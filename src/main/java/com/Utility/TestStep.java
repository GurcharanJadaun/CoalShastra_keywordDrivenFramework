package com.Utility;

import java.util.HashMap;

public class TestStep {
	int stepNumber;
	String action;
	HashMap<Integer, String> fieldList = new HashMap<Integer, String>();
	public void setStepNumber(int stepNumber) {
		this.stepNumber=stepNumber;
	}
	public void setAction(String action) {
		this.action=action;
	}
	public void setFieldNames(String fields) {
		String[] list=fields.split(",");
		for(int index=0;index<list.length;index++) {
			fieldList.put(index+1, list[index]);
		}
	}
	public String getAction() {
		//System.out.print("Test Step"+stepNumber+" : ");
		return action;
	}
	public HashMap<Integer, String> getFields(){
		return fieldList;
	}
}
