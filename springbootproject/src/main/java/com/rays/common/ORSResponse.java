package com.rays.common;

import java.util.HashMap;
import java.util.Map;

public class ORSResponse {

	private static String DATA = "data";
	private static String INPUR_ERROR = "inputerror";
	private static String MESSAGE = "message";

	private boolean success;

	private Map<String, Object> result = new HashMap<String, Object>();

	public ORSResponse() {

	}

	public ORSResponse(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public void addData(Object value) {
		result.put(DATA, value);
	}

	public void addInputError(Object value) {
		result.put(INPUR_ERROR, value);
	}

	public void addMessage(Object value) {
		result.put(MESSAGE, value);
	}

	public void addResult(String key, Object value) {
		result.put(key, value);
	}

}
