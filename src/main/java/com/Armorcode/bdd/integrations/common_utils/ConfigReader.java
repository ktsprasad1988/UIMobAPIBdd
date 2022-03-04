package com.Armorcode.bdd.integrations.common_utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class ConfigReader {
	private ConfigReader() {

	}

	/*
	 * loadPropertyFile method used for loading the properties file
	 * 
	 * @Param filePath
	 */
	public static Properties loadPropertyFile(String filePath) {
		// Read from properties file
		File file = new File(filePath);
		Properties prop = new Properties();

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (Exception e) {
			// LogUtil.errorLog(ConfigReader.class, "Caught the exception", e);

		}
		return prop;

	}

	/**
	 * will get sting value from properties file
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {

		Properties prop = loadPropertyFile("src/test/resources/Configs/config.properties");

		return prop.getProperty(key);
	}

	/**
	 * will get int value from properties file
	 * 
	 * @param key
	 * @return
	 */
	public static int getIntValue(String key) {
		Properties prop = loadPropertyFile("src/test/resources/Configs/config.properties");

		String strKey = prop.getProperty(key);

		return Integer.parseInt(strKey);
	}

	/**
	 * will get sting value from properties file
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue2(String path, String key) {

		Properties prop = loadPropertyFile(path);

		return prop.getProperty(key);
	}
	
	/**
	 * will set sting value from properties file
	 * 
	 * @param key
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public static void setStringValue(String path, String key, String value) throws IOException {

		FileOutputStream output = new FileOutputStream(path);

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty(key, value);

            // save properties to project root folder
            prop.store(output, null);
            output.close();
		
	}
	
	
	public static void moveFile(String outFileName, String sourcePath, String destinationPath, String fileextention)
			throws IOException {
		File source = new File(sourcePath);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String ts = sdf.format(source.lastModified());
		File destination = new File(destinationPath + outFileName + ts);
		FileUtils.copyFile(source, destination);
		System.out.println(" new file name is " + outFileName);
	}

}
