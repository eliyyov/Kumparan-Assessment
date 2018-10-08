package com.kumparan.common;

import com.kumparan.common.ResponseCode;


public class GenericResponseOK extends GenericResponse {
	public GenericResponseOK() {
		this.result = true;
		this.resultCode = ResponseCode.OK.getCode();
		this.resultDescription = ResponseCode.OK.getDescription();
	}
}
