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


import edu.wpi.first.wpilibj.command.BBCommand;
import org.usfirst.frc330.Robot;

/**
 *
 */
public class  ManualLift extends BBCommand {

    public ManualLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.lift);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis); //disable driving since joystick is 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.disable();
    	Robot.logger.println("Manual Lift Starting Position: " + Robot.lift.getPosition());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.set(Robot.oi.getDriverR().getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.set(0);
    	Robot.lift.setPosition(Robot.lift.getPosition());
    	Robot.logger.println("Manual Lift Ending Position: " + Robot.lift.getPosition());
    	Robot.lift.enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
