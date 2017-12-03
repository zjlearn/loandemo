package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

/*
 * 这个是用户添加的请求报文
 */
public class UserAddRequest implements RequestDTO {
	public UserAddRequest(String userName, String merBillNo, String realName,
			String bizType, String mobileNo, String enterName, String merDate,
			String userType, String webUrl, String orgCode, String isAssureCom,
			String s2sUrl, String identNo) {
		super();
		this.userName = userName;
		this.merBillNo = merBillNo;
		this.realName = realName;
		this.bizType = bizType;
		this.mobileNo = mobileNo;
		this.enterName = enterName;
		this.merDate = merDate;
		this.userType = userType;
		this.webUrl = webUrl;
		this.orgCode = orgCode;
		this.isAssureCom = isAssureCom;
		s2SUrl = s2sUrl;
		this.identNo = identNo;
	}
	public UserAddRequest(){
		
	}
	public String userName;
	public String merBillNo;
	public String realName;
	public String bizType;
	public String mobileNo;
	public String enterName;
	public String merDate;
	public String userType;
	public String webUrl;
	public String orgCode;
	public String isAssureCom;
	public String s2SUrl;
	public String identNo;
	/*
	 * validate every 
	 */
	public boolean validate(String  merchantID){  //all checked
		boolean result=true;
		result=result && Validate.NotEmpty("merBillNo", merBillNo);
		result=result && Validate.NotEmpty("merDate", merDate);
		result=result && Validate.oneOrTwo("userType", userType);
		result=result && Validate.oneOrTwo("bizType", bizType);
		
		result=result && Validate.NotEmpty("userName", userName);
		result=result && Validate.NotEmpty("mobileNo", mobileNo);
		result=result && Validate.NotEmpty("identNo", identNo);
		result=result && Validate.oneOrTwo("bizType", bizType);
		result=result && Validate.zeroOrOne("isAssureCom", isAssureCom);
		
		result=result && Validate.NotEmpty("s2SUrl", s2SUrl);
		result=result && Validate.NotEmpty("webUrl", webUrl);
		
		if(userType.equals("2")){ //�û�����Ϊ��ҵ
			if(enterName.isEmpty() || orgCode.isEmpty()){
				System.out.println("���û�����Ϊ��ҵʱ��enterName��orgcode��Ӧ��Ϊ��");
				result=false;
			}	
		}
		return result;
	}
	public String toString(){
		return "userAdd";
	}
}
