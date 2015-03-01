// Robot ARM Constants

package org.usfirst.frc330.constants;

public final class ArmPos {
	
	// TODO: Units needed for each value
	
	private ArmPos(){}
	
	// Tolerance
	public static final double tolerance       = 0.5;
	
	// PID Constants
	public static final double proportional    = 0.06;  //AP 2-25 (was 0.05) WARNING: Changing this may cause self-destruct in auto
	public static final double integral        = 0.0;
	public static final double derivitive      = 0.0;
	public static final double feedForward	   = 2.5;  //AP 2-28
	
	// Analog Pot Limits- Defaults
	public static final double frontLimit      = 1.0;
	public static final double verticalLimit   = 3.0;
	public static final double rearLimit       = 4.0;
	
	//Calibration Angles (Relative to Mast)
	public static final double frontCalAngle = 			49.0; //Angle from the mast     AP 2-14
	public static final double verticalCalAngle = 		180.0; //Angle from the mast
	public static final double rearCalAngle  = 			311.0; //Angle from the mast     AP 2-14
	
	//Angles
	//public static final double frontWheelLimit =			-43.0; // AP 2-24
	public static final double frontLimitAngle =			-40.0; // JR 2-22
	public static final double frontFlipStart =				29.0;	// AP 2-24
	public static final double rearStateFrontLimitAngle = 	78.6; // AP 2-26
	public static final double verticalAngle = 	  			 90.0;  //AP 2-26
	public static final double frontStateRearLimitAngle =	105.6;  //AP 2-14
	public static final double rearFlipStart =				150.0;	// AP 2-24
	public static final double rearLimitAngle = 			222.5;  //AP 2-14
	//public static final double rearWheelLimit =				242.0; // AP 2-24
	
	
	//Lengths
	//public static final double mastLength      = 22.0; 	//inches
	//public static final double armLength       = 44.0; 	//inches
	
	//Current
	public static final double currentLowerLimit = -50;
	public static final double currentUpperLimit = 50;
}	