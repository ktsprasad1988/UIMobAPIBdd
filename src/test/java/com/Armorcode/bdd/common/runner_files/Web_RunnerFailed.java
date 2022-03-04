package com.Armorcode.bdd.common.runner_files;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Armorcode.bdd.integrations.NG_listners.SuiteEvent;
import com.Armorcode.bdd.integrations.NG_listners.WebEvent;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@Listeners({ SuiteEvent.class, WebEvent.class })
//

@CucumberOptions(features = { "@target/rerun.txt" }, glue = { "com/dc/bdd/web/step_definitions",
"com/dc/bdd/common/hooks" }, tags = {"@Execution" }, plugin = {"pretty", "html:target/site/cucumber-pretty",
                                            "json:target/cucumber/cucumber.json" }, monochrome = true, strict = true, dryRun = false)

public class Web_RunnerFailed {

           private TestNGCucumberRunner testNGCucumberRunner;

           @BeforeClass(alwaysRun = true)
           public void setUpClass() throws Exception {
                      testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
                      System.out.println("@Before class web runner");
           }

           @Parameters({ "plartform", "udid", "systemPort", "deviceName" })
           @Test(groups = "cucumber", description = "Run Cucumber Scenario", dataProvider = "scenarios")
           public void scenario(PickleEventWrapper pickleEventWrapper, CucumberFeatureWrapper cucumberFeatureWrapper)
                                 throws Throwable {
                      testNGCucumberRunner.runScenario(pickleEventWrapper.getPickleEvent());
                      System.out.println("@test test web runner");
           }

           @DataProvider(parallel = false)
           public Object[][] scenarios() {
                      System.out.println("@Data provider web runner");
                      return testNGCucumberRunner.provideScenarios();
           }

           @AfterClass(alwaysRun = true)
           public void tearDownClass() {
                      System.out.println("@Afterclass web runner");
                      testNGCucumberRunner.finish();
           }
}
