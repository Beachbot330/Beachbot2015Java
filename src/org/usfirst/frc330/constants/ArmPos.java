package org.usfirst.frc330.constants;

public final class ArmPos {
	private ArmPos(){}
	
	// Tolerance
	public static final double tolerance =				 0.1;
	
	// PID Constants
	public static final double proportional =			 0.0;
	public static final double integral =				 0.0;
	public static final double derivitive =				 0.0;
	
	// Limits Defaults
	public static final double frontLimit = 			 1.0;
	public static final double rearLimit = 				-1.0;
	public static final double mastFrontLimit = 		 1.0;
	public static final double mastRearLimit = 			-1.0;
	
	
	//Angles
	public static final double frontLimitAngle = 		-30.0;
	public static final double rearLimitAngle = 		210.0;
	public static final double frontMastLimitAngle = 	 90.0;
	public static final double rearMastLimitAngle = 	120.0;
	public static final double vertical =		 		  0.0;
	
	//Lengths
	public static final double mastLength = 			 22.0; 	//inches
	public static final double armLength = 				 44.0; 	//inches
}	