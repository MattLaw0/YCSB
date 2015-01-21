package com.yahoo.ycsb.byteiterators;

import com.yahoo.ycsb.Utils;

/**
 * Iterator class for int fields. An integer value is generated and stored 
 * into a byte array of specified length.
 * 
 * @author mjl61
 */
public class IntByteIterator extends ProggerIterator {
	
	public IntByteIterator(int len){
		this.len = len;
		this.buf = randomIntBytes(len);
	}
	
	private static byte[] randomIntBytes(int numBytes){
		return Utils.getIntBytes(Utils.random().nextInt(), numBytes);
	}
	
}
