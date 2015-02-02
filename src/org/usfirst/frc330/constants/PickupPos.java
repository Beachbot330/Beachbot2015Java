package org.usfirst.frc330.constants;

public final class PickupPos {
	private PickupPos(){}

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
	public static final double stack3 = 		55.0;
	
	public static final double lowerLimit = 	3.0;
	public static final double upperLimit = 	60.0;
}