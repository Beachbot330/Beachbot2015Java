// Robot ARM Constants

package org.usfirst.frc330.constants;

public final class HandConst {
	
	private HandConst(){}
	
	// PID Constants
	public static final double proportional    = 0.0;
	public static final double integral        = 0.0;
	public static final double derivitive      = 0.0;
	
	// Vertical Hand
	public static final double defaultVerticalHandAngle = -90;
	public static final double vertAdjustRate 			= .2;
	
	// Current
	public static final double currentLowerLimit = -50;
	public static final double currentUpperLimit = 50;
	
	// Limits Defaults
	public static final double frontLimit      = 1.0;
	public static final double rearLimit       = -1.0;
}	