package com.yahoo.ycsb;

/**
 * Class to represent a syscall "record" - distribution of field lengths and types
 * specified in the user supplied PROGGER_FILE_PROPERTY file.
 * Val refers to the cumulative probability of this syscall.
 * 
 * @author mjl61
 */
public class ProggerRecord {

	private String syscall;
	private int val;
	private int[] fieldArr;
	private String[] typeArr;
	
	public ProggerRecord(String s, int v, int[] fields, String[] types){
		syscall = s;
		val = v;
		fieldArr = fields;
		typeArr = types;
	}
	
	public String getSyscall(){
		return syscall;
	}
	
	public int getVal(){
		return val;
	}
	
	public int[] getFieldArray(){
		return fieldArr;
	}
	
	public String[] getTypeArray(){
		return typeArr;
	}
}
