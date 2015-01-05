package org.beatific.async.message;

import java.util.HashMap;
import java.util.Map;

public class Header {

	private Map<String, Object> headerMap = new HashMap<String, Object>();

	public Object get(String key) {
		return headerMap.get(key);
	}

	public void put(String key, Object value) {
		headerMap.put(key,  value);
	}
}
