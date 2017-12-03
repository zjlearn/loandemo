package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

public class CommonQueryRequest implements RequestDTO {
	public CommonQueryRequest(String merBillNo, String queryType,
			String ipsAcctNo) {
		super();
		this.merBillNo = merBillNo;
		this.queryType = queryType;
		this.ipsAcctNo = ipsAcctNo;
	}
	public CommonQueryRequest(){}
	public String merBillNo;
	public String queryType;
	public String ipsAcctNo;
	@Override
	public boolean validate(String merchantID) {  // all checked
		boolean result= true;
		result= result && Validate.NotEmpty("ipsAcctNo", ipsAcctNo);
		try{
			if(Integer.valueOf(queryType)>=0 && Integer.valueOf(queryType)<=3)
				;
			else{
				System.out.println("queryType��ֵֻ��Ϊ1-3");
				result=false;
			}
			if(queryType.equals("2") || queryType.equals("02"))
				result = result && Validate.NotEmpty("merBillNo", merBillNo);
				
		}catch(Exception e){
			System.out.println("query ��ֵ����ȷ");
		}
		return result;
	}
}
