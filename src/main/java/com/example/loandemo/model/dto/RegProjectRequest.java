package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class RegProjectRequest implements RequestDTO {
	public RegProjectRequest(String isExcess, String merBillNo,
			String projectType, String projectUse, String projectNo,
			String projectName, String finaIpsAcctNo, String finaName,
			String rateType, String merDate, String rateMaxVal,
			String rateMinVal, String cycVal, String finaAccType,
			String finaCertNo, String rateVal, String projectAmt, String s2sUrl) {
		super();
		this.isExcess = isExcess;
		this.merBillNo = merBillNo;
		this.projectType = projectType;
		this.projectUse = projectUse;
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.finaIpsAcctNo = finaIpsAcctNo;
		this.finaName = finaName;
		this.rateType = rateType;
		this.merDate = merDate;
		this.rateMaxVal = rateMaxVal;
		this.rateMinVal = rateMinVal;
		this.cycVal = cycVal;
		this.finaAccType = finaAccType;
		this.finaCertNo = finaCertNo;
		this.rateVal = rateVal;
		this.projectAmt = projectAmt;
		s2SUrl = s2sUrl;
	}
	public RegProjectRequest(){ //Ĭ�Ϲ��캯��
		
	}
	public String isExcess;
	public String merBillNo;
	public String projectType;
	public String projectUse;
	public String projectNo;
	public String projectName;
	public String finaIpsAcctNo;
	public String finaName;
	public String rateType;
	public String merDate;
	public String rateMaxVal;
	public String rateMinVal;
	public String cycVal;
	public String finaAccType;
	public String finaCertNo;
	public String rateVal;
	public String projectAmt;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) { //all chaecked
		boolean result=true;
		
		result = result && Validate.NotEmpty("merBillNo", merBillNo);
		result = result && Validate.NotEmpty("merDate", merDate);
		result = result && Validate.NotEmpty("projectNo", projectNo);
		result = result && Validate.NotEmpty("projectName", projectName);
		
		result = result && Validate.oneOrTwo("projectType", projectType);
		result = result && Validate.NotEmpty("projectAmt", projectAmt);
		result = result && Validate.oneOrTwo("rateType", rateType);
		
		result = result && Validate.NotEmpty("cycVal", cycVal);
	
		result = result && Validate.NotEmpty("finaCertNo", finaCertNo);
		result = result && Validate.NotEmpty("finaName", finaName);
		result = result && Validate.NotEmpty("finaIpsAcctNo", finaIpsAcctNo);
		result = result && Validate.NotEmpty("s2SUrl", s2SUrl);
		
		result = result && Validate.zeroOrOne("isExcess", isExcess);
		result = result && Validate.oneOrTwo("finaAccType", finaAccType);
		
		return result;
	}
}
