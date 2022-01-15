package com.efraimpc.steps;

import com.efraimpc.testbase.BaseClass;
import com.efraimpc.utils.CommonSeleniumMethods;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	
	@Before
	public void startScenario() {
		BaseClass.setUp();
	}
	
	@After
	public void endScenario(Scenario scenario) {
		byte[] picture;
		if(scenario.isFailed()) {
			
			//take a screenshot and save it in /failed folder
			picture = CommonSeleniumMethods.takeScreenshot("failed/" + scenario.getName());
			
		}else {
			//take a screenshot and save it in /passed folder
			picture = CommonSeleniumMethods.takeScreenshot("passed/" + scenario.getName());
		}
		
		scenario.attach(picture, "image/jpg", scenario.getName());
		
		BaseClass.tearDown();
	}
}
