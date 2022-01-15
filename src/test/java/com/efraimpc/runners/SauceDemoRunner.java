package com.efraimpc.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
			//specify which features files to be run	
			features = "src/test/resources/features/",
			
			//shows where to find the step implementations from the feature file steps
			glue = "com.efraimpc.steps",
			
			//if true, it does not run steps only checks if they are defined.
			dryRun= false,
			
			//tags to be run (similar to testNG groups)
			tags = "@Login",
			
			monochrome = true,
			plugin = {
					"pretty", //prints gherkin steps on console
					"html:target/cucumber-default-report", //creates a basic html report in target folder
					"json:target/cucumber.json",
					"rerun:target/failed.txt"
			}
		)
public class SauceDemoRunner {

}
