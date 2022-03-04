package com.Armorcode.bdd.web.step_definitions;

import com.Armorcode.bdd.ccl.WebActions;
import com.Armorcode.bdd.web.pages.CustFlowPage;

import cucumber.api.PendingException;
import io.cucumber.java.en.*;

public class CustFlow {

	CustFlowPage flow;

	// Login
	@Given("^User opens the required broswer$")
	public void user_opens_the_required_broswer() throws Throwable {
		flow = new CustFlowPage();
		flow.Browser();
	}

	@Then("^User redirects to the website$")
	public void user_redirects_to_the_website() throws Throwable {
		flow.Website();
	}

	@And("^User clicks on Sign in using Google Account$")
	public void user_clicks_on_sign_in_using_google_account() throws Throwable {
		flow.Gmail_click();
	}

	@Then("^User enters the valid emailID$")
	public void user_enters_the_valid_emailid() throws Throwable {
		flow.Enter_email();
	}

	@And("^User enters the valid password$")
	public void user_enters_the_valid_password() throws Throwable {
	//	flow.Enter_password();
	}

	@And("^User enters the Home page$")
	public void user_enters_the_home_page() throws Throwable {

	}

	@When("^User clicks on Security Tools from the menu$")
	public void user_clicks_on_security_tools_from_the_menu() throws Throwable {
		flow.securityClick();
		throw new PendingException();
	}

	@Then("^User clicks on Save button$")
	public void user_clicks_on_save_button() throws Throwable {
		flow.save();
		throw new PendingException();
	}

	@And("^User chooses Netsparker from different security tools$")
	public void user_chooses_netsparker_from_different_security_tools() throws Throwable {
		flow.toolChoice();
		throw new PendingException();
	}

	@And("^User adds his website by clicking Add Website$")
	public void user_adds_his_website_by_clicking_add_website() throws Throwable {
		flow.addingWebsite();
		throw new PendingException();
	}

	@And("^User chooses Website from the dropdown as \"([^\"]*)\" and chooses the product as \"([^\"]*)\" and chooses SubProduct as \"([^\"]*)\" and  chooses environment as \"([^\"]*)\"$")
	public void user_chooses_website_from_the_dropdown_as_something_and_chooses_the_product_as_something_and_chooses_subproduct_as_something_and_chooses_environment_as_something(
			String strArg1, String strArg2, String strArg3, String strArg4) throws Throwable {
		flow.securityTool(strArg1, strArg2, strArg3, strArg4);
		throw new PendingException();
	}

	@And("^User clicks on Scans button from the menu bar$")
	public void user_clicks_on_scans_button_from_the_menu_bar() throws Throwable {
		flow.scanResult();
		throw new PendingException();
	}

	@And("^User checks the result from the total findings$")
	public void user_checks_the_result_from_the_total_findings() throws Throwable {
		flow.resultCheck();
		throw new PendingException();
	}

	@And("^User verifies the scan results after every fifteen minutes$")
	public void user_verifies_the_scan_results_after_every_fifteen_minutes() throws Throwable {
		flow.validation();
		throw new PendingException();
	}

	// product
	@When("^User clicks on Products from the menu$")
	public void user_clicks_on_products_from_the_menu() throws Throwable {
		flow.Productclick();
	}

	@Then("^User clicks in New Product$")
	public void user_clicks_in_new_product() throws Throwable {
		flow.Newproductclick();
	}

	@And("^User enters data Name as \"([^\"]*)\" and description as \"([^\"]*)\"$")
	public void user_enters_data_name_as_something_and_description_as_something(String strArg1, String strArg2)
			throws Throwable {
		flow.Name_Desc(strArg1, strArg2);
	}

	// common methods for product and sub product
	@Then("^User click on Submit button$")
	public void user_click_on_submit_button() throws Throwable {
		flow.Submit();
	}

	@And("^User click on Next button$")
	public void user_click_on_next_button() throws Throwable {
		flow.Next();
	}

	// subproduct
	@When("^User clicks on SubProducts from the menu$")
	public void user_clicks_on_subproducts_from_the_menu() throws Throwable {
		flow.Subproductclick();
	}

	@Then("^User clicks in New SubProduct$")
	public void user_clicks_in_new_subproduct() throws Throwable {
		flow.Newsubproductclick();
	}

	@And("^User chooses Parent Product as \"([^\"]*)\" and enters SubProduct as \"([^\"]*)\" and description as \"([^\"]*)\"$")
	public void user_chooses_parent_product_as_something_and_enters_subproduct_as_something_and_description_as_something(
			String strArg1, String strArg2, String strArg3) throws Throwable {
		flow.Parent_Desc_Name(strArg1, strArg2, strArg3);
	}
}
