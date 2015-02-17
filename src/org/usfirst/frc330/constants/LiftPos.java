package org.usfirst.frc330.constants;

public final class LiftPos {
	private LiftPos(){}

	// Tolerance
	public static final double tolerance =			 1.0;		//JR, SP 2-15
	
	// PID Constants
	public static final double proportional =		 0.3;		//AP, SP 2-14
	public static final double integral =			 0.000;		//AP, SP 2-14
	public static final double derivitive =			 0.001;		//AP, SP 2-14
	
	public static final double lowerLimit = 	0.25;   //JR, SP 2-15
	public static final double upperLimit = 	37.0;   //AP, SP 2-14
	
	// Values are in inches from the physical hardstop
	public static final double dropOff = 		lowerLimit; //JR 2-15
	public static final double carry =			4.1;  //JR 2-15
	public static final double load2 = 			11.2; //JR 2-15
	public static final double intake = 		33.0; //JR 2-15
	public static final double stack3 = 		39.0;
	
	public static final double bottomLimitHeight = 0; //JR 2-14
	public static final double topLimitHeight = 38.5; //JR 2-14
	
	//Current
	public static final double currentLowerLimit = -30; //JR 2-16 highest current seen was about 18amps
	public static final double currentUpperLimit = 30;  //JR 2-16
}