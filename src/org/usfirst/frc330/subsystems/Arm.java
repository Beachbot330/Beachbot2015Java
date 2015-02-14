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
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.constants.MastPos;
import org.usfirst.frc330.wpilibj.DualSpeedController;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem implements PIDSource, PIDOutput{ //TODO: is this supposed to be a PIDsource and output? How does that work with 2 PIDs?
	
	protected PIDController armPID;
	protected PIDController mastPID;
	
	boolean isFront;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    AnalogInput mastPot = RobotMap.armmastPot;
    AnalogInput armPot = RobotMap.armarmPot;
    SpeedController mastRight = RobotMap.armmastRight;
    SpeedController mastLeft = RobotMap.armmastLeft;
    DualSpeedController mast = RobotMap.armmast;
    SpeedController armLeft = RobotMap.armarmLeft;
    SpeedController armRight = RobotMap.armarmRight;
    DualSpeedController arm = RobotMap.armarm;
    DoubleSolenoid mastLock = RobotMap.armmastLock;

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
    		setArm(output);	
    	}
    };
    
    public PIDSource armPIDSource = new PIDSource() {
    	public double pidGet() {
    		return getArmAngle();
    	}
    };
    
    // Mast PIDController source and output objects
    public PIDOutput mastPIDOutput = new PIDOutput()  {
    	public void pidWrite(double output) {
    		setMast(output);	
    	}
    };
    
    public PIDSource mastPIDSource = new PIDSource() {
    	public double pidGet() {
    		return getMastAngle();
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
        						   ArmPos.derivitive,armPIDSource,armPIDOutput);
        armPID.setAbsoluteTolerance(ArmPos.tolerance);
        
        // Mast PIDController object
        mastPID = new PIDController (MastPos.proportional,
        							 MastPos.integral,
        							 MastPos.derivitive,mastPIDSource,mastPIDOutput);
        mastPID.setAbsoluteTolerance(MastPos.tolerance);
        
        // Add to Smart Dashboard
        SmartDashboard.putData("ArmPID", armPID);
        SmartDashboard.putBoolean("ArmOverride", false);
        
        SmartDashboard.putData("MastPID", mastPID);
        SmartDashboard.putBoolean("MastOverride", false);
        
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
    	
    	temp = new CSVLoggable() {
			public double get() { return getMastFrontLimit(); }
    	};
    	Robot.csvLogger.add("MastFrontLimit", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getMastRearLimit(); }
    	};
    	Robot.csvLogger.add("MastRearLimit", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getArmAngle(); }
    	};
    	Robot.csvLogger.add("ArmAngle", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getMastAngle(); }
    	};
    	Robot.csvLogger.add("MastAngle", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getMastLock(); }
    	};
    	Robot.csvLogger.add("MastLockSolenoid", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getArmPotAvgVoltage(); }
    	};
    	Robot.csvLogger.add("ArmPotAvgVoltage", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getMastPotAvgVoltage(); }
    	};
    	Robot.csvLogger.add("MastPotAvgVoltage", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getArmLeftCurrent(); }
    	};
    	Robot.csvLogger.add("ArmLeftCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getArmRightCurrent(); }
    	};
    	Robot.csvLogger.add("ArmRightCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getMastLeftCurrent(); }
    	};
    	Robot.csvLogger.add("MastLeftCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getMastRightCurrent(); }
    	};
    	Robot.csvLogger.add("MastRightCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return armLeft.get(); }
    	};
    	Robot.csvLogger.add("ArmLeftOutput", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return armRight.get(); }
    	};
    	Robot.csvLogger.add("ArmRightOutput", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return mastLeft.get(); }
    	};
    	Robot.csvLogger.add("MastLeftOutput", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return mastRight.get(); }
    	};
    	Robot.csvLogger.add("MastRightOutput", temp);
    	
    }
    
	/////////////////////////////////////////////////////////////
	// SET methods
	/////////////////////////////////////////////////////////////
	public void setIsFront(boolean isFront) {
		this.isFront = isFront;
	}
	
	// TODO: Check the logic
    public void setArm(double output){
        if (output > 0 && getArmAngle() > ArmPos.rearLimit) // Todo: Check this logic
        {
            arm.set(0);
        }
        else if (output < 0 && getArmAngle() < ArmPos.frontLimit)// Todo: Check this logic
        {
            arm.set(0);
        }
        else if (output > 0 && Robot.powerDP.getArmLeftCurrent() < ArmPos.currentLowerLimit)
        {
            arm.set(0);
        }
        else if (output < 0 && Robot.powerDP.getArmLeftCurrent() > ArmPos.currentUpperLimit)
        {
            arm.set(0);
        }
        else if (output > 0 && Robot.powerDP.getArmRightCurrent() < ArmPos.currentLowerLimit)
        {
            arm.set(0);
        }
        else if (output < 0 && Robot.powerDP.getArmRightCurrent() > ArmPos.currentUpperLimit)
        {
            arm.set(0);
        }
        else
        {
            arm.set(output);
            //armRight.set(-output); // Todo: check polarity and then re-enable
        }
    }

    // Set Mast
    // TODO: Check the logic
    public void setMast(double output){
        if (output > 0 && getMastAngle() > MastPos.rearLimit) // Todo: Check this logic
        {
            mast.set(0);
        }
        else if (output < 0 && getMastAngle() < MastPos.frontLimit)// Todo: Check this logic
        {
            mast.set(0);
        }
        else if (output > 0 && Robot.powerDP.getMastLeftCurrent() < MastPos.currentLowerLimit)
        {
            mast.set(0);
        }
        else if (output < 0 && Robot.powerDP.getMastLeftCurrent() > MastPos.currentUpperLimit)
        {
            mast.set(0);
        }
        else if (output > 0 && Robot.powerDP.getMastRightCurrent() < MastPos.currentLowerLimit)
        {
            mast.set(0);
        }
        else if (output < 0 && Robot.powerDP.getMastRightCurrent() > MastPos.currentUpperLimit)
        {
            mast.set(0);
        }
        else
        {
            mast.set(output);
            //mastRight.set(-output); // Todo: check polarity and then re-enable
        }
    }
	
	public void setArmAngle(double position)
    {
    	armPID.setSetpoint(position);
    }
    
    public void setMastAngle(double position)
    {
    	mastPID.setSetpoint(position);
    }
    
    public void setArmFrontLimit()
	{        
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeArmFrontLimit";
        else
            name = "CompetitionArmFrontLimit";
        
        Preferences.getInstance().putDouble(name, armPot.getAverageVoltage());
        Preferences.getInstance().save();
    }
	
	public void setArmRearLimit()
	{
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeArmRearLimit";
        else
            name = "CompetitionArmRearLimit";
        
        Preferences.getInstance().putDouble(name, armPot.getAverageVoltage());
        Preferences.getInstance().save();
	}
	
	public void setMastFrontLimit()
	{        
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeMastFrontLimit";
        else
            name = "CompetitionMastFrontLimit";
        
        Preferences.getInstance().putDouble(name, mastPot.getAverageVoltage());
        Preferences.getInstance().save();
    }
	
	public void setMastRearLimit()
	{
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeMastRearLimit";
        else
            name = "CompetitionMastRearLimit";
        
        Preferences.getInstance().putDouble(name, mastPot.getAverageVoltage());
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
    	double sensorRange = getArmRearLimit() - getArmFrontLimit();
    	double angleRange  = ArmPos.rearLimitAngle - ArmPos.frontLimitAngle;
    	double angleFromMast = angleRange/sensorRange * (armPot.getAverageVoltage() - getArmFrontLimit()) + ArmPos.frontLimitAngle;
    	double angleFromHorizon = -(180 - getMastAngle() - angleFromMast);
    	
    	return angleFromHorizon;
    }
    
    public double getMastAngle()
    {
    	double sensorRange = getMastRearLimit() - getMastFrontLimit();
    	double angleRange  = MastPos.rearLimitAngle - MastPos.frontLimitAngle;
    	
    	return angleRange/sensorRange * (mastPot.getAverageVoltage() - getMastFrontLimit()) + MastPos.frontLimitAngle;
    }
    
    public double getArmRearLimit() {
		String name;
        if (Robot.isPracticerobot())
            name = "PracticeArmFrontLimit";
        else
            name = "CompetitionArmFrontLimit";
		return Preferences.getInstance().getDouble(name, ArmPos.rearLimit);
	}
	
	public double getArmFrontLimit() {
		String name;
        if (Robot.isPracticerobot())
            name = "PracticeArmRearLimit";
        else
            name = "CompetitionArmRearLimit";
		return Preferences.getInstance().getDouble(name, ArmPos.rearLimit);
	}
	
	public double getMastRearLimit() {
		String name;
        if (Robot.isPracticerobot())
            name = "PracticeMastFrontLimit";
        else
            name = "CompetitionMastFrontLimit";
		return Preferences.getInstance().getDouble(name, MastPos.rearLimit);
	}
	
	public double getMastFrontLimit() {
		String name;
        if (Robot.isPracticerobot())
            name = "PracticeMastRearLimit";
        else
            name = "CompetitionMastRearLimit";
		return Preferences.getInstance().getDouble(name, MastPos.rearLimit);
	}
	
	public double getArmPotAvgVoltage() {
		return armPot.getAverageVoltage();
	}
	
	public double getMastPotAvgVoltage() {
		return mastPot.getAverageVoltage();
	}
	
	// CSVLogger interface expects a double
	// Solenoid get() returns a boolean
	// Map it
	//    1.0 == TRUE
	//    0.0 == FALSE
	// TODO: get() in CSVlogger should take any value
	public double getMastLock() {
		if(mastLock.get() == DoubleSolenoid.Value.kForward) 
		{
			return 1.0;
		} 
		else 
		{
			return 0.0;
		}
	}
	

	/////////////////////////////////////////////////////////////
	// PID Stuff
	/////////////////////////////////////////////////////////////
	public void setArmAbsoluteTolerance(double absvalue) {
		armPID.setAbsoluteTolerance(absvalue);
	}
    
    public void setMastAbsoluteTolerance(double absvalue) {
		mastPID.setAbsoluteTolerance(absvalue);
	}

    public synchronized double getArmSetpoint() {
        return armPID.getSetpoint();
    }
    
    public synchronized double getMastSetpoint() {
        return mastPID.getSetpoint();
    }
    
    // Methods to check if Arm or Mast are on target
    public synchronized boolean onArmTarget() {
        return armPID.onTarget();
    }
    
    public synchronized boolean onMastTarget() {
        return mastPID.onTarget();
    }
    
    // Methods return if Arm or Mast are enabled
    public synchronized boolean isArmEnable() {
        return armPID.isEnable();
    }
    
    public synchronized boolean isMastEnable() {
        return mastPID.isEnable();
    }
    
    // Methods to Enable Arm or Mast
    public synchronized void enableArm() {
        armPID.enable();
    }
    
    public synchronized void enableMast() {
        mastPID.enable();
    }
    
    // Methods to Disable Arm or Mast
    public synchronized void disableArm() {
        armPID.disable();
    }
    
    public synchronized void disableMast() {
        mastPID.disable();
    }

	public void stopArm()
	{
		if (armPID.isEnable())
		{
			armPID.reset();
		}
		if (mastPID.isEnable())
		{
			mastPID.reset();
		}
	}

	public void pidWrite(double output) {
		set(output);		
	}

	public double pidGet() {
		return getArmAngle();
	}
	
    public void set(double output){
        if (output > 0 && getArmAngle() < ArmPos.frontLimitAngle)
        {
        	arm.set(0);
        }
        else if (output < 0 && getArmAngle() > ArmPos.rearLimitAngle)
        {
        	arm.set(0);
        }
        //TODO: Update this code for the arm
//        else if (output > 0 && Robot.powerDP.getLiftLeftCurrent() < LiftPos.currentLowerLimit)
//        {
//        	arm.set(0);
//        }
//        else if (output < 0 && Robot.powerDP.getLiftLeftCurrent() > LiftPos.currentUpperLimit)
//        {
//        	arm.set(0);
//        }
//        else if (output > 0 && Robot.powerDP.getLiftRightCurrent() < LiftPos.currentLowerLimit)
//        {
//        	arm.set(0);
//        }
//        else if (output < 0 && Robot.powerDP.getLiftRightCurrent() > LiftPos.currentUpperLimit)
//        {
//        	arm.set(0);
//        }
        else
        {
        	arm.set(output);
        }
    }
		
}

