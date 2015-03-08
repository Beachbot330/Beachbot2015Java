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
import org.usfirst.frc330.constants.ChassisConst;

/**
 *
 */
public class  RotateAngleAbs extends BBCommand {
	double angle, tolerance, timeout;
    public RotateAngleAbs(double angle, double tolerance, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.angle = angle;
        this.tolerance = tolerance;
        if (timeout >= 0.0)
        	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (!Robot.chassis.isHighGear())
        {
            Robot.chassis.leftDrivePID.setGainName(ChassisConst.DriveLowName);
            Robot.chassis.rightDrivePID.setGainName(ChassisConst.DriveLowName);
        }
        else
        {
             Robot.chassis.leftDrivePID.setGainName(ChassisConst.DriveHighName);
             Robot.chassis.rightDrivePID.setGainName(ChassisConst.DriveHighName);
        }
		Robot.chassis.gyroPID.disable();
    	if (angle > 0) {
    		Robot.chassis.leftDrivePID.disable();
    		Robot.chassis.rightDrivePID.setSetpoint(Robot.chassis.getRightDistance());
    		Robot.chassis.rightDrivePID.enable();
    	}
    	else {
    		Robot.chassis.rightDrivePID.disable();
    		Robot.chassis.leftDrivePID.setSetpoint(Robot.chassis.getRightDistance());
    		Robot.chassis.leftDrivePID.enable();
    	}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    double error = 360;
    protected void execute() {
    	error = angle - Robot.chassis.getAngle();
    	if (angle > 0)
    		Robot.chassis.tankDrive(error*ChassisConst.rotateProportional, 0);
    	else
    		Robot.chassis.tankDrive(0, -error*ChassisConst.rotateProportional);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || Math.abs(error) < tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
