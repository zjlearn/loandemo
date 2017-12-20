package com.example.loandemo.model.dto;

public class BankQueryDTO implements RequestDTO {

	public BankQueryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String merchantID) { //all checked
		// TODO Auto-generated method stub
		return true;
	}

}
