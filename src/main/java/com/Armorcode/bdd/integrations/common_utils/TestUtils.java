package com.Armorcode.bdd.integrations.common_utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtils {

	public static Object getTypedObject(String value, String type) {

		if (type == null)
			type = "";
		switch (type) {
		case "I":
			return Integer.parseInt(value);
		case "D":
			return Double.parseDouble(value);
		case "BI":
			return new BigInteger(value);
		case "Null":
		case "None":
			return null;
		case "Bool":
			return (value.equals("true")) ? true : false;
		case "A":
			return new JSONArray(value);
		case "Spc":
			return " " + value.toString();
		default:
			if (value != null) {
				return value.toString();
			} else {
				return "";
			}
		}
	}

	

	public static String getDateTimeDiff(String timeDiff) {

		System.out.println(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS").format(LocalDateTime.now().minusHours(1)));
		if (timeDiff.contains("d")) {
			return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS")
					.format(LocalDateTime.now().minusDays(Integer.parseInt(timeDiff.split("d")[0])));
		} else if (timeDiff.contains("h")) {
			return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS")
					.format(LocalDateTime.now().minusHours(Integer.parseInt(timeDiff.split("h")[0])));
		} else if (timeDiff.contains("m")) {
			return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS")
					.format(LocalDateTime.now().minusMinutes(Integer.parseInt(timeDiff.split("m")[0])));
		} else
			throw new RuntimeException("timeDiff doesn't contain 'm'(minutes), 'h'(hours), 'd'(days)");
	}

	
	public static Map<String, String> setHeaders(String HeaderKeys, String HeaderValues) {
		Map<String, String> headers = new HashMap<String, String>();
		int i = 0;
		String[] values = HeaderValues.split(";");
		for (String Key : HeaderKeys.split(";")) {
			if (values[i] == null)
				values[i] = "";
			headers.put(Key, values[i]);
			i++;
		}
		return headers;
	}

	public static Map<String, Object> getDefaultFields(String PayLoad, Map<String, Object> keyValue) {
		switch (PayLoad) {
		case "PaymentTransactionPOSTPayload":
			if (!keyValue.containsKey("pg_req_res_id")) {
				keyValue.put("pg_req_res_id", ThreadLocalRandom.current().nextInt(1000000001, 1100000000 + 1));
			}
			break;
		default:
			break;
		}
		return keyValue;
	}

	public static int getRandomNumber(int start, int end) {
		return (start + new Random().nextInt(end - start));
	}

	public static Object checkIfInteger(String Field) {
		Object value = Field;
		try {
			if (value.equals("null")) {
				return JSONObject.NULL;
			}
			value = Integer.parseInt(Field);
			return value;
		} catch (Exception ex) {
			System.out.println("Field value contains some characters or symbols");
			return value;
		}
	}


	public static BufferedReader readFile(String FilePath, String FileName) {
		try {
			Path path = Paths.get(FilePath + FileName);
			return Files.newBufferedReader(path);
		} catch (IOException ex) {
			throw new RuntimeException(
					"Specified file " + FileName + "either could not be accessed or not present in the " + FilePath);
		}
	}

	public static boolean isInvalid(String sInput) throws Exception {
		boolean isInvalid = true;
		if (sInput != null && sInput.trim().length() > 0)
			isInvalid = false;
		return isInvalid;
	}

	public static String getLastWeek() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.minusDays(5);
		return String.format(then.format(format));
	}

	public static String modifyJsonPayLoadFromDataTable(String request, List<Map<String, String>> table) {
		if (table.get(0).containsKey("Type")) {
			for (int i = 0; i < table.size(); i++) {
				request = JsonUtils.ModifyJSON(request, table.get(i).get("JPath"),
						TestUtils.getTypedObject(table.get(i).get("Value"), table.get(i).get("Type")));
			}
		} else {
			for (int i = 0; i < table.size(); i++) {
				String value = (table.get(i).get("Value")==null)?"":table.get(i).get("Value");
                request = JsonUtils.ModifyJSON(request, table.get(i).get("JPath"), value);
			}
		}
		return request;
	}

	public static String updateJsonFieldsFromDataTable(String request, List<Map<String, String>> table) {
		for (int i = 0; i < table.size(); i++) {
			request = JsonUtils.updateJsonField(request, table.get(i).get("Pointer"), table.get(i).get("Value"));
		}
		return request;
	}

	public static List<Map<String, String>> transformObjectToStrinMap(List<Map<String, Object>> objectMap) {
		List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
		for (Map<String, Object> map : objectMap) {
			Map<String, String> hashMap = map.entrySet().stream()
					.collect(Collectors.toMap(entry -> entry.getKey(), entry -> String.valueOf((entry.getValue()))));
			resultMap.add(hashMap);
		}
		return resultMap;
	}
	
	public static File[] findFiles(String regex, File dir) {
		if(Files.exists(Paths.get(dir.getAbsolutePath()))) {
			return dir.listFiles(file -> file.getName().matches(regex));
		}
		else
			throw new RuntimeException("specified directory :"+dir.getAbsolutePath()+ " does not exist");
	}
	
	public static void deleteFiles(String regex, File dir) {
		if(Files.exists(Paths.get(dir.getAbsolutePath()))) {
		File[] fileList = dir.listFiles(file -> file.getName().matches(regex));
		for(File file:fileList)
			file.delete();
	}
	}
	
}
