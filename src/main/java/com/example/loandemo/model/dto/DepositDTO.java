package com.example.loandemo.model.dto;
import com.example.loandemo.util.Validate;


public class DepositDTO implements RequestDTO {
	public DepositDTO(String merFee, String merBillNo, String taker,
                      String merFeeType, String depositType, String webUrl,
                      String merDate, String ipsFeeType, String userType, String trdAmt,
                      String ipsAcctNo, String bankCode, String channelType, String s2sUrl) {
		super();
		this.merFee = merFee;
		this.merBillNo = merBillNo;
		this.taker = taker;
		this.merFeeType = merFeeType;
		this.depositType = depositType;
		this.webUrl = webUrl;
		this.merDate = merDate;
		this.ipsFeeType = ipsFeeType;
		this.userType = userType;
		this.trdAmt = trdAmt;
		this.ipsAcctNo = ipsAcctNo;
		this.bankCode = bankCode;
		this.channelType = channelType;
		s2SUrl = s2sUrl;
	}
	public DepositDTO(){  //Ĭ�Ϲ��캯��
		
	}
	public String merFee;
	public String merBillNo;
	public String taker;
	public String merFeeType;
	public String depositType;
	public String webUrl;
	public String merDate;
	public String ipsFeeType;
	public String userType;
	public String trdAmt;
	public String ipsAcctNo;
	public String bankCode;
	public String channelType;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) {  //part check , there is some not clear
		// TODO Auto-generated method stub
		boolean result=true;
		result = result && Validate.NotEmpty("merBillNo",merBillNo);
		result = result && Validate.NotEmpty("merDate",merDate);
		result = result && Validate.oneOrTwo("depositType",depositType);
		if(!channelType.isEmpty()){
			result = result && Validate.oneOrTwo("channelType",channelType);
		}else{ //Ϊ��
			
		}
		result = result && Validate.oneOrTwo("userType",userType);
		result = result && Validate.NotEmpty("ipsAcctNo",ipsAcctNo);
		result = result && Validate.NotEmpty("trdAmt",trdAmt);
		result = result && Validate.NotEmpty("merFee",merFee);
		result = result && Validate.NotEmpty("s2SUrl",s2SUrl);
		result = result && Validate.NotEmpty("webUrl",webUrl);
		
		result=result && Validate.oneOrTwo("ipsFeeType", ipsFeeType);
		result=result && Validate.oneOrTwo("merFeeType", merFeeType);
		result=result && Validate.oneOrTwo("taker", taker);
		
		return result;
	}
}
