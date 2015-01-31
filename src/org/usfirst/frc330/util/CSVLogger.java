package org.usfirst.frc330.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.usfirst.frc330.Robot;

public class CSVLogger {
	LinkedHashMap<String,CSVLoggable> table;
	
	private File roboRIOFile, usbFile;
	private BufferedWriter roboRIOWriter, usbWriter;
	private String m_roboRIOPath, m_usbPath, m_filePrefix;
	private GregorianCalendar calendar = new java.util.GregorianCalendar();
	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
	private Date date;
	boolean usbWorking = true;
	
	public CSVLogger(String roboRIOPath, String usbPath, String filePrefix) {
		table = new LinkedHashMap<String,CSVLoggable>();
		m_roboRIOPath = roboRIOPath;
		m_usbPath = usbPath;
		m_filePrefix = filePrefix;
		
		
    	calendar.setTimeInMillis(System.currentTimeMillis());
    	date = calendar.getTime();

		roboRIOFile = new File(m_roboRIOPath + "/" + m_filePrefix + "_" + sdf.format(System.currentTimeMillis()));
		usbFile = new File(m_usbPath + "/" + m_filePrefix + "_" + sdf.format(System.currentTimeMillis()));
		System.out.println("CSV Date: " + sdf.format(date));
		
		try {
			usbWriter = new BufferedWriter(new FileWriter(usbFile));
		} catch (IOException e) {
			usbWorking = false;
			e.printStackTrace();
		}
		try {
			roboRIOWriter = new BufferedWriter(new FileWriter(roboRIOFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public CSVLogger() {
		this("/home/lvuser", "/media/sda1", "BB2015_CSV");
	}
	
	public void add(String name, CSVLoggable data) {
		table.put(name, data);
	}

	public void writeHeader() {
		String header = "Time, ";
		for (String key : table.keySet()) {
			header = header + key + ", ";
		}
		header = header + "\r\n";
		
		if (usbWorking) {
	    	try {
				usbWriter.write(header);
			} catch (IOException e) {
				usbWorking = false;
				e.printStackTrace();
			}
		}
		else {
			try {
				roboRIOWriter.write(header);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
	
	String data;
	public void writeData() {
		data = sdf.format(System.currentTimeMillis()) + ", ";
		for (CSVLoggable key : table.values()) {
			data = data + key.get() + ", ";
		}
		data = data + "\r\n";
		
		if (usbWorking) {
	    	try {
				usbWriter.write(data);
			} catch (IOException e) {
				usbWorking = false;
				e.printStackTrace();
			}
		}
		else {
			try {
				roboRIOWriter.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
	
}
