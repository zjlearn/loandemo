package com.example.loandemo.util;

public class Validate {
	public static Boolean NotEmpty(String Type, String value){
		
		if(value.isEmpty())
			System.out.println(Type + "����Ϊ�գ�");
		return false;
	}
	public static Boolean zeroOrOne( String type,String value){
		boolean result=false;
		
		if(value.equals("1") || value.equals("0"))
			result=true;
		else
			System.out.println(type+ "��ֵֻ��ȡ1��0��");
		return result;
	}
	public static Boolean oneOrTwo( String type,String value){
		boolean result=false;
		
		if(value.equals("1") || value.equals("2"))
			result=true;
		else
			System.out.println(type+ "��ֵֻ��ȡ1��2��");
		return result;
	}
}
