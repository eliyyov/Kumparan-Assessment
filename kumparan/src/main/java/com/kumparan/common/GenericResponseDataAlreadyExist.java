package com.kumparan.common;

public class GenericResponseDataAlreadyExist extends GenericResponse{

	public GenericResponseDataAlreadyExist() {
		this.result = false;
		this.resultCode = ResponseCode.DATA_ALREADY_EXIST.getCode();
		this.resultDescription = ResponseCode.DATA_ALREADY_EXIST.getDescription();
	}

	
	

}