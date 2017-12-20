package com.example.loandemo.model.dto;


import com.example.loandemo.util.Validate;

import java.util.List;

public class TransferDTO implements RequestDTO {  //all checked
	public TransferDTO(String isAutoRepayment, String transferType,
					   String projectNo, String transferMode, String batchNo,
					   String merDate, List<TransferAccDetail> transferAccDetail, String s2sUrl) {
		super();
		this.isAutoRepayment = isAutoRepayment;
		this.transferType = transferType;
		this.projectNo = projectNo;
		this.transferMode = transferMode;
		this.batchNo = batchNo;
		this.merDate = merDate;
		this.transferAccDetail = transferAccDetail;
		s2SUrl = s2sUrl;
	}
	public TransferDTO(){}
	public String isAutoRepayment;
	public String transferType;
	public String projectNo;
	public String transferMode;
	public String batchNo;
	public String merDate;
	public List<TransferAccDetail> transferAccDetail;
	public String s2SUrl;
	@Override
	public boolean validate(String merchantID) {
		boolean result = true;
		result = result && Validate.NotEmpty("batchNo", batchNo);
		result = result && Validate.NotEmpty("merDate", merDate);
		result = result && Validate.NotEmpty("projectNo", projectNo);
		if(Validate.NotEmpty("transferType", transferType)){ //
			if(Integer.valueOf(transferType)>=0 && Integer.valueOf(transferType)<=9)
				;
			else
				System.out.println("transferTypeȡֵֻ��Ϊ1-9 ");
		}else{  //
			;
		}
		result = result && Validate.oneOrTwo("transferMode", transferMode);
		for(TransferAccDetail  accDetail : transferAccDetail)
			result = result && accDetail.validate();
		return result;
	}
}
class TransferAccDetail{
	public TransferAccDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransferAccDetail(String outMerFee, String merBillNo, String outIpsAcctNo,
			String inIpsAcctNo, String trdAmt, String freezeId, String inMerFee) {
		super();
		this.outMerFee = outMerFee;
		this.merBillNo = merBillNo;
		this.outIpsAcctNo = outIpsAcctNo;
		this.inIpsAcctNo = inIpsAcctNo;
		this.trdAmt = trdAmt;
		this.freezeId = freezeId;
		this.inMerFee = inMerFee;
	}
	public String outMerFee;
	public String merBillNo;
	public String outIpsAcctNo;
	public String inIpsAcctNo;
	public String trdAmt;
	public String freezeId;
	public String inMerFee;
	
	public boolean validate(){  //������֤TransferAccDetail����ȷ��
		
		boolean result = true;
		result= result&& Validate.NotEmpty("merBillNo", merBillNo);
		result= result&& Validate.NotEmpty("freezeId", freezeId);
		result= result&& Validate.NotEmpty("outIpsAcctNo", outIpsAcctNo);
		result= result&& Validate.NotEmpty("trdAmt", trdAmt);
		return result;
	}
	
}
