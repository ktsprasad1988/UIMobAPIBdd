package com.Armorcode.bdd.integrations.common_utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;;

public class JsonUtils {

	public static String addJsonObject(String jsonString, String jPath, String key, Object value) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		DocumentContext documentContext = JsonPath.using(conf).parse(jsonString);
		documentContext.put(jPath, key, value);
		return documentContext.jsonString();
	}

	public static String updateJsonField(String jsonString, String pointer, Object value) {		
		if (String.valueOf(jsonString).startsWith("{")
				&& String.valueOf(jsonString).endsWith("}")) {
			if (String.valueOf(value).startsWith("{")
					&& String.valueOf(value).endsWith("}")) {
				JsonObject object = Json.createReader(new StringReader(jsonString)).readObject();
		        JsonPointer activePointer = Json.createPointer(pointer);
		        object = activePointer.add(object, Json.createReader(new StringReader((String) value)).readObject());
		        return object.toString();
			}
			else {
				JsonObject object = Json.createReader(new StringReader(jsonString)).readObject();
		        JsonPointer activePointer = Json.createPointer(pointer);
		        object = activePointer.add(object, Json.createReader(new StringReader((String) value)).readArray());
		        return object.toString();
			}
		}else {
			if (String.valueOf(value).startsWith("{")
					&& String.valueOf(value).endsWith("}")) {
				JsonArray array = Json.createReader(new StringReader(jsonString)).readArray();
		        JsonPointer activePointer = Json.createPointer(pointer);
		        array = activePointer.add(array, Json.createReader(new StringReader((String) value)).readObject());
		        return array.toString();
			}
			else {
				JsonArray array = Json.createReader(new StringReader(jsonString)).readArray();
		        JsonPointer activePointer = Json.createPointer(pointer);
		        array = activePointer.add(array, Json.createReader(new StringReader((String) value)).readArray());
		        return array.toString();
			}
		}
			
	}

	public static String ModifyJSON(String jsonString, String jPath, Object newValue) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		DocumentContext documentContext = JsonPath.using(conf).parse(jsonString);
		documentContext = documentContext.set(jPath, newValue);
		return documentContext.jsonString();
	}

	public static String removeJsonObject(String jsonString, String jPath) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		DocumentContext documentContext = JsonPath.using(conf).parse(jsonString);
		documentContext.delete(jPath);
		return documentContext.jsonString();
	}

	public static Object getJSonObjectValue(String jsonString, String jPath) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		Object value = JsonPath.using(conf).parse(jsonString).read(jPath);
		return value;
	}

	public static Object getJSonObjectValue(InputStream jsonFile, String jPath) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		Object value = JsonPath.using(conf).parse(jsonFile).read(jPath);
		return value;
	}

	public static JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
		JSONObject mergedJSON = new JSONObject();
		try {
			mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
			for (String crunchifyKey : JSONObject.getNames(json2)) {
				mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
			}

		} catch (JSONException e) {
			throw new RuntimeException("JSON Exception" + e);
		}
		return mergedJSON;
	}

	public static JSONObject addJsonObject(JSONObject json, String jPath, String fieldValue) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json.toString());
		ObjectNode objectNode = jsonNode.deepCopy();
		int count = jPath.split("/").length;
		String path = null;
		String node = null;
		if (count > 1) {
			int index = jPath.lastIndexOf("/");
			path = jPath.substring(0, index);
			node = jPath.substring(index + 1, jPath.length());
			((ObjectNode) objectNode.at(path)).put(node, fieldValue);
		} else {
			node = jPath.substring(1, jPath.length());
			objectNode.put(node, fieldValue);
		}
		return new JSONObject(objectMapper.writeValueAsString(objectNode).replaceAll("\\\\", ""));
	}

	public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
		Iterator<String> fieldNames = updateNode.fieldNames();

		while (fieldNames.hasNext()) {
			String updatedFieldName = fieldNames.next();
			JsonNode valueToBeUpdated = mainNode.get(updatedFieldName);
			JsonNode updatedValue = updateNode.get(updatedFieldName);

			// If the node is an @ArrayNode
			if (valueToBeUpdated != null && valueToBeUpdated.isArray() && updatedValue.isArray()) {
				// running a loop for all elements of the updated ArrayNode
				for (int i = 0; i < updatedValue.size(); i++) {
					JsonNode updatedChildNode = updatedValue.get(i);
					// Create a new Node in the node that should be updated, if there was no
					// corresponding node in it
					// Use-case - where the updateNode will have a new element in its Array
					if (valueToBeUpdated.size() <= i) {
						((ArrayNode) valueToBeUpdated).add(updatedChildNode);
					}
					// getting reference for the node to be updated
					JsonNode childNodeToBeUpdated = valueToBeUpdated.get(i);
					merge(childNodeToBeUpdated, updatedChildNode);
				}
				// if the Node is an @ObjectNode
			} else if (valueToBeUpdated != null && valueToBeUpdated.isObject()) {
				merge(valueToBeUpdated, updatedValue);
			} else {
				if (mainNode instanceof ObjectNode) {
					((ObjectNode) mainNode).replace(updatedFieldName, updatedValue);
				}
			}
		}
		return mainNode;
	}

	public static String getMergedJson(String parentJson, String updateJson) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode mainNode = objectMapper.readTree(parentJson);
			JsonNode updateNode = objectMapper.readTree(updateJson);
			return objectMapper.writeValueAsString(merge(mainNode, updateNode));
		} catch (Exception e) {
			throw new RuntimeException("Failed merging json" + e.getMessage());
		}
	}

	public static void JsonSchemaVaidator(Response response, String schema) {
		JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
				.setValidationConfiguration(
						ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
				.freeze();
		JsonSchemaValidator.settings = JsonSchemaValidatorSettings.settings().with().jsonSchemaFactory(factory).and()
				.with().checkedValidation(false);

		response.then().assertThat().body(matchesJsonSchema(schema));

	}

}
