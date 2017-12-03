package com.example.loandemo.model.dto;

public class BankQueryRequest implements RequestDTO {

	public BankQueryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String merchantID) { //all checked
		// TODO Auto-generated method stub
		return true;
	}

}
