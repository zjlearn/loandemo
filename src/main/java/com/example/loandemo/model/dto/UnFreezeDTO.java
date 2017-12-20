package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class UnFreezeDTO implements RequestDTO {

	public UnFreezeDTO(String merFee, String merBillNo, String projectNo,
                       String bizType, String webUrl, String merDate, String trdAmt,
                       String ipsAcctNo, String freezeId, String s2sUrl) {
		super();
		this.merFee = merFee;
		this.merBillNo = merBillNo;
		this.projectNo = projectNo;
		this.bizType = bizType;
		this.webUrl = webUrl;
		this.merDate = merDate;
		this.trdAmt = trdAmt;
		this.ipsAcctNo = ipsAcctNo;
		this.freezeId = freezeId;
		s2SUrl = s2sUrl;
	}
	public UnFreezeDTO(){}
	public String merFee;
	public String merBillNo;
	public String projectNo;
	public String bizType;
	public String webUrl;
	public String merDate;
	public String trdAmt;
	public String ipsAcctNo;
	public String freezeId;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) {  //all checked.
		boolean result=true;
		result = result && Validate.NotEmpty("merBillNo",merBillNo);
		result = result && Validate.NotEmpty("merDate",merDate);
		
		result = result && Validate.NotEmpty("projectNo",projectNo);
		result = result && Validate.NotEmpty("freezeId",freezeId);
		result = result && Validate.NotEmpty("trdAmt",trdAmt);
		result = result && Validate.NotEmpty("s2SUrl",s2SUrl);
		result = result && Validate.NotEmpty("meipsAcctNorDate",ipsAcctNo);
		if(bizType.length()!=0){
				if(Integer.valueOf(bizType)>=1 && Integer.valueOf(bizType)<=4 ){  //not clear in bizType can be null or not
			System.out.println("bizType��ֵӦ����1-4֮�䣡��");
			result=false;
				}
		}
		return result;
	}
}
