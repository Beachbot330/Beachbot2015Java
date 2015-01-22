package org.usfirst.frc330.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.wpi.first.wpilibj.Timer;

public class Logger {
	private File roboRIOFile, usbFile;
	private BufferedWriter roboRIOWriter, usbWriter;
	private String m_roboRIOPath, m_usbPath, m_filePrefix;
	private GregorianCalendar calendar = new java.util.GregorianCalendar();
	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	private Date date;
	boolean usbWorking = true;
	
	
	public Logger(String roboRIOPath, String usbPath, String filePrefix) {
		m_roboRIOPath = roboRIOPath;
		m_usbPath = usbPath;
		m_filePrefix = filePrefix;
		
		
    	calendar.setTimeInMillis(System.currentTimeMillis());
    	date = calendar.getTime();

		roboRIOFile = new File(m_roboRIOPath + "/" + m_filePrefix + "_" + sdf.format(System.currentTimeMillis()));
		usbFile = new File(m_usbPath + "/" + m_filePrefix + "_" + sdf.format(System.currentTimeMillis()));
		System.out.println(sdf.format(date));
		
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
	
	public Logger() {
		this("/home/lvuser", "/media/sda1", "BB2015_Log");
	}
	
	public void updateDate() {
		boolean success;
		if (calendar.get(calendar.YEAR) < 2015) {
			calendar.setTimeInMillis(System.currentTimeMillis() - (long)(Timer.getFPGATimestamp()*1000));
			date = calendar.getTime();
			
			if (calendar.get(calendar.YEAR) >= 2015) {
				File tempFile = new File(m_roboRIOPath + "/" + m_filePrefix + "_" + sdf.format(date));
				System.out.println(sdf.format(date));
				success = roboRIOFile.renameTo(tempFile);
				System.out.println("RoboRIO File Renamed: " + success);
				tempFile = new File(m_usbPath + "/" + m_filePrefix + "_" + sdf.format(date));
				success = usbFile.renameTo(tempFile);
				usbWorking &= success;
				System.out.println("USB File Renamed: " + success);
			}
		}
	}
	
	public void println(String data) {
		data = sdf.format(System.currentTimeMillis()) + " "  + data + "\r\n";
		if (usbWorking) {
	    	try {
				usbWriter.write(data);
				usbWriter.flush();
			} catch (IOException e) {
				usbWorking = false;
				e.printStackTrace();
			}
		}
		else {
			try {
				roboRIOWriter.write(data);
				roboRIOWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
}
