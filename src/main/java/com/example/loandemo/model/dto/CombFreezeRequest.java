package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;


public class CombFreezeRequest implements RequestDTO {
	public CombFreezeRequest(String projectNo, RedPacket redPacket, Bid bid,
			String webUrl, String merDate, String contractNo, String authNo,
			String regType, String s2sUrl) {
		super();
		this.projectNo = projectNo;
		this.redPacket = redPacket;
		this.bid = bid;
		this.webUrl = webUrl;
		this.merDate = merDate;
		this.contractNo = contractNo;
		this.authNo = authNo;
		this.regType = regType;
		s2SUrl = s2sUrl;
	}
	public CombFreezeRequest(){}
	public String projectNo;
	public RedPacket redPacket;
	public Bid bid;
	public String webUrl;
	public String merDate;
	public String contractNo;
	public String authNo;
	public String regType;
	public String s2SUrl;
	
	@Override
	public boolean validate(String merchantID) {  //part checked. handbook is unclear.
		boolean result = true;
		result = result && Validate.NotEmpty("projectNo", projectNo);
		result = result && Validate.NotEmpty("merDate", merDate);
		
		result = result && Validate.oneOrTwo("regType", regType);
		result = result && Validate.NotEmpty("s2SUrl", s2SUrl);
		result = result && Validate.NotEmpty("webUrl", webUrl);
		//for other type
		
		
		//for redPacket
		result = result && Validate.NotEmpty("s2SUrl", redPacket.merBillNo);
		result = result && Validate.NotEmpty("redPacket.bizType", redPacket.bizType);
		result = result && Validate.NotEmpty("redPacket.trdAmt", redPacket.trdAmt);
		result = result && Validate.NotEmpty("redPacket.freezeMerType", redPacket.freezeMerType);
		result = result && Validate.NotEmpty("redPacket.ipsAcctNo", redPacket.ipsAcctNo);
		
		//for biid
		result = result && Validate.NotEmpty("bid.merBillNo", bid.merBillNo);
		result = result && Validate.NotEmpty("bid.trdAmt", bid.trdAmt);
		result = result && Validate.oneOrTwo("bid.bizType", bid.bizType);
		
		result = result && Validate.NotEmpty("bid.freezeMerType", bid.freezeMerType);
		result = result && Validate.NotEmpty("bid.ipsAcctNo", bid.ipsAcctNo);
		
		return result;
	}
}


class Bid{  
	public String merFee;
	public String merBillNo;
	public String bizType;
	public String otherIpsAcctNo;
	public String freezeMerType;
	public String trdAmt;
	public String ipsAcctNo;
	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bid(String merFee, String merBillNo, String bizType,
			String otherIpsAcctNo, String freezeMerType, String trdAmt,
			String ipsAcctNo) {
		super();
		this.merFee = merFee;
		this.merBillNo = merBillNo;
		this.bizType = bizType;
		this.otherIpsAcctNo = otherIpsAcctNo;
		this.freezeMerType = freezeMerType;
		this.trdAmt = trdAmt;
		this.ipsAcctNo = ipsAcctNo;
	}
}

class RedPacket{
	public String merFee;
	public String merBillNo;
	public String bizType;
	public String otherIpsAcctNo;
	public String freezeMerType;
	public String trdAmt;
	public String ipsAcctNo;
	public String regType;
	public RedPacket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RedPacket(String merFee, String merBillNo, String bizType,
			String otherIpsAcctNo, String freezeMerType, String trdAmt,
			String ipsAcctNo, String regType) {
		super();
		this.merFee = merFee;
		this.merBillNo = merBillNo;
		this.bizType = bizType;
		this.otherIpsAcctNo = otherIpsAcctNo;
		this.freezeMerType = freezeMerType;
		this.trdAmt = trdAmt;
		this.ipsAcctNo = ipsAcctNo;
		this.regType = regType;
	}
}

