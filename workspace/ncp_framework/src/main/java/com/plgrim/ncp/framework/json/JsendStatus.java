package com.plgrim.ncp.framework.json;

public enum JsendStatus { 
	/**
	 * All went well, and (usually) some data was returned.
	 */
	success, 
	
	/**
	 * There was a problem with the data submitted, or some pre-condition of the API call wasn't satisfied
	 */
	fail,
	
	/**
	 * An error occurred in processing the request, i.e. an exception was thrown
	 */
	error;
}
