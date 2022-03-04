package com.Armorcode.bdd.integrations.common_utils;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import io.restassured.config.EncoderConfig;
import io.restassured.config.ParamConfig.UpdateStrategy;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.ParamConfig;

public class RestAssuredUtils {

	public static final String baseURI = "https://preprod.armorcode.ai";

	public static RequestSpecBuilder getSpec() {
		RestAssured.config = RestAssuredConfig.config()
				.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
				.and().paramConfig(ParamConfig.paramConfig().queryParamsUpdateStrategy(UpdateStrategy.REPLACE));
		return new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation().setConfig(RestAssured.config);
	}

	public static RequestSpecBuilder getJsonReqSpec() {
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName("");
		basicAuth.setPassword("");
		return new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation().setAuth(basicAuth)
				.setAccept("application/json").setContentType("application/json");
	}

	public static RequestSpecBuilder getXxxEncodedReqSpec() {
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName("");
		basicAuth.setPassword("");
		return new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation().setAuth(basicAuth)
				.setAccept("application/json").setContentType("application/x-www-form-urlencoded");
	}

	public static Response get(RequestSpecification specification, String resourceURI) {

		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, Map<String, Object> pathParam, String resourceURI) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).log()
				.all().get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> queryParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		if (resourceURI.contains("?")) {
			resourceURI = resourceURI.substring(0, resourceURI.indexOf('?'));
		}
		Response response = given().log().all().spec(specification).relaxedHTTPSValidation().when()
				.queryParams(queryParam).get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> queryParam,
			Map<String, Object> pathParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		if (resourceURI.contains("?")) {
			resourceURI = resourceURI.substring(0, resourceURI.indexOf('?'));
		}
		Response response = RestAssured.given().spec(specification).when().queryParams(queryParam).pathParams(pathParam)
				.log().all().get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			String body) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		Response response = given().spec(specification).when().body(body).pathParams(pathParam).get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			Map<String, Object> queryParam, String body) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		Response response = given().spec(specification).when().body(body).queryParams(queryParam).pathParams(pathParam)
				.get(resourceURI);

		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, String body,
			Map<String, Object> queryParam) {

		Response response = given().spec(specification).when().body(body).queryParams(queryParam).get(resourceURI);

		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, String body) {
		Response response = given().spec(specification).relaxedHTTPSValidation().when().body(body).log().all()
				.get(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, File bodyFile, String resourceURI) {
		return given().spec(specification).body(bodyFile).post(resourceURI);
	}

	public static Response post(RequestSpecification specification, String body, String resourceURI) {

		Response response = given().spec(specification).log().all().relaxedHTTPSValidation().when().body(body).log().all()
				.post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, String body, Map<String, Object> pathParam,
			String resourceURI) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).body(body)
				.log().all().post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, Map<String, Object> pathParam,
			Map<String, Object> queryParam, String resourceURI) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().log().all().pathParams(pathParam).queryParams(queryParam)
				.post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, String body, Map<String, Object> pathParam,
			String resourceURI, Map<String, Object> queryParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam).body(body)
				.post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, String body, String resourceURI,
			Map<String, Object> queryParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().queryParams(queryParam).body(body).log().all()
				.post(resourceURI);

		return response;
	}

	public static Response put(RequestSpecification specification, String body, String resourceURI) {

		Response response = given().spec(specification).relaxedHTTPSValidation().when().body(body).log().all()
				.put(resourceURI);

		return response;
	}

	public static Response put(RequestSpecification specification, String body, String resourceURI,
			Map<String, Object> pathParam) {

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).body(body)
				.log().all().put(resourceURI);

		return response;

	}

	public static Response put(RequestSpecification specification, String body, Map<String, Object> queryParam,
			String resourceURI) {

		Response response = given().spec(specification).when().queryParams(queryParam).body(body).put(resourceURI);

		return response;
	}

	public static Response put(RequestSpecification specification, String body, Map<String, Object> queryParam,
			String resourceURI, Map<String, Object> pathParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam).body(body)
				.put(resourceURI);

		return response;
	}

	public static Response patch(RequestSpecification specification, String body, Map<String, Object> pathParam,
			String resourceURI) {

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).body(body)
				.log().all().patch(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI) {

		Response response = given().log().all().spec(specification).when().delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, Map<String, Object> pathParam,
			String resourceURI) {

		Response response = given().spec(specification).when().pathParams(pathParam).delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, Map<String, Object> pathParam, String resourceURI,
			Map<String, Object> queryParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam)
				.delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI,
			Map<String, Object> queryParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI,
			Map<String, Object> queryParam, Map<String, Object> pathParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam)
				.delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			String body) {
		Response response = given().spec(specification).when().body(body).pathParams(pathParam).delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			Map<String, Object> queryParam, String body) {
		Response response = given().spec(specification).when().body(body).queryParams(queryParam).pathParams(pathParam)
				.delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, String body,
			Map<String, Object> queryParam) {
		if (resourceURI.contains("?")) {
			resourceURI = resourceURI.substring(0, resourceURI.indexOf('?'));
		}
		Response response = given().spec(specification).when().body(body).queryParams(queryParam).log().all()
				.delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, String body) {
		Response response = given().spec(specification).body(body).when().delete(resourceURI);
		return response;
	}

}
