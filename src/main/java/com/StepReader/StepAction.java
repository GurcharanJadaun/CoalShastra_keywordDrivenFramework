package com.StepReader;

import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class StepAction {
	AppiumDriver<MobileElement> driver;
	public StepAction(AppiumDriver<MobileElement> driver){
		this.driver=driver;
	}
	StepDefinations act=new StepDefinations();
	public boolean performAction(String action,Object target) {
		boolean result=false;
		if (action.trim().equalsIgnoreCase("Launch the app")) {
			result=act.launchApp(driver);
			
		} else if (action.trim().equalsIgnoreCase("Validate Presence of fields")) {
			HashMap<String,Boolean> resultMap=new HashMap<String,Boolean>();
			HashMap<Integer,String> map= (HashMap<Integer,String>) target;
			result=true;
			resultMap=act.ValidatePresenceOfElements(driver, map);
			for(Map.Entry<String, Boolean> entry:resultMap.entrySet()) {
				
				//System.out.println(entry.getKey()+"-->"+entry.getValue());
				result=(result&&entry.getValue());
			}
		} else if (action.trim().equalsIgnoreCase("Tap on")) {
			HashMap<Integer,String> map= (HashMap<Integer,String>) target;
			String elementToBeClicked=map.get(1);
			result=act.TapOnElement(driver, elementToBeClicked);

		} else if (action.trim().contains("Enter data in text field")) {
			HashMap<Integer,String> map= (HashMap<Integer,String>) target;
			String textField=map.get(1);
			String data= action.split(":")[1].trim();
			result=act.enterDataInTextField(driver, textField, data);
			
		} else if (action.trim().equalsIgnoreCase("Validate Absence of fields")) {
			result=!performAction( "Validate Presence of fields", target);
		}
		
		return result;
	}
}
