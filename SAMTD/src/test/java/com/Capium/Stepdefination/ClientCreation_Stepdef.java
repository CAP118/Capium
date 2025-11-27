package com.Capium.Stepdefination;

import com.Capium.Actions.HMRC_Authorization_Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ClientCreation_Stepdef {
	
	HMRC_Authorization_Actions HMRC=new HMRC_Authorization_Actions();
	
	@Given("click on Manage tab")
	public void click_on_manage_tab() {
		HMRC.ClickOnManageTab();
	}

	@When("click on Add client button")
	public void click_on_add_client_button() {
	    HMRC.ClickOnAddClinet();
	}

	@When("Enter mandatory fields")
	public void enter_mandatory_fields() {
	   HMRC.EnterAllMandatoryFields();
	   HMRC.ClickOnSaveAndContinue();
	   HMRC.ClickonHMRC_Authorization();
	   HMRC.ClickOnContinue();
	   HMRC.ClickOnSignINtoHMRC();
	   HMRC.ClickOnDonthaveTestCrendentials();
	   HMRC.SelectIndividualType();
	   HMRC.ClickOnSaveAndContinue();
	}

}
