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


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.BBCommand;

import org.usfirst.frc330.Robot;

/**
 *
 */
public class  BuzzerBeepTimed extends BBCommand {

	Timer timeyWhimey = new Timer();
	
    public BuzzerBeepTimed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.frills);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeyWhimey.reset();
    	timeyWhimey.start();
    	Robot.frills.buzzerOn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timeyWhimey.hasPeriodPassed(1.0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.frills.buzzerOff();
    	timeyWhimey.stop();    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
