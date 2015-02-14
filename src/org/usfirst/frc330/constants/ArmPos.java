// Robot ARM Constants

package org.usfirst.frc330.constants;

public final class ArmPos {
	
	// TODO: Units needed for each value
	
	private ArmPos(){}
	
	// Tolerance
	public static final double tolerance       = 0.1;
	
	// PID Constants
	public static final double proportional    = 0.0;
	public static final double integral        = 0.0;
	public static final double derivitive      = 0.0;
	
	// Analog Pot Limits- Defaults
	public static final double frontLimit      = 1.0;
	public static final double rearLimit       = -1.0;
	
	//Angles
	public static final double frontLimitAngle = 			-30.0;
	public static final double rearStateFrontLimitAngle = 	 20.0;
	public static final double verticalAngle = 	  			 90.0;
	public static final double frontStateRearLimitAngle =	120.0;
	public static final double rearLimitAngle  = 			210.0;
	
	
	//Lengths
	//public static final double mastLength      = 22.0; 	//inches
	//public static final double armLength       = 44.0; 	//inches
	
	//Current
	public static final double currentLowerLimit = -50;
	public static final double currentUpperLimit = 50;
}	