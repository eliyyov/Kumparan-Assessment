package com.kumparan.common;

import com.kumparan.common.ResponseCode;


public class GenericResponseDataNotFound extends GenericResponse {
	public GenericResponseDataNotFound() {
		this.result = false;
		this.resultCode = ResponseCode.DATA_NOT_FOUND.getCode();
		this.resultDescription = ResponseCode.DATA_NOT_FOUND.getDescription();
	}
}
