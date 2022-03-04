package com.Armorcode.bdd.ccl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.Armorcode.bdd.integrations.common_utils.Constants;
import com.Armorcode.bdd.integrations.common_utils.FlatMapUtil;
import com.Armorcode.bdd.integrations.common_utils.JsonComponent;
import com.Armorcode.bdd.integrations.common_utils.PermittedCharacters;
import com.Armorcode.bdd.integrations.common_utils.RandomGenerator;
import com.Armorcode.bdd.integrations.report_utils.Log;
import com.Armorcode.bdd.integrations.report_utils.ReportManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

public class RestAssured_Actions extends ReportManager{
	
	public static HashMap<String, String> testData;
	public static HashMap<String, String> headers;
	public static HashMap<String, String> sendOtpbody ;
	public static HashMap<String, String> verifyOtpbody ;
	
	public static 	ObjectMapper mapper = new ObjectMapper();
	protected static  final AtomicInteger id=new AtomicInteger(1);
	static ReportManager htmlreport=new ReportManager();
	public static String createCustomerBody;
	
	public static HashMap<String, String> setOtpBaseHeader() {
		headers = new HashMap<String, String>();
		headers.put("grant-type", Constants.NEBULA_GRANT_TYPE);
		headers.put("x-api-key", Constants.NEBULA_BEARER_X_API_KEY);
		return headers;
	}
	
	public static HashMap<String, String> setCreateUserHeader(String verificationtoken) {
		headers = new HashMap<String, String>();
		headers.put("grant-type", Constants.NEBULA_GRANT_TYPE);
		headers.put("x-api-key", Constants.NEBULA_BEARER_X_API_KEY);
		headers.put("Authorization", "Bearer " + verificationtoken);
		return headers;
	}
	
	public static HashMap<String, String> generateSendOtpBody(String mobileNum,String channeltype) {
		sendOtpbody = new HashMap<String, String>();
		sendOtpbody.put("mobile",mobileNum );
		sendOtpbody.put("channel",channeltype);
		return sendOtpbody;
	}
	
	public static HashMap<String, String> verifyOtpBody(String mobileNum) {
		verifyOtpbody = new HashMap<String, String>();
		verifyOtpbody.put("mobile",mobileNum );
		verifyOtpbody.put("otp", Constants.BEARER_OTP);
		return verifyOtpbody;
	}
	
	public static HashMap<String, String> generateToken() throws IOException {
		testData = new HashMap<String, String>();

		// Given the range of Mobile number , E.g it should start with 9 or 7 or 6 or 8
		int mobilefirstindex = ThreadLocalRandom.current().nextInt(6, 10);
		String mobileotherindex = RandomGenerator.random(9, PermittedCharacters.NUMERIC);
		String mobileNum = mobilefirstindex + mobileotherindex;
		
		createCustomer(mobileNum, Constants.ANDROID_CHANNEL);
		testData.put("accessToken",getAccessToken(mobileNum, Constants.ANDROID_CHANNEL));
		testData.put("mobileNumber", mobileNum);
		return testData;
	}
	
