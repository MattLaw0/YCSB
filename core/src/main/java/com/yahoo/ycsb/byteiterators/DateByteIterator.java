package com.yahoo.ycsb.byteiterators;

import java.util.Date;

import com.yahoo.ycsb.Utils;

/**
 * Iterator class for date fields. Date is generated (from current time) and stored
 * into byte array. The division of 1000 is so the date can be stored as 4 byte int.
 * 
 * @author mjl61
 */
public class DateByteIterator extends ProggerIterator {

	public DateByteIterator(int len){
		this.len = len;
		int time = (int)(new Date().getTime() / 1000);
		buf = Utils.getIntBytes(time, len);
	}
}
