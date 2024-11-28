package com.testleaf.web.browser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import org.openqa.selenium.Capabilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.testleaf.constants.BrowserTypes;

public class BrowserCapabilitiesKey {
	
	private BrowserTypes browserType;
	private String capabilityKey;
	
	public BrowserCapabilitiesKey(BrowserTypes browserType, Capabilities capabilities) {
		
		this.browserType = browserType;
		this.capabilityKey = serializeCapabilities(browserType, capabilities);
		
	}
	
	/**
	 * Serializes the Capabilities object into a consistent JSON string.
     * Only relevant fields are serialized, and lists are sorted to ensure
     * that different orders of the same elements produce identical keys.
     * 
	 * @param browserType The type of browser
	 * @param capabilities The desired capabilities/configurations for the browser
	 * @return A JSON String representing the serialized capabilities
	 */

	private String serializeCapabilities(BrowserTypes browserType, Capabilities capabilities) {
		
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		
		switch(browserType) {
			
		case CHROME:
			serializeBrowserOptions(capabilities, "goog:chromeOptions", json, gson);
			break;
		case FIREFOX:
			serializeBrowserOptions(capabilities, "moz:firefoxOptions", json, gson);
			break;
		default:
			//For browsers without specific options, serialize general capabilities
			serializeGeneralOptions(capabilities, json, gson);
			break;
		
		}
		
		return gson.toJson(json);

	}
	
	/**
	 * Serializes general capabilities (excluding browser-specific options) and adds them to the main JSON object.
	 * 
	 * @param capabilities The Capabilities object.
	 * @param mainJson	   The main JSON object to which serialized capabilities are added.
	 * @param gson		   The Gson instance for JSON serialization.
	 */

	private void serializeGeneralOptions(Capabilities capabilities, JsonObject mainJson, Gson gson) {
		
		Map<String, Object> optionsMap = new TreeMap<>(capabilities.asMap());
		
		//Remove browser specific options to reduce redundancy
		optionsMap.remove("goog:chromeOptions");
		optionsMap.remove("moz:firefoxOptions");
		
		JsonObject generalJson = new JsonObject();
		
		for(Entry<String, Object> entry : optionsMap.entrySet()) {
			
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(value instanceof List) {
				JsonArray array = new JsonArray();
				for(Object item : (List<?>)value) {
					array.add(gson.toJsonTree(item));
				}
				
				
			}else {
				generalJson.add(key, gson.toJsonTree(value));
			}
		}
		mainJson.add("GeneralCapabilities", generalJson);
	}
	
	/**
	 * Serializes browser-specific and adds them to the main JSON object.
	 * 
	 * @param capabilities The Capabilities object.
	 * @param optionskey   The key under which BrowserSpecific options are stored.
	 * @param mainJson	   The main JSON object to which serialized capabilities are added
	 * @param gson		   The Gson instance for JSON serialization
	 */

	@SuppressWarnings("unchecked")
	private void serializeBrowserOptions(Capabilities capabilities, String optionskey, JsonObject mainJson, Gson gson) {
		
		
		Map<String, Object> browserOptionsMap = (Map<String, Object>) capabilities.getCapability(optionskey);
		
		if(browserOptionsMap != null) {
			
			JsonObject browserJson = new JsonObject();
			
			//Serialize and sort 'args' if present
			List<String> args = (List<String>) browserOptionsMap.getOrDefault("args", Collections.EMPTY_LIST);
			
			List<String> sortedArgs = new ArrayList<>(args);
			
			Collections.sort(sortedArgs);
			
			JsonArray argsArray = new JsonArray();
			
			for(String arg : sortedArgs) {
				argsArray.add(arg);
			}
			browserJson.add("args", argsArray);
			mainJson.add(optionskey, browserJson);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(browserType, capabilityKey);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		BrowserCapabilitiesKey that = (BrowserCapabilitiesKey) o;
		
		if(browserType != that.browserType) return false;
		return capabilityKey.equals(that.capabilityKey);
	}

	@Override
	public String toString() {
		return "BrowserCapabilitiesKey {" +
				"browserType=" + browserType + 
				", capabilityKey=" + capabilityKey + "}";
	}
	
	
	
	
	
	

}
