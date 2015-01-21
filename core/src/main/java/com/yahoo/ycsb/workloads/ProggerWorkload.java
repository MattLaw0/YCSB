package com.yahoo.ycsb.workloads;

import java.io.*;
import java.util.*;

import com.yahoo.ycsb.ProggerRecord;
import com.yahoo.ycsb.Utils;
import com.yahoo.ycsb.WorkloadException;
import com.yahoo.ycsb.byteiterators.*;

/**
 * Custom workload implementation designed to be more representative of a real progger workload.
 * Analysis data is generated via parser.py, and a text file output is fed into YCSB via
 * PROGGER_FILE_PROPERTY.<p>
 * Currently all changes are done to the generation process of custom records to load/insert 
 * into the DBMS. </p>
 * </p>
 * @author mjl61
 *
 */
public class ProggerWorkload extends CoreWorkload {

	public static final String PROGGER_FILE_PROPERTY = "proggersummary";
	public static final String PROGGER_FILE_PROPERTY_DEFAULT = "syscall_summary.txt";
	
	public static final int MAXINT = 1000000;	// total value of cumulative distribution
	
	ArrayList<ProggerRecord> proggerRecords = new ArrayList<ProggerRecord>();
	HashMap<String, ProggerRecord> recordMap = new HashMap<String, ProggerRecord>();
	
	@Override
	public void init(Properties p) throws WorkloadException {
		String proggerFilename = p.getProperty(PROGGER_FILE_PROPERTY, PROGGER_FILE_PROPERTY_DEFAULT); 
		try{
			File file = new File(proggerFilename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null){
				String[] spl = line.split(" ");
				String key = spl[0];
				int val = Integer.parseInt(spl[1]);
				int n = Integer.parseInt(spl[2]);
				int[] fields = new int[n];
				String[] types = new String[n];
				for (int i=0; i<n; i++){
					fields[i] = Integer.parseInt(spl[i+3]);
					types[i] = spl[n+i+3];
				}
				ProggerRecord pr = new ProggerRecord(key, val, fields, types);
				proggerRecords.add(pr);
				recordMap.put(key, pr);
			}
			reader.close();
		}catch (Exception e){
			throw new WorkloadException("Exception loading the progger file: " + proggerFilename, e);
		}
		super.init(p);
	}
	
	@Override
	HashMap<String, ByteIterator> buildValues(){
		HashMap<String,ByteIterator> values=new HashMap<String,ByteIterator>();
		ProggerRecord pr = getRandomRecord();
		int[] fieldArr = pr.getFieldArray();
		String[] typeArr = pr.getTypeArray();
 		for (int i=0; i<fieldArr.length; i++)
 		{
 			String fieldkey = fieldnameprefix + i;
 			ByteIterator data = getIterator(typeArr[i], fieldArr[i]);
 			values.put(fieldkey,data);
 		}
		return values;
	}
	
	private static ByteIterator getIterator(String type, int len){
		switch (type){
		case "date":
			return new DateByteIterator(len);
		case "int":
			return new IntByteIterator(len);
		default:
			return new RandomByteIterator(len);
		}
	}
	
	/**
	 * Chooses a random record based on the supplied probability distribution
	 */
	private ProggerRecord getRandomRecord(){
		int val = Utils.random().nextInt(MAXINT);
		
		for (int i=0; i<proggerRecords.size(); i++){
			if (val < proggerRecords.get(i).getVal()){
				return proggerRecords.get(i);
			}
		}
		System.err.println("Couldn't find a progger record for: " + val + ". This is bad. Check that MAXINT == the last value in the progger file.");
		return null;
	}
}
