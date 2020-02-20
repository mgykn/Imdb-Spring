package com.project.assessment.response;

import com.project.assessment.type.ErrorType;

import lombok.Data;

@Data
public class BaseResponse {

	private String status;
	private String statusCode;
	private String statusDescription;

	
	public void setSuccess(String desc) {
		this.status = ErrorType.SUCCESS.getStatusType().name();
		this.statusCode = ErrorType.SUCCESS.getResultCode();
		this.statusDescription = desc;
	}
}


