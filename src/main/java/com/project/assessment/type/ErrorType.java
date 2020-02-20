package com.project.assessment.type;

public enum ErrorType {

	SUCCESS(StatusType.SUCCESS, "0"), 
	VALIDATION_ERROR(StatusType.FAIL, "89"), 
	GENERAL_SYSTEM_ERROR(StatusType.FAIL, "98"),	
	USER_NOT_EXIST(StatusType.FAIL, "96"),
	NOT_UNIQ_PARAMETER(StatusType.FAIL, "93"),
	TEMPORARY_SYSTEM_ERROR(StatusType.FAIL, "91"),
	PWD_WRONG(StatusType.FAIL, "86");

	private final StatusType statusType;
	private final String resultCode;

	ErrorType(final StatusType statusType, final String resCode) {
		this.statusType = statusType;
		this.resultCode = resCode;
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public String getResultCode() {
		return this.resultCode;
	}

}
