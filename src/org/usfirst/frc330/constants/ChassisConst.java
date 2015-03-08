// Robot Chassic Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.wpilibj.PIDGains;

public final class ChassisConst {
	private ChassisConst(){}
	
	// PID MaxOutputs
	public static final double backupThrottle   = 0.5;
	public static final double defaultMaxOutput = 0.8;
	public static final double defaultMaxOutputStep = 0.1;
	
	
	//Encoder Distance Constants
    public static final double wheelDiameter = 6;
    public static final double pulsePerRevolution = 360;
    public static final double practicePulsePerRevolution = 250;
    public static final double encoderGearRatio = 3;
    public static final double gearRatio = 64.0/20.0;
    public static final double Fudgefactor = 1.0;
    
    //Turn Gyro 
    public static final double rotateProportional = 0.11;
    
    public static final PIDGains DriveLow = new PIDGains(0.1,0,0,0,0.8,defaultMaxOutputStep);
    public static final PIDGains DriveHigh = new PIDGains(0.04,0,0,0,0.8,defaultMaxOutputStep);
    public static final PIDGains GyroLow = new PIDGains(0.2,0,0,0,1,1);
    public static final PIDGains GyroHigh = new PIDGains(0.15,0,0,0,1,1);
}