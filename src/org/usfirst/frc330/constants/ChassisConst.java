// Robot Chassic Constants

package org.usfirst.frc330.constants;

public final class ChassisConst {
	private ChassisConst(){}
	
	//PID Names
	public static final String DriveLowName     = "LowGear";
	public static final String DriveHighName    = "HighGear";
	
	// PID MaxOutputs
	public static final double backupThrottle   = 0.5;
	public static final double defaultMaxOutput = 0.8;
	
	// PID Constants
	public static final double proportionalLow  = 0.0;
	public static final double integralLow      = 0.0;
	public static final double derivitiveLow    = 0.0;
	
	public static final double proportionalHigh = 0.0;
	public static final double integralHigh     = 0.0;
	public static final double derivitiveHigh   = 0.0;
}