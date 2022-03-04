package com.Armorcode.bdd.api.step_definitions;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;



public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Response response =
		 * given().auth().oauth2("3b87b2db-f7df-4d18-a3ee-fbc16edac027") //
		 * .header("Authorization", "Bearer " + "3b87b2db-f7df-4d18-a3ee-fbc16edac027")
		 * .contentType("application/json") .when() .body("{\r\n" +
		 * "    \"status\": \"abcd\",\r\n" + "    \"classType\": \"Software only\",\r\n"
		 * + "    \"type\": {\r\n" + "        \"id\": 1\r\n" + "    },\r\n" +
		 * "    \"publicCloud\": true,\r\n" + "    \"internetFacing\": true,\r\n" +
		 * "    \"complianceRequired\": true,\r\n" +
		 * "    \"confidentialityRequirement\": \"Not Defined\",\r\n" +
		 * "    \"availabilityRequirement\": \"Low\",\r\n" +
		 * "    \"availability\": \"None\",\r\n" +
		 * "    \"attackingVector\": \"Network\",\r\n" +
		 * "    \"name\": \"Testing_abc\",\r\n" + "    \"description\": \"Desc\",\r\n" +
		 * "    \"slaTemplateId\": null,\r\n" + "    \"confidentiality\": \"NONE\",\r\n"
		 * + "    \"confidentialityOptions\": \"\"\r\n" + "}")
		 * .post("https://preprod.armorcode.ai/user/product");
		 */
		String path= "$.product.id";
		String key = path.substring(path.lastIndexOf(".")+1,path.length());
		System.out.println(key);
		/*
		 * System.out.println(response.statusCode());
		 * System.out.println(response.asString());
		 */
	}

}
