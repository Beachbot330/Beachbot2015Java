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
import org.usfirst.frc330.constants.ArmPos;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Arm extends Subsystem implements PIDSource, PIDOutput{
	protected PIDController armPID;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DoubleSolenoid mast = RobotMap.armmast;
    AnalogInput armPot = RobotMap.armarmPot;
    SpeedController armLeft = RobotMap.armarmLeft;
    SpeedController armRight = RobotMap.armarmRight;

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
		armPID.setAbsoluteTolerance(absvalue);
	}
    
	public Arm() {
        super();
        armPID = new PIDController(ArmPos.proportional,
        						   ArmPos.integral,
        						   ArmPos.derivitive ,this,this);
        
        armPID.setAbsoluteTolerance(ArmPos.tolerance);
        SmartDashboard.putData("ArmPID", armPID);
        SmartDashboard.putBoolean("ArmOverride", false);
    }
    
    public double getPosition()
    {
    	return armPot.getAverageVoltage();
    }
    
    public void setArmPosition(double position)
    {
    	armPID.setSetpoint(position);
    }

	public void pidWrite(double output) {
		set(output);	
	}

	public double pidGet() {
		return getArmPosition();
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
	
    public synchronized double getSetpoint() {
        return armPID.getSetpoint();
    }
    
    public synchronized boolean onTarget() {
        return armPID.onTarget();
    }
    
    public synchronized boolean isEnable() {
        return armPID.isEnable();
    }
    
    public synchronized void enable() {
        armPID.enable();
    }
    
    public synchronized void disable() {
        armPID.disable();
    }
    
    public double getArmPosition()
    {
        return armPot.getAverageVoltage(); // Todo: Scale
    }
    
    public void set(double output){
        if (output > 0 && getArmPosition() > ArmPos.rearLimit) // Todo: Check this logic
        {
            armLeft.set(0);
            armRight.set(0);
        }
        else if (output < 0 && getArmPosition() < ArmPos.frontLimit)// Todo: Check this logic
        {
            armLeft.set(0);
            armRight.set(0);
        }
        else
        {
            armLeft.set(output);
            //armRight.set(-output); // Todo: check polarity and then re-enable
        }
    }

}

