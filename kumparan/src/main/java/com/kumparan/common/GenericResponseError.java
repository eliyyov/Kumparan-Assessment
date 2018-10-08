package com.kumparan.common;

import com.kumparan.common.ResponseCode;


public class GenericResponseError extends GenericResponse {
	public GenericResponseError(String additionalInformation) {
		this.result = false;
		this.resultCode = ResponseCode.GENERAL_ERROR.getCode();
		this.resultDescription = ResponseCode.GENERAL_ERROR.getDescription() + ": " + additionalInformation;
	}
}
