package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class AutoSignRequest implements RequestDTO {
	public AutoSignRequest(String webUrl, String merBillNo, String rateMin,
			String rateMax, String bidMin, String bidMax, String validity,
			String merDate, String userType, String cycMaxVal,
			String ipsAcctNo, String signedType, String cycMinVal, String s2sUrl) {
		super();
		this.webUrl = webUrl;
		this.merBillNo = merBillNo;
		this.rateMin = rateMin;
		this.rateMax = rateMax;
		this.bidMin = bidMin;
		this.bidMax = bidMax;
		this.validity = validity;
		this.merDate = merDate;
		this.userType = userType;
		this.cycMaxVal = cycMaxVal;
		this.ipsAcctNo = ipsAcctNo;
		this.signedType = signedType;
		this.cycMinVal = cycMinVal;
		s2SUrl = s2sUrl;
	}
	public AutoSignRequest(){}
	public String webUrl;
	public String merBillNo;
	public String rateMin;
	public String rateMax;
	public String bidMin;
	public String bidMax;
	public String validity;
	public String merDate;
	public String userType;
	public String cycMaxVal;
	public String ipsAcctNo;
	public String signedType;
	public String cycMinVal;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) { //all checked
		boolean result = true;
		result = result && Validate.NotEmpty("merBillNo",merBillNo);
		result = result && Validate.NotEmpty("merDate",merDate);
		result = result && Validate.oneOrTwo("userType",userType);
		result = result && Validate.NotEmpty("ipsAcctNo",ipsAcctNo);
		
		result = result && Validate.zeroOrOne("signedType",signedType);
		result = result && Validate.NotEmpty("validity",validity);
		result = result && Validate.NotEmpty("s2SUrl",s2SUrl);
		result = result && Validate.NotEmpty("webUrl",webUrl);
		return result;
	}
}
