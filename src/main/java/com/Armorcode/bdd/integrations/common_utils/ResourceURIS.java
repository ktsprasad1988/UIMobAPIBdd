package com.Armorcode.bdd.integrations.common_utils;

public class ResourceURIS {

	
	private static final String CreateProduct = "/user/product";
	private static final String CreateSubProduct = "/api/sub-product";
	private static final String ScanConfiguration = "/user/tools/generic/configurations";
	private static final String ScanResult = "/api/scans";
		
	public static String getResourceURI(String requestName) {
		switch(requestName) {
		case "CreateProduct" : return CreateProduct;
		case "CreateSubProduct" : return CreateSubProduct;
		case "ScanConfiguration" : return ScanConfiguration;
		case "ScanResult" : return ScanResult;
		default: throw new RuntimeException("Resource uri not defined for the specific file name - "+requestName);
		}
	}
	
	

}
