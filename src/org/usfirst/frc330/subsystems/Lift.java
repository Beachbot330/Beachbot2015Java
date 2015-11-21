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
import org.usfirst.frc330.constants.LiftPos;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import org.usfirst.frc330.wpilibj.DualSpeedController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Lift extends Subsystem implements PIDSource, PIDOutput
{
	protected PIDController liftPID;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    BBDoubleSolenoid totePincher = RobotMap.lifttotePincher;
    SpeedController liftLeft = RobotMap.liftliftLeft;
    SpeedController liftRight = RobotMap.liftliftRight;
    DualSpeedController lift = RobotMap.liftLift;
    AnalogInput liftPot = RobotMap.liftliftPot;

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
        
        liftPID = new PIDController(	LiftPos.proportional,
						        		LiftPos.integral,
						        		LiftPos.derivitive ,this,this);
        liftPID.setOutputRange(-1, 1);
        
        liftPID.setAbsoluteTolerance(LiftPos.tolerance);
        SmartDashboard.putData("LiftPID", liftPID);

    	CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return getPosition(); }
    	};
    	Robot.csvLogger.add("LiftPosition", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return liftPot.getAverageVoltage(); }
    	};
    	Robot.csvLogger.add("LiftPot", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return lift.get(); }
    	};
    	Robot.csvLogger.add("LiftOutput", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return 0/*getTotePinch()*/; } //TODO renable after cache
    	};
    	Robot.csvLogger.add("TotePincher", temp);
    	
    	temp = new CSVLoggable(true) {
    		public double get() { return Robot.powerDP.getLiftLeftCurrent(); }
    	};
    	Robot.csvLogger.add("LiftLeftCurrent", temp);
    	
    	temp = new CSVLoggable() {
    		public double get() { return Robot.powerDP.getLiftRightCurrent(); }
    	};
    	Robot.csvLogger.add("LiftRightCurrent", temp);
    }
	
	public double getTotePinch() {
		DoubleSolenoid.Value pincherState = totePincher.get();
		
		if (pincherState == DoubleSolenoid.Value.kForward)
		{
			return 1.0;
		} 
		else
		{
			return 0.0;
		}
	}
    
    public double getPosition()
    {	
    	double sensorRange = getLiftTopLimit() - getLiftBottomLimit();
    	double heightRange  =  LiftPos.topLimitHeight - LiftPos.bottomLimitHeight;
    	
    	return heightRange/sensorRange * (liftPot.getAverageVoltage() - getLiftBottomLimit()) + LiftPos.bottomLimitHeight;
    }
    
	public double getLiftTopLimit() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeLiftTopLimit";
        else
            name = "CompetitionLiftTopLimit";
		return Preferences.getInstance().getDouble(name, 5);
	}
	
	public double getLiftBottomLimit() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeLiftBottomLimit";
        else
            name = "CompetitionLiftBottomLimit";
		return Preferences.getInstance().getDouble(name, 0);
	}
	
	public void setLiftBottomLimit()
	{        
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeLiftBottomLimit";
        else
            name = "CompetitionLiftBottomLimit";
        
        Preferences.getInstance().putDouble(name, liftPot.getAverageVoltage());
    }
	
	public void setLiftTopLimit()
	{
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeLiftTopLimit";
        else
            name = "CompetitionLiftTopLimit";
        
        Preferences.getInstance().putDouble(name, liftPot.getAverageVoltage());
	}
    
    public void setPosition(double position)
    {
    	if (position > LiftPos.upperLimit)
    		position = LiftPos.upperLimit;
    	else if (position < LiftPos.lowerLimit)
    		position = LiftPos.lowerLimit;
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
        return liftPID.isEnabled();
    }
    
    public synchronized void enable() {
        liftPID.enable();
    }
    
    public synchronized void disable() {
        liftPID.disable();
    }
    
    boolean hitLimit = false;
    public void set(double output){
    	
        if (output > 0 && getPosition() > LiftPos.upperLimit)
        {
        	if (hitLimit == false)
        		Robot.logger.println("Hit Lift upper limit. Position: " + getPosition() + " Limit: " + LiftPos.upperLimit, true);
        	hitLimit = true;
        	lift.set(0);
        }
        else if (output < 0 && getPosition() < LiftPos.lowerLimit)
        {
        	if (hitLimit == false)
        		Robot.logger.println("Hit Lift lower limit. Postion: " + getPosition() + " Limit: " + LiftPos.lowerLimit, true);
        	hitLimit = true;
        	lift.set(0);
        }
        else if (output > 0 && (Robot.powerDP.getLiftLeftCurrent() < LiftPos.currentLowerLimit) || 
        		(Robot.powerDP.getLiftRightCurrent() < LiftPos.currentLowerLimit))
        {
        	if (hitLimit == false)
        		Robot.logger.println("Hit Lift down current limit. Left Current: " + Robot.powerDP.getLiftLeftCurrent() + 
        				" Right Current: " + Robot.powerDP.getLiftRightCurrent() +" Limit: " + LiftPos.currentLowerLimit, true);
        	hitLimit = true;
        	lift.set(0);
        }
        else if (output < 0 && (Robot.powerDP.getLiftLeftCurrent() > LiftPos.currentUpperLimit) || 
        		(Robot.powerDP.getLiftRightCurrent() > LiftPos.currentUpperLimit))
        {
        	if (hitLimit == false)
        		Robot.logger.println("Hit Lift up current limit. Left Current: " + Robot.powerDP.getLiftLeftCurrent() + 
        				" Right Current: " + Robot.powerDP.getLiftRightCurrent() +" Limit: " + LiftPos.currentUpperLimit, true);
        	hitLimit = true;
        	lift.set(0);
        }
        else
        {
        	lift.set(output);
        	hitLimit = false;
        }
    }

	public void stopLift() 
	{
		if (liftPID.isEnabled())
		{
			liftPID.reset();
		}
	}
	
	public void closeTotePincher()
	{
		totePincher.set(DoubleSolenoid.Value.kForward);
	}
	
	public void openTotePincher()
	{
		totePincher.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void setMaxOutput(double maxOutput) {
		liftPID.setOutputRange(-maxOutput, maxOutput);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}
}

