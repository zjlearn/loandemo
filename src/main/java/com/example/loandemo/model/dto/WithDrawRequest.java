package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class WithDrawRequest implements RequestDTO {

	public WithDrawRequest(String merFee, String merBillNo, String merFeeType,
			String webUrl, String merDate, String ipsFeeType, String userType,
			String trdAmt, String ipsAcctNo, String s2sUrl) {
		super();
		this.merFee = merFee;
		this.merBillNo = merBillNo;
		this.merFeeType = merFeeType;
		this.webUrl = webUrl;
		this.merDate = merDate;
		this.ipsFeeType = ipsFeeType;
		this.userType = userType;
		this.trdAmt = trdAmt;
		this.ipsAcctNo = ipsAcctNo;
		s2SUrl = s2sUrl;
	}
	public WithDrawRequest(){}
	
	public String merFee;
	public String merBillNo;
	public String merFeeType;
	public String webUrl;
	public String merDate;
	public String ipsFeeType;
	public String userType;
	public String trdAmt;
	public String ipsAcctNo;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) {  //all check
		boolean result=true;
		result = result && Validate.NotEmpty("merBillNo", merBillNo);
		result = result && Validate.NotEmpty("merDate", merDate);
		result = result && Validate.oneOrTwo("userType", userType);
		result = result && Validate.NotEmpty("trdAmt", trdAmt);
		result = result && Validate.NotEmpty("merFee", merFee);
		result = result && Validate.oneOrTwo("merFeeType", merFeeType);
		result = result && Validate.oneOrTwo("ipsFeeType", ipsFeeType);
		result = result && Validate.NotEmpty("webUrl", webUrl);
		result = result && Validate.NotEmpty("s2SUrl", s2SUrl);
		result = result && Validate.NotEmpty("ipsAcctNo", ipsAcctNo);
		return result;
	}
}
