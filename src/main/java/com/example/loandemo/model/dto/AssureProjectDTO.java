package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class AssureProjectDTO implements RequestDTO {

	public String merBillNo;
	public String assureIpsAcctNo;
	public String assureType;
	public String assureIncome;
	public String projectNo;
	public String merDate;
	public String s2SUrl;
	public String assureAmt;

	public AssureProjectDTO(){}
	public AssureProjectDTO(String merBillNo, String assureIpsAcctNo,
							String assureType, String assureIncome, String projectNo,
							String merDate, String s2sUrl, String assureAmt) {
		super();
		this.merBillNo = merBillNo;
		this.assureIpsAcctNo = assureIpsAcctNo;
		this.assureType = assureType;
		this.assureIncome = assureIncome;
		this.projectNo = projectNo;
		this.merDate = merDate;
		s2SUrl = s2sUrl;
		this.assureAmt = assureAmt;
	}


	@Override
	public boolean validate(String merchantID) { //all checked
		boolean result=true;
		result = result && Validate.NotEmpty("merBillNo",merBillNo);
		result = result && Validate.NotEmpty("merDate",merDate);
		
		result = result && Validate.NotEmpty("projectNo",projectNo);
		result = result && Validate.NotEmpty("assureAmt",assureAmt);
		result = result && Validate.NotEmpty("assureIncome",assureIncome);
		result = result && Validate.NotEmpty("assureIpsAcctNo",assureIpsAcctNo);
		result = result && Validate.NotEmpty("s2SUrl",s2SUrl);
		result = result && Validate.oneOrTwo("assureType", assureType);
		
		return result;
	}
}
