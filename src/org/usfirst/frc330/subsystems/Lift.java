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
import org.usfirst.frc330.constants.LiftPos;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.wpilibj.DualSpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Lift extends Subsystem implements PIDSource, PIDOutput
{
	protected PIDController liftPID;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder liftEncoder = RobotMap.liftliftEncoder;
    DoubleSolenoid totePincher = RobotMap.lifttotePincher;
    SpeedController liftLeft = RobotMap.liftliftLeft;
    SpeedController liftRight = RobotMap.liftliftRight;
    DualSpeedController lift = RobotMap.liftLift;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setAbsoluteTolerance(double absvalue) {
		liftPID.setAbsoluteTolerance(absvalue);
	}
    
	public Lift() {
        super();
        
        liftEncoder.setDistancePerPulse(LiftPos.liftLength/LiftPos.encoderTurns/LiftPos.encoderPPR);
        
        liftPID = new PIDController(	LiftPos.proportional,
						        		LiftPos.integral,
						        		LiftPos.derivitive ,this,this);
        
        liftPID.setAbsoluteTolerance(LiftPos.tolerance);
        SmartDashboard.putData("LiftPID", liftPID);

    	CSVLoggable temp = new CSVLoggable() {
			public double get() { return getPosition(); }
    	};
    	Robot.csvLogger.add("LiftEncoder", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return lift.get(); }
    	};
    	Robot.csvLogger.add("LiftOutput", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return getTotePinch(); }
    	};
    	Robot.csvLogger.add("TotePincher", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getLiftLeftCurrent(); }
    	};
    	Robot.csvLogger.add("LiftLeftCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getLiftRightCurrent(); }
    	};
    	Robot.csvLogger.add("LiftRightCurrent", temp);
    }
	
	public double getTotePinch() {
		boolean returnVal = false;
		returnVal = totePincher.get();
		
		if(returnVal) {
			return 1.0;
		} else {
			return 0.0;
		}
	}
    
    public double getPosition()
    {	
    	return liftEncoder.getDistance();
    }
    
    public void setPosition(double position)
    {
    	liftPID.setSetpoint(position);
    }

	public void pidWrite(double output) {
		set(output);	
	}

	public double pidGet() {
		return getPosition();
	}
	
    public synchronized double getSetpoint() {
        return liftPID.getSetpoint();
    }
    
    public synchronized boolean onTarget() {
        return liftPID.onTarget();
    }
    
    public synchronized boolean isEnable() {
        return liftPID.isEnable();
    }
    
    public synchronized void enable() {
        liftPID.enable();
    }
    
    public synchronized void disable() {
        liftPID.disable();
    }
    
    public void set(double output){
        if (output > 0 && getPosition() < LiftPos.lowerLimit) // Todo: Check this logic
        {
        	lift.set(0);
        }
        else if (output < 0 && getPosition() > LiftPos.upperLimit)// Todo: Check this logic
        {
        	lift.set(0);
        }
        else if (output > 0 && Robot.powerDP.getLiftLeftCurrent() < LiftPos.currentLowerLimit)
        {
        	lift.set(0);
        }
        else if (output < 0 && Robot.powerDP.getLiftLeftCurrent() > LiftPos.currentUpperLimit)
        {
        	lift.set(0);
        }
        else if (output > 0 && Robot.powerDP.getLiftRightCurrent() < LiftPos.currentLowerLimit)
        {
        	lift.set(0);
        }
        else if (output < 0 && Robot.powerDP.getLiftRightCurrent() > LiftPos.currentUpperLimit)
        {
        	lift.set(0);
        }
        else
        {
        	lift.set(output);
        }
    }

	public void stopLift() 
	{
		if (liftPID.isEnable())
		{
			liftPID.reset();
		}
	}
}

