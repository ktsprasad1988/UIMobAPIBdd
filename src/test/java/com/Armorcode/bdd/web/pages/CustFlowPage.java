package com.Armorcode.bdd.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.Armorcode.bdd.ccl.WebActions;
import com.Armorcode.bdd.integrations.common_utils.DriverFactory;

import junit.framework.Assert;

public class CustFlowPage {

	WebActions webActions = new WebActions();
	String firstScan;
	By gmail_btn = By.xpath("(//a[@class='ant-btn ant-btn-lg ant-btn-block'])[1]");
	By profile =By.xpath("//div[text()='krishna.chaitanya@armorcode.io']");
	By email_box = By.xpath("//input[@id='identifierId']");
	By password_box = By.xpath("//input[@type='password']");
	By email_next = By.xpath("(//button[@type='button'])[4]");
	By pass_next = By.xpath("(//button[@type='button'])[2]");
	By otp_box = By.xpath("//input[@id='idvPin']");
	By otp_next = By.xpath("(//button[@type='button'])[1]");
	By securityTools= By.xpath("//a[text()='Security Tools']");
	By securitySearch= By.xpath("//input[@placeholder= 'Search']");
	By netsparker= By.xpath("//div[text()= 'Netsparker']");
	By addWebsite= By.xpath("//span[text()= 'Add Webiste']");
	By websiteID= By.xpath("//div[@name='websiteId']");
	By productChoice= By.xpath("//input[@id='configurations_0_product']");
	By subProductChoice= By.xpath("//input[@id='configurations_0_subProduct']");
	By environmentChoice= By.xpath("//input[@id='configurations_0_environment']");
	By securityToolSave= By.xpath("//span[text()= 'Save']");
	By scanButton= By.xpath("//a[text()='Scans']");
	By scanProgress= By.xpath("//div[@class='progressContainer']");
	By progressimage= By.xpath("(//img[@alt= \"Scan Status\"])[1]");
	By totalfindings= By.xpath("//span[@class='ant-typography finding-text']");
	//product
		By product_btn = By.xpath("(//li[@class='menu-v2-item'])[7]");
		By new_productbtn = By.xpath("//button[@class='ant-btn ant-btn-primary ant-btn-block']"); //common for prod and subprod
		By name_prdt = By.xpath("//input[@id='name']"); //common for prod and subprod
//		By dropdown = By.xpath("//span[@class='ant-select-selection-item']");
		By desc_box = By.xpath("//textarea[@id='description']"); //common for prod and subprod
		By Next_btn = By.xpath("//button[@class='ant-btn ant-btn-primary pull-right']");
		By Submit_btn = By.xpath("//button[@class='ant-btn ant-btn-primary pull-right ']");
		
		
		//subproduct
		By subproduct_btn =By.xpath("//li[@class='menu-v2-item selected']");
		By prod_dropdown = By.xpath("//input[@id='product']");
		
		
		//product
		public void Productclick() throws InterruptedException {
			webActions.Click(product_btn, "Product button!");
		}

		public void Newproductclick() throws InterruptedException {
			webActions.Click(new_productbtn, "New Product button");
		}

		public void Name_Desc(String name, String desc) throws InterruptedException {
			webActions.sendKeys(name_prdt, name);
			webActions.sendKeys(desc_box, desc);
		}

		public void Next() throws InterruptedException {
			webActions.Click(Next_btn, "Next Button");
		}

		public void Submit() throws InterruptedException {
			webActions.Click(Submit_btn, "Submit Button");
		}

		//subproduct
		public void Subproductclick() throws InterruptedException {
			webActions.Click(subproduct_btn, "Sub Product Button");
		}

		public void Newsubproductclick() throws InterruptedException {
			webActions.Click(new_productbtn, "New Sub Product button");
			
		}
		
		public void Parent_Desc_Name(String product, String name, String desc)throws InterruptedException {
			
			webActions.sendKeys(prod_dropdown, product);
			DriverFactory.getInstance().getWebDriver().findElement(websiteID).sendKeys(Keys.ENTER);
			webActions.sendKeys(name_prdt, name);
			webActions.sendKeys(desc_box, desc);
		}
	
	
	public void securityTool(String webSite, String product, String subProduct, String environment) {
		webActions.sendKeys(websiteID, webSite);
		DriverFactory.getInstance().getWebDriver().findElement(websiteID).sendKeys(Keys.ENTER);
		webActions.sendKeys(productChoice, product);
		DriverFactory.getInstance().getWebDriver().findElement(websiteID).sendKeys(Keys.ENTER);
		webActions.sendKeys(subProductChoice, subProduct);
		DriverFactory.getInstance().getWebDriver().findElement(websiteID).sendKeys(Keys.ENTER);
		webActions.sendKeys(environmentChoice, environment);
		DriverFactory.getInstance().getWebDriver().findElement(websiteID).sendKeys(Keys.ENTER);
	}
	public void securityClick() {
		webActions.Click(securityTools, "Security Tools");
		webActions.waitForVisible(securitySearch);
	}
	public void toolChoice() {
		webActions.sendKeys(securitySearch, "netsparker");
		webActions.waitForVisible(netsparker);
		webActions.Click(netsparker, "NetSparker Tool");
		webActions.waitForVisible(addWebsite);
	}
	public void addingWebsite() {
		webActions.Click(addWebsite, "Add Website");
		webActions.waitForVisible(websiteID);
	}
	public void save() throws InterruptedException {
		webActions.Click(securityToolSave, "Save");
		Thread.sleep(5000);
	}
	public void scanResult() {
		webActions.Click(scanButton, "Scan Result");
		webActions.waitForVisible(scanProgress);
	}
	public void resultCheck() {
		webActions.waitForVisible(progressimage);
		firstScan= webActions.getElmText(totalfindings);
	}
	public void validation() throws InterruptedException {
		webActions.verifyText(firstScan, "44");
		
	}
	// Login
	public void Browser() throws InterruptedException {
	webActions.openURL("https://www.google.co.in/");
	}



	public void Website() throws InterruptedException {
	webActions.openURL("https://preprod.armorcode.ai/#/login?/");
	}



	public void Gmail_click() throws InterruptedException {
	webActions.Click(gmail_btn, "Gmail Button");

	}



	public void Enter_email() throws InterruptedException {
	webActions.Click(profile, "Saved Profile");
	}

	/*
	 * public void Enter_password() throws InterruptedException {
	 * webActions.sendKeys(password_box, "bhagath84"); webActions.Click(pass_next,
	 * "Next button"); Thread.sleep(15000); webActions.sendKeysOTP(otp_box);
	 * webActions.Click(otp_next, "Next Button"); }
	 */
	public void homePagevalidation() {
		if(DriverFactory.getInstance().getWebDriver().findElement(securityTools).isDisplayed()) {
			System.out.println("We have entered the homepage successfully");
		}
		else 
			System.out.println("Login function failure");
	}
	
	
	
}
