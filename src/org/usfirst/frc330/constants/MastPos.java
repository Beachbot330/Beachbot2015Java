// Robot Mast constants
// written by Autumn

package org.usfirst.frc330.constants;

public final class MastPos {
	
	// TODO: Units needed for each value
	
	// Tolerance
	public static final double tolerance       = 0.0;
	
	//DrivePower
	public static final double softDrive		= 0.1;  //AP & JR <2-25
	public static final double maxOutput		= 0.8;  //AP 2-25
	
	// PID Constants
	public static final double proportional    = 0.2;  //AP 2-25
	//public static final double integral        = 0.05;  //AP 2-25
	//public static final double proportional    = 0.1;  //
	public static final double integral        = 0.0;  //
	public static final double derivitive      = 0.0;

	// Limits Defaults
	public static final double frontLimit      = 1.158;  //AP 2-14
	public static final double rearLimit       = -1.0;

	// Positions
	public static final double vertical        = 90.0; //AP 2-25
	
	// Angles
	public static final double frontLimitAngle = 90.0;  //AP 2-14
	public static final double rearLimitAngle  = 151.0;  //AP 2-25
	
	//Current
	public static final double currentUpperLimit = 50;
	public static final double currentLowerLimit = -50;
}
