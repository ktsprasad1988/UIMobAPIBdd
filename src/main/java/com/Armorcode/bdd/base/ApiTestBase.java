package com.Armorcode.bdd.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTestBase {
	
	public RequestSpecification specification;
	public RequestSpecBuilder specBuilder;
	public String stringRequestObject;
	public Response response;
	public List<Map<String, String>> requestData;
	public Map<String, String> requestMap;
	public Map<String, Object> contextMap;
	
	ApiTestBase(){
		requestData = new ArrayList<>();
		requestMap = new HashMap<String, String> ();
	}
}
