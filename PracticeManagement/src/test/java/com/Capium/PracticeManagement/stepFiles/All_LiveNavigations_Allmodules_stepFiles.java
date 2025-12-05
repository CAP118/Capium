package com.Capium.PracticeManagement.stepFiles;

import com.Capium.PracticeManagement.Actions.Login_Actions;
import com.Capium.Utilities.ConfigReader;
import com.Capium.Utilities.HelperClass;

import io.cucumber.java.en.*;

public class All_LiveNavigations_Allmodules_stepFiles {
	
	
	
	Login_Actions login=new Login_Actions();

	@When("Login into Live Application {string}")
	public void login_into_live_application(String url) {
		url = ConfigReader.getProperty("Live.url");
		HelperClass.openPage(url);
		login.clickOnAccountant();
	}

	@Then("Navigate Home to {string} Module")
	public void navigate_home_to_module(String string) {
	    login.navigateToModule(string);
	}

	@Then("Navigate {string} to Home")
	public void navigate_to_home(String string) {
	    login.navigateToHome(HelperClass.getDriver());
	    System.out.println(string+":Navigated to Home");
	}

//	@Then("Navigate Home to {string} Module")
//	public void navigate_home_to_module(String string) {
//	    
//	}
}
