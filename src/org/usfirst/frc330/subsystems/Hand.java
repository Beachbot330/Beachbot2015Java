// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ArmPos;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.wpilibj.DualSpeedController;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Hand extends Subsystem {
	double verticalHandAngle = HandConst.defaultVerticalHandAngle;
	boolean handLevelActivated = false;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DoubleSolenoid handLeft = RobotMap.handhandLeft;
    DoubleSolenoid handCenter = RobotMap.handhandCenter;
    DoubleSolenoid handRight = RobotMap.handhandRight;
    SpeedController wristLeft = RobotMap.handwristLeft;
    SpeedController wristRight = RobotMap.handwristRight;
    DualSpeedController wrist = RobotMap.handwrist;
    AnalogInput wristPot = RobotMap.handwristPot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Hand() {
    	super();
    	
    	/////////////////////////////////////////////////////////////////
    	// LOG IT!
    	
    	CSVLoggable temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getWristLeftCurrent(); }
    	};
    	Robot.csvLogger.add("WristLeftCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getWristRightCurrent(); }
    	};
    	Robot.csvLogger.add("WristRightCurrent", temp);
    	
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new handLevel());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void setWrist(double output){
        if (output > 0 && Robot.powerDP.getWristLeftCurrent() < HandConst.currentLowerLimit)
        {
        	wrist.set(0);
        }
        else if (output < 0 && Robot.powerDP.getWristLeftCurrent() > HandConst.currentUpperLimit)
        {
        	wrist.set(0);
        }
        else if (output > 0 && Robot.powerDP.getWristRightCurrent() < HandConst.currentLowerLimit)
        {
        	wrist.set(0);
        }
        else if (output < 0 && Robot.powerDP.getWristRightCurrent() > HandConst.currentUpperLimit)
        {
        	wrist.set(0);
        }
        else
        {
        	wrist.set(output);
        }
    }
    
    
    public void setAngle(double angle)
    {
    	//TODO: Finish Implementation
    	//double sensorRange = getHandRearLimit() - getHandFrontLimit();
    	//double angleRange = HandConst.rearLimitAngle - HandConst.frontLimitAngle;
    	//double angleFromArm = sensorRange/angleRange * wristPot.getAverageVoltage()- getHandFrontLimit()) + HandConst.frontLimitAngle;
    }
    
    public double getAngle(){
    	//Todo: implement
    	return 0.0;
    }

	public double getVerticalHandAngle() {
		return verticalHandAngle;
	}

	public void setVerticalHandAngle(double verticalHandAngle) {
		this.verticalHandAngle = verticalHandAngle;
	}
	
	// ///////////////////////////////////////
	// Hand (Grabber) Open and Close Functions
	// ///////////////////////////////////////
	public void closeHandLeft()
	{
		handLeft.set(DoubleSolenoid.Value.kForward);
	}
	
	public void openHandLeft()
	{
		handLeft.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeHandRight()
	{
		handRight.set(DoubleSolenoid.Value.kForward);
	}
	
	public void openHandRight()
	{
		handRight.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeHandCenter()
	{
		handCenter.set(DoubleSolenoid.Value.kForward);
	}
	
	public void openHandCenter()
	{
		handCenter.set(DoubleSolenoid.Value.kReverse);
	}

	// //////////////////////////////////////
	// Hand Leveling Code
	// //////////////////////////////////////
	public boolean isHandLevelActivated() {
		return handLevelActivated;
	}

	public void setHandLevelActivated(boolean handLevelActivated) {
		this.handLevelActivated = handLevelActivated;
	}

	public void stopHand() 
	{
		
		
	}
}

