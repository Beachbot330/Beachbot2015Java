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
import org.usfirst.frc330.constants.HandConst;

import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *
 */
public class  SetWristAngle extends BBCommand {
	double angle;
	double timeout;
	double tolerance;
    
	public SetWristAngle(double angle) {
		this(angle, HandConst.tolerance, -1.0);
	}
	
	public SetWristAngle(double angle, double tolerance){
		this(angle, tolerance, -1.0);
	}
	
	public SetWristAngle(double angle, double tolerance, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.hand);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.angle = angle;
        this.timeout = timeout;
        this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hand.setAngle(angle);
    	Robot.hand.setAbsoluteTolerance(tolerance);
    	if (timeout >= 0.0)
    		{setTimeout(timeout);}
    	else
    		{setTimeout(9999999);}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.hand.isWristAtLimit())
    		Robot.logger.println("Wrist At Limit");
    	return (Robot.hand.onTarget() || isTimedOut() || Robot.hand.isWristAtLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
