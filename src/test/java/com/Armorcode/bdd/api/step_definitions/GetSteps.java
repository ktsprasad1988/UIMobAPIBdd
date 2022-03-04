package com.Armorcode.bdd.api.step_definitions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Armorcode.bdd.base.TestContext;
import com.Armorcode.bdd.ccl.WS_PayloadsHeaders;
import com.Armorcode.bdd.integrations.common_utils.ResourceURIS;
import com.Armorcode.bdd.integrations.common_utils.RestAssuredUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class GetSteps {

	private TestContext apiTestContext;

	public GetSteps(TestContext apiTestContext) {
		this.apiTestContext = apiTestContext;
	}

	// Gets the resource uri based on request name from DB and sends get request
	@When("^I send the GET request to \"([^\"]*)\" endpoint$")
	public void get(String fileName) {
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()));

	}
	/*
	 * Gets the resource uri based on request name from DB, gets path params from
	 * data table and sends get request
	 */

	@When("^I send the GET request to \"([^\"]*)\" endpoint with path parameters$")
	public void getWithPathPrams(String fileName, DataTable dataTable) {

		List<Map<String, String>> table = dataTable.asMaps();

		Map<String, Object> pathParams = new HashMap<String, Object>();
		for (int i = 0; i < table.size(); i++) {
			String value = table.get(i).get("Value");
			if (value == null)
				value = "";
			pathParams.put(table.get(i).get("Path"), value);
		}
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(apiTestContext.getApiTestBase().specification,
				pathParams, apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()));
	}

	@When("^I send the GET request to \"([^\"]*)\" endpoint with path parameters and without encoded url$")
	public void getWithPathPramswithoutencodedurl(String fileName, DataTable dataTable) {
		System.out.println(LocalDateTime.now());
		List<Map<String, String>> table = dataTable.asMaps();

		Map<String, Object> pathParams = new HashMap<String, Object>();
		for (int i = 0; i < table.size(); i++) {
			String value = table.get(i).get("Value");
			if (value == null)
				value = "";
			pathParams.put(table.get(i).get("Path"), value);
		}
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(
				apiTestContext.getApiTestBase().specification.urlEncodingEnabled(false), pathParams,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()));
		System.out.println(LocalDateTime.now());
	}

	/*
	 * Gets the resource uri based on request name from DB, gets query params from
	 * data table and sends get request
	 */

	@When("^I send the GET request to \"([^\"]*)\" endpoint with query parameters$")
	public void getWithQueryParams(String fileName, DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, Object> queryParams = new HashMap<String, Object>();

		for (int i = 0; i < table.size(); i++) {
			queryParams.put(table.get(i).get("QueryParamName"), table.get(i).get("QueryParamValue"));
		}
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()), queryParams);
	}

	/*
	 * Gets the resource uri based on request name from DB, gets path and query
	 * params from data table and sends get request
	 */

	@When("^I send the GET request to \"([^\"]*)\" endpoint with query and path parameters$")
	public void getWithQueryAndPathParams(String fileName, DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		Map<String, Object> pathParams = new HashMap<String, Object>();
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).get("QueryParamName") != null) {
				queryParams.put(table.get(i).get("QueryParamName"), table.get(i).get("QueryParamValue"));
			}
			if (table.get(i).get("Path") != null) {
				pathParams.put(table.get(i).get("Path"), table.get(i).get("Value"));
			}
		}
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()), queryParams,
				pathParams);

	}

	@When("^I send the GET request to \"([^\"]*)\" endpoint with query and path parameters and without encoded url$")
	public void getWithQueryAndPathParamswithoutencodedurl(String fileName, DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		Map<String, Object> pathParams = new HashMap<String, Object>();
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).get("QueryParamName") != null) {
				queryParams.put(table.get(i).get("QueryParamName"), table.get(i).get("QueryParamValue"));
			}
			if (table.get(i).get("Path") != null) {
				pathParams.put(table.get(i).get("Path"), table.get(i).get("Value"));
			}
		}
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(
				apiTestContext.getApiTestBase().specification.urlEncodingEnabled(false),
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()), queryParams,
				pathParams);

	}

	@And("hit the Get request to endpoint with query parameters")
	public void putWithQueryParams(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		for (int i = 0; i < table.size(); i++) {
			queryParams.put(table.get(i).get("QueryParamName"), table.get(i).get("QueryParamValue"));
		}
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()),
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name()), queryParams);
	}

	/*
	 * GET Method endpoint with query parameters without encoded url
	 */

	@When("^I send the GET request to \"([^\"]*)\" endpoint with query parameters without encoded url$")
	public void getWithQueryParamsWithoutEncodedUrl(String fileName, DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, Object> queryParams = new HashMap<String, Object>();

		for (int i = 0; i < table.size(); i++) {
			queryParams.put(table.get(i).get("QueryParamName"), table.get(i).get("QueryParamValue"));
		}
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(
				apiTestContext.getApiTestBase().specification.urlEncodingEnabled(false),
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()), queryParams);
	}

	/*
	 * Gets the resource uri and payload based on request name from DB and sends get
	 * request
	 */
	@When("^I send the GET request to \"([^\"]*)\" endpoint with urlEncoded payload$")
	public void getWithurlEncodedRequestBody(String fileName, DataTable dataTable) {
		List<String> table = dataTable.asList();
		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
					ResourceURIS.getResourceURI(fileName));
		}

		apiTestContext.getApiTestBase().response = RestAssuredUtils.get(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()), table.get(0));

	}

}
