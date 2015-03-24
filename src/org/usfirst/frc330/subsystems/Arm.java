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

import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.commands.ManualArm;
import org.usfirst.frc330.constants.ArmPos;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.constants.MastPos;
import org.usfirst.frc330.wpilibj.DualSpeedController;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {
	
	protected PIDController armPID;
	boolean isFront = true;
	boolean switchingSides = false;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    AnalogInput armPot = RobotMap.armarmPot;
    SpeedController armLeft = RobotMap.armarmLeft;
    SpeedController armRight = RobotMap.armarmRight;
    DualSpeedController arm = RobotMap.armarm;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ManualArm());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Arm PIDController source and output objects
    public PIDOutput armPIDOutput = new PIDOutput()  {
    	public void pidWrite(double output) {
    		setArm(output - (Robot.mast.getMastOutput()-MastPos.softDrive)/ArmPos.feedForward);
    	}
    };
    
    public PIDSource armPIDSource = new PIDSource() {
    	public double pidGet() {
    		return getArmAngle();
    	}
    };
    

    
    /////////////////////////////////////////////////////////////
    // MAIN CLASS
    /////////////////////////////////////////////////////////////
	public Arm() {
        super();
        
        // Arm PIDController object
        armPID = new PIDController(ArmPos.proportional,
        						   ArmPos.integral,
        						   ArmPos.derivitive,armPIDSource,armPIDOutput, 0.01);
        armPID.setAbsoluteTolerance(ArmPos.tolerance);
    
        // Add to Smart Dashboard
        SmartDashboard.putData("ArmPID", armPID);
        SmartDashboard.putBoolean("ArmOverride", false);
        
        /////////////////////////////////////////////////////////////////
        // LOG IT!
        
        // Log limits from Arm and Mast
        CSVLoggable temp = new CSVLoggable() {
			public double get() { return getArmFrontLimit(); }
    	};
    	Robot.csvLogger.add("ArmFrontLimit", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getArmRearLimit(); }
    	};
    	Robot.csvLogger.add("ArmRearLimit", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getArmAngle(); }
    	};
    	Robot.csvLogger.add("ArmAngle", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getArmPotAvgVoltage(); }
    	};
    	Robot.csvLogger.add("ArmPotAvgVoltage", temp);
    		
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getArmLeftCurrent(); }
    	};
    	Robot.csvLogger.add("ArmLeftCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getArmRightCurrent(); }
    	};
    	Robot.csvLogger.add("ArmRightCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return armLeft.get(); }
    	};
    	Robot.csvLogger.add("ArmLeftOutput", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return armRight.get(); }
    	};
    	Robot.csvLogger.add("ArmRightOutput", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return getArmSetpoint(); }
    	};
    	Robot.csvLogger.add("ArmSetpoint", temp);
    	
    }
    
	/////////////////////////////////////////////////////////////
	// SET methods
	/////////////////////////////////////////////////////////////
	public void setIsFront(boolean isFront) {
		this.isFront = isFront;
	}
	
	// TODO: Check the logic
    public void setArm(double output){
        if (getArmAngle() > ArmPos.frontStateRearLimitAngle && output > 0 && this.getIsFront() == true && isSwitchingSides() == false)
        	arm.set(0);
        else if (getArmAngle() < ArmPos.frontLimitAngle && output < 0 && this.getIsFront() == true)
        	arm.set(0);
        else if (getArmAngle() > ArmPos.rearLimitAngle && output > 0 && this.getIsFront() == false)
        	arm.set(0);
        else if (getArmAngle() < ArmPos.rearStateFrontLimitAngle && output < 0 && this.getIsFront() == false && isSwitchingSides() == false)
        	arm.set(0); 
        else if (output > 0 && Robot.powerDP.getArmLeftCurrent() < ArmPos.currentLowerLimit)
            arm.set(0);
        else if (output < 0 && Robot.powerDP.getArmLeftCurrent() > ArmPos.currentUpperLimit)
            arm.set(0);
        else if (output > 0 && Robot.powerDP.getArmRightCurrent() < ArmPos.currentLowerLimit)
            arm.set(0);
        else if (output < 0 && Robot.powerDP.getArmRightCurrent() > ArmPos.currentUpperLimit)
            arm.set(0);
        else
            arm.set(output);
    }


	
	public void setArmAngle(double position)
    {
    	armPID.setSetpoint(position);
    }
    

    
    public void setArmFrontLimit()
	{        
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeArmFrontLimit";
        else
            name = "CompetitionArmFrontLimit";
        
        Preferences.getInstance().putDouble(name, armPot.getAverageVoltage());
        Preferences.getInstance().save();
    }
	
	public void setArmRearLimit()
	{
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeArmRearLimit";
        else
            name = "CompetitionArmRearLimit";
        
        Preferences.getInstance().putDouble(name, armPot.getAverageVoltage());
        Preferences.getInstance().save();
	}
	
	public void setArmVertical()
	{
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeArmVertical";
        else
            name = "CompetitionArmVertical";
        
        Preferences.getInstance().putDouble(name, armPot.getAverageVoltage());
        Preferences.getInstance().save();
	}
	
	
	/////////////////////////////////////////////////////////////
	// GET methods
	/////////////////////////////////////////////////////////////	
    public boolean getIsFront() {
		return isFront;
	}
	
	public double getArmAngle()
    {
		double angleFromMast;
		if(armPot.getAverageVoltage() < getArmVertical()) //if arm is frontside
		{
	    	double sensorRange = getArmVertical() - getArmFrontLimit();
	    	double angleRange  = ArmPos.verticalCalAngle - ArmPos.frontCalAngle;
	    	angleFromMast = (armPot.getAverageVoltage() - getArmVertical()) * (angleRange/sensorRange);
		}
		else
		{
			double sensorRange = getArmRearLimit() - getArmVertical();
	    	double angleRange  = ArmPos.rearCalAngle - ArmPos.verticalCalAngle;
	    	angleFromMast = (armPot.getAverageVoltage() - getArmVertical()) * (angleRange/sensorRange);	
		}
		double angleFromHorizon = Robot.mast.getMastAngle() + angleFromMast;
	    return angleFromHorizon;
    }

    public double getArmFrontLimit() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeArmFrontLimit";
        else
            name = "CompetitionArmFrontLimit";
		return Preferences.getInstance().getDouble(name, ArmPos.frontLimit);
	}
	
	public double getArmRearLimit() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeArmRearLimit";
        else
            name = "CompetitionArmRearLimit";
		return Preferences.getInstance().getDouble(name, ArmPos.rearLimit);
	}
	
	public double getArmVertical() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeArmVertical";
        else
            name = "CompetitionArmVertical";
		return Preferences.getInstance().getDouble(name, ArmPos.verticalLimit);
	}
	
	public double getArmPotAvgVoltage() {
		return armPot.getAverageVoltage();
	}
	
	// CSVLogger interface expects a double
	// Solenoid get() returns a boolean
	// Map it
	//    1.0 == TRUE
	//    0.0 == FALSE
	// TODO: get() in CSVlogger should take any value
	
	
	public double getArmOutput() {
		return arm.get();
	}

	/////////////////////////////////////////////////////////////
	// PID Stuff
	/////////////////////////////////////////////////////////////
	public void setArmAbsoluteTolerance(double absvalue) {
		armPID.setAbsoluteTolerance(absvalue);
	}

    public synchronized double getArmSetpoint() {
        return armPID.getSetpoint();
    }
    
    // Method to check if Arm is on target
    public synchronized boolean onArmTarget() {
        return armPID.onTarget();
    }
  
    // Method returns if Arm is enabled
    public synchronized boolean isArmEnable() {
        return armPID.isEnable();
    }
    
    
    // Method to Enable Arm
    public synchronized void enableArm() {
        armPID.enable();
    }
    
   
    // Method to Disable Arm
    public synchronized void disableArm() {
        armPID.disable();
    }

	public void stopArm()
	{
		if (armPID.isEnable())
		{
			armPID.reset();
		}
	}
	
	public void setPIDConstants (double P, double I, double D, double F)
	{
		armPID.setPID(P, I, D, F);
	}

//	public void pidWrite(double output) {
//		set(output);		
//	}

	public double pidGet() {
		return getArmAngle();
	}
    
    //////////////////////////
    // Other Methods
    //////////////////////////
    public void manualArm() {
        double armCommand = Robot.oi.armJoystick.getY();
//        if (armCommand < 0) 
//            armCommand = -(armCommand*armCommand);
//        else
//            armCommand = armCommand*armCommand;
        if (Math.abs(armCommand) > 0.03)
        {
        	if (armPID.isEnable())
                armPID.disable();
        	if (getIsFront()) 
        		setArm(armCommand);
        	else
        		setArm(-armCommand);
        }
        else if (!armPID.isEnable())
        {
            armPID.setSetpoint(this.getArmAngle());
            armPID.enable();
        } 
    }
    
    public synchronized boolean isEnable() {
        return armPID.isEnable();
    }

	public boolean isSwitchingSides() {
		return switchingSides;
	}

	public void setSwitchingSides(boolean switchingSides) {
		this.switchingSides = switchingSides;
	}
		
}