	public static void createCustomer(String mobileNum,String ChannelType) {
		try {
		// Calling Send OTP endPoint
		postWithHeaderAndJsonBody(setOtpBaseHeader(), generateSendOtpBody(mobileNum,ChannelType), Constants.NEBULA_OTPBASE_URL + "send_otp");
		// Calling Verify OTP endpOint
		Response response = postWithHeaderAndJsonBody(setOtpBaseHeader(), verifyOtpBody(mobileNum),Constants.NEBULA_OTPBASE_URL + "verify_otp");
		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String verificationtoken = jsonObject.get("verification_token").toString();
		//Calling Create user endPoint
		logInfoAPI("Customer Mobile Number : " + mobileNum);
		createCustomerBody = JsonComponent.readTextFile(Constants.CART_JSONINPUT_DIR+"CreateNebullaUser.json")
				.replaceAll("<<MOBILE>>",mobileNum).replaceAll("<<EMAILID>>",RandomGenerator.randomEmailAddress(12));
		 postWithHeaderWithJsonObject(setCreateUserHeader(verificationtoken), createCustomerBody, Constants.NEBULA_CREATEUSER_BASE_URL+"smart_individual");
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public static HashMap<String, String> generateTokenWithUserNameAsOption(String nameOption) throws IOException {
		testData = new HashMap<String, String>();

		// Given the range of Mobile number , E.g it should start with 9 or 7 or 6 or 8
		int mobilefirstindex = ThreadLocalRandom.current().nextInt(6, 10);
		String mobileotherindex = RandomGenerator.random(9, PermittedCharacters.NUMERIC);
		String mobileNum = mobilefirstindex + mobileotherindex;
		String emailId = RandomGenerator.randomEmailAddress(12);
		String firstName = RandomGenerator.randomAlphabetic(8);
		String lastName = RandomGenerator.randomAlphabetic(8);
		createCustomerWithNameOption(mobileNum, Constants.WEB_CHANNEL,nameOption, emailId, firstName, lastName);
		//testData.put("accessToken",getAccessToken(mobileNum, Constants.WEB_CHANNEL));
		testData.put("mobileNumber", mobileNum);
		testData.put("customerEmailId", emailId);
		testData.put("customerFirstName", firstName);
		testData.put("customerLastName", lastName);
		return testData;
	}
	
	public static void createCustomerWithNameOption(String mobileNum,String ChannelType,String nameOption, String emailId, String firstName, String lastName) {
		try {
		// Calling Send OTP endPoint
		postWithHeaderAndJsonBody(setOtpBaseHeader(), generateSendOtpBody(mobileNum,ChannelType), Constants.NEBULA_OTPBASE_URL + "send_otp");
		// Calling Verify OTP endpOint
		Response response = postWithHeaderAndJsonBody(setOtpBaseHeader(), verifyOtpBody(mobileNum),Constants.NEBULA_OTPBASE_URL + "verify_otp");
		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String verificationtoken = jsonObject.get("verification_token").toString();
		//Calling Create user endPoint
		//logInfoAPI("Customer Mobile Number : " + mobileNum);
		if(nameOption.equals("withFirstName")){
			createCustomerBody = JsonComponent.readTextFile(Constants.CART_JSONINPUT_DIR+"CreateNebullaUser.json")
					.replaceAll("<<MOBILE>>",mobileNum).replaceAll("<<EMAILID>>",emailId)
					.replaceAll("<<LASTNAME>>","")
					.replaceAll("<<FIRSTNAME>>",RandomGenerator.randomAlphabetic(8));
		}
		else if(nameOption.equals("withLastName")){
			createCustomerBody = JsonComponent.readTextFile(Constants.CART_JSONINPUT_DIR+"CreateNebullaUser.json")
				.replaceAll("<<MOBILE>>",mobileNum).replaceAll("<<EMAILID>>",RandomGenerator.randomEmailAddress(12))
				.replaceAll("<<LASTNAME>>",RandomGenerator.randomAlphabetic(8))
				.replaceAll("<<FIRSTNAME>>","");
		}else if(nameOption.equals("withoutEmail")) {
			createCustomerBody = JsonComponent.readTextFile(Constants.CART_JSONINPUT_DIR+"CreateNebullaUser.json")
					.replaceAll("<<MOBILE>>",mobileNum).replaceAll("<<EMAILID>>","")
					.replaceAll("<<LASTNAME>>",lastName)
					.replaceAll("<<FIRSTNAME>>",firstName);
		}else if(nameOption.equals("withoutFirstNameAndLastName")){
			createCustomerBody = JsonComponent.readTextFile(Constants.CART_JSONINPUT_DIR+"CreateNebullaUser.json")
					.replaceAll("<<MOBILE>>",mobileNum).replaceAll("<<EMAILID>>",emailId)
					.replaceAll("<<LASTNAME>>","")
					.replaceAll("<<FIRSTNAME>>","");
		}
		 postWithHeaderWithJsonObject(setCreateUserHeader(verificationtoken), createCustomerBody, Constants.NEBULA_CREATEUSER_BASE_URL+"smart_individual");
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public static String getAccessToken(String mobileNum,String ChannelType) {
		// Calling Send OTP endPoint
		postWithHeaderAndJsonBody(setOtpBaseHeader(), generateSendOtpBody(mobileNum,ChannelType), Constants.NEBULA_OTPBASE_URL + "send_otp");
		// Calling Verify OTP endpOint
		Response response = postWithHeaderAndJsonBody(headers, verifyOtpBody(mobileNum),Constants.NEBULA_OTPBASE_URL + "verify_otp");
		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		return accessToken;
	}
	

	public static RequestSpecification setLogs() {
		RestAssured.useRelaxedHTTPSValidation();
		RequestLogSpecification log = RestAssured.given().log();
		return log.all().contentType(getContentType());
	}
	
	public static Response getRequest(Map<String, String> headers,String URL) {
		logInfoAPI("HTTP Get Method and URL is : " + URL);
		return setLogs()
					.headers(headers)
					.when()
					.get(URL);
	}

	public static Response postWithHeaderAndForm(Map<String, String> headers, String jsonObject, String URL) {
		logInfoAPI("HTTP Post Method and URL is : " + URL);
		logInfoAPI("<details><summary><font color=\"green\"><b>Click to view Payload</b></font></summary><p><pre>"+jsonObject+"</pre></script></p></details>");
		return setLogs()
					.headers(headers)
					.body(jsonObject)
					.post(URL);
	}
	
	public static Response postRequestWithoutJsonBody(Map<String, String> headers, String URL) {
		logInfoAPI("HTTP Post Method and URL is : " + URL);
		return setLogs()
					.headers(headers)
					.post(URL);
	}
	
	public static Response postWithHeaderWithJsonObject(Map<String, String> headers, String jsonObject, String URL) {
		return setLogs()
					.headers(headers)
					.body(jsonObject)
					.post(URL);
	}
	
	public static Response postWithHeaderAndJsonBody(Map<String, String> headers, Map<String, String> jsonObject,
			String URL) {
		return setLogs().when().headers(headers).body(jsonObject).post(URL);
	}

	public static Response delete(String URL) {
		return setLogs()
					.when()
					.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers, String jsonObject,
			String URL) {
		Log.info("Request Payload for Delete request : "+ jsonObject);
		logInfoAPI("HTTP Delete Method and URL is : " + URL);
		return setLogs()
					.when()
					.headers(headers)
					.body(jsonObject)
					.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(Map<String, String> headers, String URL)
	{
		logInfoAPI("HTTP Delete Method and URL is : " + URL);
		return setLogs()
					.when()
					.headers(headers)
					.delete(URL);
	}
	
	public static Response putWithHeaderAndBodyForm(Map<String, String> headers, String jsonObject, String URL) {
		logInfoAPI("HTTP Put Method and URL is : " + URL);
		logInfoAPI("<details><summary><font color=\"green\"><b>Click to open HTTP Put Request Payload</b></font></summary><p><pre>"+jsonObject+"</pre></script></p></details>");
		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.put(URL);
	}

	public static Response putRequestwithoutBody(Map<String, String> headers,String URL) {
		logInfoAPI("HTTP Put Method and URL is : " + URL);
		return setLogs()
				.headers(headers)
				.put(URL);
	}
	
	public static Response patchWithHeaderAndBodyForm(Map<String, String> headers, String jsonObject, String URL) {
		logInfoAPI("HTTP Patch Method and URL is : " + URL);
		logInfoAPI("<details><summary><font color=\"green\"><b>Click to open HTTP Patch Request Payload</b></font></summary><p><pre>"+jsonObject+"</pre></script></p></details>");
		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.patch(URL);
	}

	private static ContentType getContentType() {
		return ContentType.fromContentType(Constants.JSON_CONTENTTYPE);

	}

	public static void verifyContentType(Response response, String type){
		logResponseInReport(response);
		try {
		if(response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			logPassAPI("The Content type " + type + " matches the expected content type");
			Log.info("The Content type " + type + " matches the expected content type");
		} else{
			logFailAPI("The Content type " + type + " does not match the expected content type "+ response.getContentType());
			Log.info("The Content type " + type + " does not match the expected content type "+ response.getContentType());
		}
	}catch(Exception e) {
		Assert.fail(e.getMessage());
	}
	}
	public static void verifyResponseCode(Response response, String responseCode){
			if (response.statusCode() == Integer.parseInt(responseCode)) {
			logPassAPI("The status code " + responseCode + " matches the expected code");
			Log.info("The status code " + responseCode + " matches the expected code");
		}else{
			logFailAPI("The status code " + response.statusCode() + " does not match the expected code " + responseCode);
			Log.info("The status code " + response.statusCode() + " does not match the expected code " + responseCode);
			Assert.fail("ValidationFail");
		}
	}

	public static void verifyResponseStatusLineCode(Response response, String code){
		try {
			Assert.assertEquals(response.statusLine() , code);
			logPassAPI("The status code " + code + " matches the expected code");
		} catch (Exception e) {
			logFailAPI("The status code " + code + " does not match the expected code" + response.statusLine());			
			Assert.fail(e.getMessage());
		}
	}

	public static void verifyResponseTime(Response response, long time) {
		if (response.time() <= time) {
			logPassAPI("The time taken " + response.time() + " with in the expected time");
			Log.info("The time taken " + response.time() + " with in the expected time");
		} else {
			logInfoAPI("The time taken " + response.time() + " is greater than expected SLA time " + time);
			Log.info("The time taken " + response.time() + " is greater than expected SLA time " + time);
		}
	}

public static void verifyContentWithKey(Response response,String responsekey, String expVal){
		
		if (response.getContentType().contains("json")) {
			try {
				JsonPath jsonPath = response.jsonPath();
				String actValue = jsonPath.get(responsekey).toString();
				System.out.println("acstualvalye :" + actValue);
				if ((expVal.length() == 0 && actValue.trim() == null)
						|| (actValue.trim() != null && actValue.equalsIgnoreCase(expVal.trim()))) {
					logPassAPI("The JSON response for the Key :" + responsekey + " --->actual value :" + actValue
							+ " matched with the expected value :" + expVal + "");
					Log.info("The JSON response has value " + expVal + " as expected. ");
				} else {
					logFailAPI("The JSON response for the Key :" + responsekey + " --->actual value :" + actValue
							+ " matched with the expected value :" + expVal + "");
					Log.info(
							"The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logInfoAPI(e.getMessage());
				if (e.getMessage().contains("Exception")) {
					Assert.assertTrue(false);
				}
			}
		}else {
			logFailAPI("Received response is in different content type");
		}
	}

	public static void verifyContentsWithKey(Response response, String key, String expVal){
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			if (actValue != null && actValue.get(0).equalsIgnoreCase(expVal)) {
				logPassAPI("The JSON response has value " + expVal + " as expected. ");
			} else {
				logFailAPI("The JSON response :" + actValue + " does not have the value " + expVal + " as expected. ");
			}
		}
	}
	
	public static void verifyContentWithForNullValue(Response response, String key, String expVal){
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if (actValue==null) {
				logPassAPI("The JSON response for the Key :"+ key + " --->actual value :" +  actValue + " matched with the expected value :" + expVal + "");
			} else {
				logPassAPI("The JSON response for the Key :"+ key + " --->actual value :" +  actValue + " matched with the expected value :" + expVal + "");
			}
		}
	}

	public static List<String> getContentsWithKey(Response response, String key){
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);
		} else {
			return null;
		}
	}

	public static String getContentWithKey(Response response, String key) {
		String value = null;
		if (response.getContentType().contains("json")) {
			try {
				JsonPath jsonPath = response.jsonPath();
				value=(String) jsonPath.get(key);
			}catch(Exception e) {
				e.printStackTrace();
				logFailAPI(e.getMessage());
				if(e.getMessage().contains("Exception")) {
					Assert.assertTrue(false);
				}
			}
		} else {
			return null;
		}
		return value;
		}
	
	
	public static int getContentWithKeyIntegerValue(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (int) jsonPath.get(key);
		} else {
			return (Integer) null;
		}
	}
	
	public static boolean getContentWithKeyForBooleanValue(Response response, String key) {
		if (response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (boolean) jsonPath.get(key);
		} else {
			return (Boolean) null;
		}
	}
	
	
	public static void logResponseInReport(Response response) {
		Log.info("Response: " + response.asString());
		Log.info("Status code: " + response.statusCode());
		logInfoAPI("<details><summary><font color=\"green\"><b>Click to open Api Response</b></font></summary><p><pre>"+response.getBody().prettyPrint()+"</pre></script></p></details>");
	}
	
	public static JsonNode resetJsonInnerArrayValues(JsonNode jsonNode,String outerArray,String innerArray) {
		ArrayNode x = (ArrayNode)jsonNode.get("properties");
		for (JsonNode j : x) {
			if((j.get("name").toString()).equals("\"_lastModifiedDate\"") || (j.get("name").toString()).equals("\"_endDate\"")) {
				((ObjectNode)j).put("value", "");
			}
		}
		return jsonNode;
	}
	
	public static JsonNode resetJsonArrayValues(JsonNode jsonNode,String resetkey) {
		ArrayNode y =(ArrayNode)jsonNode;
		for (JsonNode j : y) {
				((ObjectNode)j).put(resetkey, "");
		}
		return jsonNode;
	}
	
	
	public static void validateJsonSchemaByResettingArray(Response response,String expResponseBody) {
		try {
		String actResponseBody=response.getBody().asString();
		JsonNode jsonNode1 =mapper.readTree(expResponseBody);
		JsonNode jsonNode2 = mapper.readTree(actResponseBody);
		boolean result=jsonNode1.equals(jsonNode2);
		if(result==true) {
			//htmlreport.reportRequest("The response has expected JSON Response", "PASS");
		}else {
			//htmlreport.reportRequest("<details><summary><font color=\"red\"><b>Json Node doesn't matched,Click to open Expected Json</b></font></summary><p><pre>"+expResponseBody+"</pre></script></p></details>","INFO");
			fineDifferenceInJsonNode(expResponseBody, actResponseBody);
			}
		}catch(Exception e) {
			Assert.fail("Unable to read JsonNode");
		}
	}
	
	public static void fineDifferenceInJsonNode(String expResponseBody ,String actResponseBody) {
		try {
		StringBuffer sbr=new StringBuffer();
		TypeReference<Map<String, Object>> type = new TypeReference<Map<String, Object>>() {};
		Map<String, Object> leftMap = mapper.readValue(expResponseBody, type);
		Map<String, Object> rightMap = mapper.readValue(actResponseBody, type);
		Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
		Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);
		MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);
		difference.entriesDiffering().forEach((key, value) ->sbr.append(key + " " + value + "<br>"));
		//htmlreport.reportRequest("<details><summary><font color=\"red\"><b>Difference in Json is:</b></font></summary><p><pre>"+sbr.toString()+"</pre></script></p></details>","FAIL");
		}catch(Exception e) {
			Assert.fail("Json response doesn't match");
		}
	}

	
}
