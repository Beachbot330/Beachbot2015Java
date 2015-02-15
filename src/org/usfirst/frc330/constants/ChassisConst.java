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
	
	public static final double gyroProportionalLow  = 0.0;
	public static final double gyroIntegralLow      = 0.0;
	public static final double gyroDerivitiveLow    = 0.0;
	
	public static final double gyroProportionalHigh = 0.0;
	public static final double gyroIntegralHigh     = 0.0;
	public static final double gyroDerivitiveHigh   = 0.0;
	
	//Enocoder Distance Constants
    public static final double wheelDiameter = 6;
    public static final double PulseperRevolution = 360;
    public static final double encoderGearRatio = 3;
    public static final double gearRatio = 64.0/20.0;
    public static final double Fudgefactor = 1.0;
}