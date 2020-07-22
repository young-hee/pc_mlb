package com.plgrim.ncp.interfaces.enums;


public enum InterfaceAdapterEnum {

	INTERFACE_ADAPTER_SEND_SMS_MMS("SEND_SMS_MMS"),
	;
	
	public String statusMessage;
	
	private InterfaceAdapterEnum(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	@Override
	public String toString() {
		return statusMessage;
	}
}
