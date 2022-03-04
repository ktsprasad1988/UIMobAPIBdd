package com.Armorcode.bdd.api.step_definitions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Armorcode.bdd.base.TestContext;
import com.Armorcode.bdd.ccl.WS_PayloadsHeaders;
import com.Armorcode.bdd.integrations.common_utils.Constants;
import com.Armorcode.bdd.integrations.common_utils.JsonUtils;
import com.Armorcode.bdd.integrations.common_utils.ResourceURIS;
import com.Armorcode.bdd.integrations.common_utils.RestAssuredUtils;
import com.Armorcode.bdd.integrations.common_utils.TestUtils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

/*
 * All type of request body, request parameters modification methods should be present in this class 
 */
public class ApiRequestModificationSteps {

	private TestContext apiTestContext;

	public ApiRequestModificationSteps(TestContext apiTestContext) {
		this.apiTestContext = apiTestContext;
	}

	/*
	 * Gets the payload by payload name if already not loaded Upadate or replace
	 * payload content from data table based on the payload name Input - Request
	 * Name,DataTable containing JPath of fields to be replaced
	 */
	@When("^(?:I update the|I replace the) \"([^\"]*)\" payload$")
	public void replacePayLoad(String fileName, DataTable dataTable) {
		// Gets the sample payload form database if request data is not empty
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
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
				ResourceURIS.getResourceURI(fileName));

		// updates or replaces payload content from data table
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(),
				dataTable.asMaps().get(0).get("PayLoad"));
	}

	/*
	 * Gets the request from DB based on request name and Modifies payload based on
	 * the field Jpaths and values from Data Table Input - Request Name,DataTable
	 * containing JPath of fields to be modified
	 * 
	 */

	@When("^(?:I modify the fields in|I modify the field values in|I update the fields in|"
			+ "I set null value to the fields in|I set the field values in) \"([^\"]*)\" payload$")
	public void modifyPayload(String fileName, DataTable dataTable) {
		Configuration configuration = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
		File json = new File(Constants.JSONINPUT_DIR + fileName + ".json");
		String request = null;
		try {
			request = JsonPath.using(configuration).parse(json).jsonString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, String>> table = dataTable.asMaps();
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.PAYLOAD.name(), request);
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
				ResourceURIS.getResourceURI(fileName));
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(),
				TestUtils.modifyJsonPayLoadFromDataTable(request, table));
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		System.out.println(LocalDateTime.now());
	}

	/*
	 * removes single field in the payload received as inline param Input - Request
	 * Name, JPath of field to be removed
	 */

	@When("^I remove the \"([^\"]*)\" from \"([^\"]*)\" payload$")
	public void removeFieldFromPayLoad(String fieldJPath, String fileName) {
		// List<Map<String, String>> table = dataTable.asMaps();
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
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
				ResourceURIS.getResourceURI(fileName));
		if (!apiTestContext.getApiTestBase().requestMap.isEmpty()) {
			request = apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name());
			request = JsonUtils.removeJsonObject(request, fieldJPath);
		} else {
			throw new RuntimeException(fileName + " not such sample payload json file");
		}
		// }
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(), request);

	}

	/*
	 * Removes fields from payload loaded in previous steps or removes fields in the
	 * payload after fetching from DB in case not loaded in previous steps Input -
	 * Request Name, DataTable containing JPath of fields to be removed
	 */
	@When("^I remove the fields from \"([^\"]*)\" payload$")
	public void removeFieldFromPayLoad(String fileName, DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
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
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
				ResourceURIS.getResourceURI(fileName));
		for (int i = 0; i < table.size(); i++) {
			request = JsonUtils.removeJsonObject(request, table.get(i).get("JPath"));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(), request);
	}

	/*
	 * Removes fields from payload loaded in previous steps Input - DataTable
	 * containing JPath of fields to be removed
	 */
	@And("^(?:I remove the fields)$")
	public void removeFieldFromPayLoad(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String request = apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name());
		for (int i = 0; i < table.size(); i++) {
			request = JsonUtils.removeJsonObject(request, table.get(i).get("JPath"));
		}
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(), request);

	}

	@When("^I modify the json fields in \"([^\"]*)\" payload$")
	public void modifyJsonFieldInPayload(String fileName, DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
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
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
				ResourceURIS.getResourceURI(fileName));
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(),
				TestUtils.updateJsonFieldsFromDataTable(request, table));
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
	}

	@When("^I modify the field values$")
	public void modifyPayloadFromRequestData(DataTable dataTable) {
		List<Map<String, String>> table = dataTable.asMaps();
		String request = apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name());
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(),
				TestUtils.modifyJsonPayLoadFromDataTable(request, table));
		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
	}

	@And("I send request with same  Productid")
	public String returnStringValue(String jPath) {
		String key = jPath.substring(jPath.lastIndexOf("\\."), jPath.length());
		return apiTestContext.getApiTestBase().requestMap.get("key");

	}
@And("I create subProduct with \"([^\"]*)\" with same Id \"([^\"]*)\"")
	public void modifyPayloadWithChainData(String fileName, String jPaths, DataTable dataTable) {
		Configuration configuration = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
		File json = new File(Constants.JSONINPUT_DIR + fileName + ".json");
		String request = null;
		try {
			request = JsonPath.using(configuration).parse(json).jsonString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, String>> table = dataTable.asMaps();
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.PAYLOAD.name(), request);
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
		apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
				ResourceURIS.getResourceURI(fileName));
		request = TestUtils.modifyJsonPayLoadFromDataTable(request, table);
		/*
		 * List<Map<String, String>> chainTable = new ArrayList(); Map<String, String>
		 * chainMap = new HashMap<String,String>();
		 */
		String[] paths = jPaths.split(";");
		for (String path : paths) {
			String key = path.substring(path.lastIndexOf(".")+1,path.length());
			Object value = apiTestContext.getApiTestBase().contextMap.get(key);
			System.out.println("value is"+String.valueOf(value));
			request = JsonUtils.ModifyJSON(request, path, value);
			System.out.println("I am in 2nd key ->" + key);
			System.out.println("I am in 2nd path ->" + path);
		}
		apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(), request);
		apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name());

		apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
		System.out.println(LocalDateTime.now());
	}

@And("I create scanConfiguration with \"([^\"]*)\"")
public void modifyPayloadWithData(String fileName, DataTable dataTable) {
	Configuration configuration = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
	File json = new File(Constants.JSONINPUT_DIR + fileName + ".json");
	String request = null;
	try {
		request = JsonPath.using(configuration).parse(json).jsonString();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<Map<String, String>> table = dataTable.asMaps();
	apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.PAYLOAD.name(), request);
	apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.REQUEST_NAME.name(), fileName);
	apiTestContext.getApiTestBase().requestMap.put(WS_PayloadsHeaders.RESOURCE_URI.name(),
			ResourceURIS.getResourceURI(fileName));
	request = TestUtils.modifyJsonPayLoadFromDataTable(request, table);
	/*
	 * List<Map<String, String>> chainTable = new ArrayList(); Map<String, String>
	 * chainMap = new HashMap<String,String>();
	 */
	
	apiTestContext.getApiTestBase().requestMap.replace(WS_PayloadsHeaders.PAYLOAD.name(), request);
	apiTestContext.getApiTestBase().requestMap.get(WS_PayloadsHeaders.PAYLOAD.name());

	apiTestContext.getApiTestBase().specification.baseUri(RestAssuredUtils.baseURI);
	System.out.println(LocalDateTime.now());
}

}