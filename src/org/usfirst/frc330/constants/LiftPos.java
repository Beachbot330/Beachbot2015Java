package org.usfirst.frc330.constants;

public final class LiftPos {
	private LiftPos(){}

	// Tolerance
	public static final double tolerance =			 0.1;
	
	// PID Constants
	public static final double proportional =		 0.0;
	public static final double integral =			 0.0;
	public static final double derivitive =			 0.0;
	
	// Values are in inches from the ground
	public static final double dropOff = 		5.0;
	public static final double carry =			15.0;
	public static final double load2 = 			22.0;
	public static final double intake = 		35.0;
	public static final double stack3 = 		39.0;
	
	public static final double lowerLimit = 	-1.0;
	public static final double upperLimit = 	44.0;
	
	public static final double encoderTurns =   12.5;
	public static final double liftLength   =   45.0;
	public static final int encoderPPR      =   64;
	
	//Current
	public static final double currentLowerLimit = -50;
	public static final double currentUpperLimit = 50;
}