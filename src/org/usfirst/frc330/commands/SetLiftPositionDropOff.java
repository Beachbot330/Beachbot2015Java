// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands;	


import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.LiftPos;

/**
 *
 */
public class  SetLiftPositionDropOff extends SetLiftPosition {
	double position;
	double timeout;
	double tolerance;
	
	public SetLiftPositionDropOff() {
		this(LiftPos.tolerance, -1.0);
	}
	
	public SetLiftPositionDropOff(double tolerance){
		this(tolerance, -1.0);
	}
	
    public SetLiftPositionDropOff(double tolerance, double timeout) {
        super(LiftPos.dropOff, tolerance, timeout);
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.lift.getPosition() < LiftPos.carry)
    		Robot.lift.setMaxOutput(LiftPos.slowMaxOutput);
    }


    // Called once after isFinished returns true
    protected void end() {
    	Robot.logger.println("SetLiftPosition Setpoint: " + position + " Ending Position: " + Robot.lift.getPosition());
    	Robot.lift.setMaxOutput(LiftPos.defaultMaxOutput);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
