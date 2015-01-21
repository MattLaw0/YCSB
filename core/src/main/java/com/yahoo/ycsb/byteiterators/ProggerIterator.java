package com.yahoo.ycsb.byteiterators;
/**
 * Abstract iterator class for Progger field types to inherit
 * 
 * @author mjl61
 */
public abstract class ProggerIterator extends ByteIterator {
		
	protected byte[] buf;
	protected int bufOff;
	protected int len;
	
	@Override
	public boolean hasNext() {
		return bufOff < len;
	}

	@Override
	public byte nextByte() {
		bufOff++;
		return buf[bufOff-1];
	}

	@Override
	public long bytesLeft() {
		return len - bufOff;
	}
}
