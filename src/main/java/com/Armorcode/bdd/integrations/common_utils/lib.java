package com.Armorcode.bdd.integrations.common_utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class lib {
	public static Properties prop;
	public static String resourceLocation = Constants.RESOURCE_DIR;
	public static String dataInputLocation = Constants.DATAINPUT_DIR;
	public static ThreadLocal<String> testName = new ThreadLocal<String>();

	public static boolean isInvalid(String sInput) throws Exception {
		boolean isInvalid = true;
		if (sInput != null && sInput.trim().length() > 0)
			isInvalid = false;
		return isInvalid;
	}
	
	public static Properties getProperty() throws IOException
	{
		prop= new Properties();
		FileInputStream ip = new FileInputStream("src"+File.separator+"test"+File.separator+"resources"+File.separator+"ConfigFiles"+File.separator+"config.properties");
		prop.load(ip);
		return prop;
	}
}
