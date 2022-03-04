package com.Armorcode.bdd.api.step_definitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Armorcode.bdd.base.TestContext;
import com.Armorcode.bdd.integrations.common_utils.RestAssuredUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

/*
 * All API Specifications, Authentications, Headers should be added here in step methods
 */

public class ApiBackgroundSteps {

	private TestContext apiTestContext;

	public ApiBackgroundSteps(TestContext apiTestContext) {
		this.apiTestContext = apiTestContext;
	}

	/*
	 * Method to set up request specification or add header input - Request Headers
	 * as data table
	 */
	@Given("^(?:I set up request specification|I add request headers|I add new headers)$")
	public void setUpSpecification(DataTable dataTable) throws Throwable {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, String> HeaderMaps = new HashMap<String, String>();
		for (int row = 0; row < table.size(); row++) {
			HeaderMaps.put(table.get(row).get("HeaderName"), table.get(row).get("HeaderValue"));
		}
		if (apiTestContext.getApiTestBase().specification == null) {
			apiTestContext.getApiTestBase().specification = RestAssuredUtils.getSpec().build();
		}
		apiTestContext.getApiTestBase().specification.headers(HeaderMaps);
	}

	/*
	 * Method to set up request specification or add header input - Request cookies
	 * as data table
	 */
	@And("^I set cookies$")
	public void setCookies(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, String> cookieMaps = new HashMap<String, String>();
		for (int row = 0; row < table.size(); row++) {
			cookieMaps.put(table.get(row).get("cookieName"), table.get(row).get("cookieValue"));
		}
		if (apiTestContext.getApiTestBase().specification == null) {
			apiTestContext.getApiTestBase().specification = RestAssuredUtils.getSpec().build();
		}
		apiTestContext.getApiTestBase().specification.cookies(cookieMaps);
	}

	@And("^I add basic authentication$")
	public void setBasicAuthentication(String username, String password) {
		if (apiTestContext.getApiTestBase().specification == null) {
			apiTestContext.getApiTestBase().specification = RestAssuredUtils.getSpec().build();
		}

		apiTestContext.getApiTestBase().specification.auth().preemptive().basic(username, password);
	}

	@And("^I add digest authentication$")
	public void setDigestAuthentication(String username, String password) {
		if (apiTestContext.getApiTestBase().specification == null) {
			apiTestContext.getApiTestBase().specification = RestAssuredUtils.getSpec().build();
		}
		apiTestContext.getApiTestBase().specification.auth().digest(username, password);

	}

	@And("^I add oauth2 authentication \"([^\"]*)\"$")
	public void setOauth2Authentication(String accessToken) {
		if (apiTestContext.getApiTestBase().specification == null) {
			apiTestContext.getApiTestBase().specification = RestAssuredUtils.getSpec().build();
		}
		apiTestContext.getApiTestBase().specification.auth().oauth2(accessToken);
	}

	
}
