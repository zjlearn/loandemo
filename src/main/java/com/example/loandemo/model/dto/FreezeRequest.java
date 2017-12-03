package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class FreezeRequest implements RequestDTO {
	public FreezeRequest(String merFee, String merBillNo, String projectNo,
			String bizType, String otherIpsAcctNo, String ipsAcctNo,
			String merDate, String contractNo, String freezeMerType,
			String webUrl, String trdAmt, String authNo, String regType,
			String s2sUrl) {
		super();
		this.merFee = merFee;
		this.merBillNo = merBillNo;
		this.projectNo = projectNo;
		this.bizType = bizType;
		this.otherIpsAcctNo = otherIpsAcctNo;
		this.ipsAcctNo = ipsAcctNo;
		this.merDate = merDate;
		this.contractNo = contractNo;
		this.freezeMerType = freezeMerType;
		this.webUrl = webUrl;
		this.trdAmt = trdAmt;
		this.authNo = authNo;
		this.regType = regType;
		s2SUrl = s2sUrl;
	}
	public FreezeRequest(){} //
	public String merFee;
	public String merBillNo;
	public String projectNo;
	public String bizType;
	public String otherIpsAcctNo;
	public String ipsAcctNo;
	public String merDate;
	public String contractNo;
	public String freezeMerType;
	public String webUrl;
	public String trdAmt;
	public String authNo;
	public String regType;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) {  //part checked not clear in handbook
		boolean result = true;
		result = result &&  Validate.NotEmpty("merBillNo",merBillNo);
		result = result && Validate.NotEmpty("merDate",merDate);
		try{
			int b=Integer.valueOf(bizType);
			if(b<0 || b>12){
				System.out.println("bizType ��ֵֻ����1-12֮��");
				result =false;
			}
			if(b!=9 && projectNo==null){
				System.out.println("ҵ�����Ͳ�λ���ʱ��projectNo����Ϊ��");
			}	
		}catch(Exception e){
			System.out.println("bizType must not null");
		}
		result = result && Validate.oneOrTwo("regType", regType);
		result = result && Validate.oneOrTwo("freezeMerType", freezeMerType);
		result = result && Validate.NotEmpty("trdAmt",trdAmt);
		result = result && Validate.NotEmpty("s2SUrl",s2SUrl);
		result = result && Validate.NotEmpty("meipsAcctNorDate",ipsAcctNo);
		result = result && Validate.oneOrTwo("freezeMerType", freezeMerType);
		
		//�����������֤
		
		return result;
	}
}
