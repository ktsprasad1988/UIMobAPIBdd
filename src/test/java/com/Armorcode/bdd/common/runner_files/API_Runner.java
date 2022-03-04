package com.Armorcode.bdd.common.runner_files;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Armorcode.bdd.integrations.NG_listners.APIEvent;
import com.Armorcode.bdd.integrations.NG_listners.SuiteEvent;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@Listeners({ SuiteEvent.class, APIEvent.class })
@CucumberOptions(features = { "src/test/java/com/Armorcode/bdd/api/features/ScanConfiguration.feature" }, glue = {
		"com/Armorcode/bdd/api/step_definitions", "com/Armorcode/bdd/common/hooks" }, plugin = { "pretty", "json:target/cucumber.json",
				"rerun:target/rerun.txt" },monochrome = true, strict = true, dryRun = false)
public class API_Runner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		System.out.println("@Before class");
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