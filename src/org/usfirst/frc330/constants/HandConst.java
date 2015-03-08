// Robot ARM Constants

package org.usfirst.frc330.constants;

public final class HandConst {
	
	private HandConst(){}
	
	// Tolerance
	public static final double tolerance       = 0.1;
	
	// PID Constants
	public static final double proportional    = 0.045; //needed to turn this up during auto programming AP 2-24 (was 0.02)
	public static final double integral        = 0.0;
	public static final double derivitive      = 0.0;
	public static final double feedForward     = 4.0;	//value to divide arm by to set wrist speed (feedForward)
	
	// Vertical Hand
	public static final double defaultFrontVerticalHandAngle = -90;
	public static final double defaultRearVerticalHandAngle = 270;
	public static final double vertAdjustRate 			= 10;
	
	// Current
	public static final double currentLowerLimit = -50;
	public static final double currentUpperLimit = 50;
	
	// Limits Defaults
	public static final double frontLimit      = 1.0;
	public static final double rearLimit       = -1.0;
	
	//Angles
	public static final double frontLimitAngle = 72;
	public static final double rearLimitAngle  = 285.0;
	
	//Lengths
	//public static final double handLength       = 20.5; 	//inches  AP 3-7
	public static final double handLength       = 20.5 - 0.4;
	
	public static final double handHeightLimit = 76.5;
	
}	
