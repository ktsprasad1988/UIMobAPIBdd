package com.Armorcode.bdd.api.step_definitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static net.javacrumbs.jsonunit.jsonpath.JsonPathAdapter.inPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.testng.Assert;

import com.Armorcode.bdd.base.TestContext;
import com.Armorcode.bdd.integrations.common_utils.JsonUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;
import net.javacrumbs.jsonunit.core.Option;

/*
 * All request validation steps should be performed in this class
 */
public class APIResponseValidationSteps {

	private TestContext apiTestContext;

	public APIResponseValidationSteps(TestContext apiTestContext) {
		this.apiTestContext = apiTestContext;
	}

	@Then("I validate the response scheama is as expected")
	public void validateResponseScheama() {
		apiTestContext.getApiTestBase().response.then().assertThat()
				.body(matchesJsonSchema(apiTestContext.getApiTestBase().requestData.get(0).get("responseSchema")));
	}

	// Validate the respose code
	@Then("I should see the response code as {string}")
	public void i_should_see_the_response_code_as(String ExpectedStatusCode) throws Throwable {
		/*
		 * int ExpectedStatusCode =
		 * ResponseCodes.valueOf(Responsecode).getResponseCode();
		 */
		  int actualResponseCode= apiTestContext.getApiTestBase().response.statusCode();
		  System.out.println(actualResponseCode);
		  System.out.println(ExpectedStatusCode);
		  System.out.println(apiTestContext.getApiTestBase().response.asString());
		  
		Assert.assertEquals(apiTestContext.getApiTestBase().response.statusCode(), Integer.parseInt(ExpectedStatusCode),
				"Failed in status code validation");
	}

	// Validate the response content type
	@And("contentType as {string}")
	public void contenttype_as(String ExpectedContentType) throws Throwable {
		String ActualContentType = apiTestContext.getApiTestBase().response.contentType();
		if (ActualContentType.equals("text/html; charset=utf-8")) {
			ActualContentType = "text/html; charset=UTF-8";
		}
		Assert.assertEquals(ActualContentType, ExpectedContentType, "Actual ContentType " + ActualContentType
				+ " is not macthing with expected ContentType " + ExpectedContentType);
	}

	/*
	 * Validate the response fields matches the expected value from Cucumber data
	 * table based on JPath Ignores order of the JSON
	 */
	@And("the fields in response should match with expected values")
	public void veriFyApiResponseAtPath(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();

		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isEqualTo(table.get(row).get("Value"));
		}

	}

	/*
	 * Validate the response fields matches the expected value from Cucumber data
	 * table based on JPath Ignores order of the JSON
	 */

	@And("validating the substring in response")
	public void verifyApiResponse(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		for (int row = 0; row < table.size(); row++) {
			apiTestContext.getApiTestBase().response.then().body(table.get(row).get("Path"),
					Matchers.containsString(table.get(row).get("Value")));
		}

	}

	/*
	 * Validate the response fields doesn't matches with the unexpected value from
	 * Cucumber data table based on JPath Ignores order of the JSON
	 */
	@And("the fields in response should not match with unexpected values")
	public void verifyNotEqual(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isNotEqualTo(table.get(row).get("Value"));
		}
	}

	// verifies the response field values are not null based on expected JPath
	@And("^verifies the field is not null$")
	public void verifyNodeIsNotNull(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isNotNull();

		}
	}

	// verifies the response field values are null based on expected JPath
	@And("^verifies the field is null|verify the fields are null$")
	public void verifyNodeIsNull(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isNull();
		}
	}

	// verifies the field is not present in the response based on expected JPath
	@And("^verifies the field is not present in the response|verify the field is not present in the response$")
	public void verifyNodeIsNotPresent(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isAbsent();
		}
	}

	// verifies the field is present in the response based on expected JPath
	@And("^verifies the field is present in the response$")
	public void verifyNodeIsPresent(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isPresent();
		}
	}

	// verifies the list is not empty in the response based on expected JPath
	@And("^verifies the list is not empty$")
	public void verifyListIsNotEmpty(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isArray().isNotEmpty();

		}
	}

	// verifies the list is empty in the response based on expected JPath
	@And("^verifies the list is empty$")
	public void verifyListIsEmpty(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isArray().isEmpty();

		}
	}

	// verifies the list contains the values
	@And("^(?:verifies the list contains the values|verify the list contains the value)$")
	public void verifyListContainsValue(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();
		for (int row = 0; row < table.size(); row++) {
			List<Object> values = Arrays.asList(table.get(row).get("Value").split(";", -1));
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isArray().contains(values);

		}
	}

	// verifies the list doesn't contain the values
	@And("^(?:verifies the list does not contain the values|verifies the list does not contain the value)$")
	public void verifyListDoesNotContainsValue(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String response = apiTestContext.getApiTestBase().response.asString();

		for (int row = 0; row < table.size(); row++) {
			List<Object> values = Arrays.asList(table.get(row).get("Value").split(";", -1));
			JsonAssertions.assertThatJson(inPath(response, table.get(row).get("JPath")))
					.when(Option.IGNORING_ARRAY_ORDER).isArray().doesNotContain(values);

		}
	}

	@And("^verify response time is less than \"([^\"]*)\" milliseconds$")
	public void validateResponseTime(String time) {
		if (apiTestContext.getApiTestBase().response.time() >= Long.parseLong(time)) {
			Assert.fail("actual response time is more than excpected response time. expected time is: " + time
					+ " milliseconds and actual time is: " + apiTestContext.getApiTestBase().response.time());
		}
	}

	@And("verify response headers")
	public void validateResponseHeaders(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Headers headers = apiTestContext.getApiTestBase().response.getHeaders();
		for (int i = 0; i < table.size(); i++) {
			Assert.assertEquals(headers.getValue(table.get(i).get("HeaderName")), table.get(i).get("HeaderValue"),
					"Header value mismatch");
		}
	}

	@And("verify response cookies")
	public void validateResponseCookies(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		Map<String, String> cookies = apiTestContext.getApiTestBase().response.getCookies();
		for (int i = 0; i < table.size(); i++) {
			Assert.assertEquals(cookies.get(table.get(i).get("HeaderValue")), table.get(i).get("HeaderName"),
					"cookie value mismatch");
		}
	}

	@And("verify the {string} HTML response")
	public void validateResponseHtml(String value) {
		XmlPath htmlPath = new XmlPath(HTML, apiTestContext.getApiTestBase().response.getBody().asString());
		Assert.assertEquals(htmlPath.getString("html.body.p"), value);
	}

	@And("verify the HTML response")
	public void validateResponseHtml(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		XmlPath htmlPath = new XmlPath(HTML, apiTestContext.getApiTestBase().response.getBody().asString());

		for (int i = 0; i < table.size(); i++) {
			Assert.assertEquals(htmlPath.getString(table.get(i).get("Path")), table.get(i).get("Value"),
					"htmlpath value mismatch");

		}
	}
	
	
	
	@And("I capture the Productid {string}")
	public void fetchStringValue(String jPaths) {
		String response = apiTestContext.getApiTestBase().response.asString();
		Map<String,Object> contextMap = new HashMap<String,Object>();
		for(String path:jPaths.split(";") ) {
			String key = path.substring(path.lastIndexOf(".")+1,path.length());
			contextMap.put(key, JsonUtils.getJSonObjectValue(response, path));
			System.out.println("value is "+String.valueOf(JsonUtils.getJSonObjectValue(response, path)));
		}
		
		apiTestContext.getApiTestBase().contextMap=contextMap;
		}
	
	
	
	
	
}
