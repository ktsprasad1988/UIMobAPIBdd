package com.Armorcode.bdd.common.runner_files;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Armorcode.bdd.integrations.NG_listners.SuiteEvent;
import com.Armorcode.bdd.integrations.NG_listners.WebEvent;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@Listeners({ SuiteEvent.class, WebEvent.class })

@CucumberOptions(features = { "src/test/java/com/Armorcode/bdd/web/features" }, glue = {
		"com/Armorcode/bdd/web/step_definitions" }, tags = { "@Smoke" }, plugin = { "pretty",
				"html:target/site/cucumber-pretty", "json:target/cucumber/cucumber.json",
				"rerun:target/rerun.txt" }, monochrome = true, strict = true, dryRun = false)

//@flipkart , @md , @dd

// @AccountDetails, @AddressPage, @Algolia, @CartPage, @Login, @ApplyCoupon, @Communication, @CustomCheckout, @GiftCards, @LocationBar, @NebulaBackend
// @Logout, @OrderPage, @ProductDetailPage, @SignUp, @PurchaseHistory, @ReturnsPage, @SplitOrderEtoE, @SplitOrder, @Voucher
public class Web_Runner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		System.out.println("@Before class");
		// ReportManager.startTestAPI("Test", "Smoke");
	}

	@Test(groups = "cucumber", description = "Run Cucumber Scenario", dataProvider = "scenarios")
	public void scenario(PickleEventWrapper pickleEventWrapper, CucumberFeatureWrapper cucumberFeatureWrapper)
			throws Throwable {
		testNGCucumberRunner.runScenario(pickleEventWrapper.getPickleEvent());
	}

	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		System.out.println("@Data provider");
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		System.out.println("@Afterclass");
		testNGCucumberRunner.finish();
	}
}
