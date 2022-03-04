package com.Armorcode.bdd.api.step_definitions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.Armorcode.bdd.base.TestContext;
import com.Armorcode.bdd.ccl.WS_PayloadsHeaders;
import com.Armorcode.bdd.integrations.common_utils.Constants;
import com.Armorcode.bdd.integrations.common_utils.ResourceURIS;
import com.Armorcode.bdd.integrations.common_utils.RestAssuredUtils;
import com.Armorcode.bdd.integrations.common_utils.TestUtils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class PostSteps {

	private TestContext apiTestContext;

	public PostSteps(TestContext apiTestContext) {
		this.apiTestContext = apiTestContext;
	}

	/*
	 * Gets the uri and payload from db using request name and sends the post
	 * request
	 */
	@When("I post the request with {string} payload")
	public void postwithPayload(String fileName) {

		if (apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			Configuration configuration = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
			File json = new File(Constants.JSONINPUT_DIR + fileName + ".json");
			String request = null;
			try {
				request = JsonPath.using(configuration).parse(json).jsonString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.PAYLOAD.name(), request);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
			apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(), ResourceURIS.getResourceURI(fileName));

			apiTestContext.getApiTestBase().specification.baseUri(
					RestAssuredUtils.baseURI);
		}

		apiTestContext.getApiTestBase().response = RestAssuredUtils.post(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name()),
				apiTestContext.getApiTestBase().requestData.get(0).get(WS_PayloadsHeaders.RESOURCE_URI.name()));

	}
	
	@And("^(?:I post the request to endpoint|post the request to endpoint|post the json request|post the same request again|send the post request to endpoint)$")
	public void postRequest() {
		apiTestContext.getApiTestBase().specification.auth().oauth2("3b87b2db-f7df-4d18-a3ee-fbc16edac027");
		apiTestContext.getApiTestBase().response = RestAssuredUtils.post(apiTestContext.getApiTestBase().specification,
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name()),
				apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.RESOURCE_URI.name()));
		System.out.println(apiTestContext.getApiTestBase().response.asString());
	}
}