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
import org.usfirst.frc330.wpilibj.PIDGains;

/**
 *
 */
public class  DriveDistance extends BBCommand {

	double leftDistance, rightDistance, tolerance, maxOutput, maxOutputStep, maxOutputMax;
	double origDistance;
    boolean stopAtEnd = false;
    PIDGains highGains, lowGains, gains;
    
    public DriveDistance(double distance) {
        this(distance, 0, 15, false, ChassisConst.DriveLow, ChassisConst.DriveHigh);
    }
    
    public DriveDistance(double distance, double tolerance)
    {
        this(distance, tolerance, 15, false, ChassisConst.DriveLow, ChassisConst.DriveHigh);
    }
    
    public DriveDistance(	double distance, double tolerance,
			double timeout, boolean stopAtEnd) {
    	this(distance, tolerance, timeout, false, ChassisConst.DriveLow, ChassisConst.DriveHigh);
    }
    
    public DriveDistance(	double distance, double tolerance,
    						double timeout, boolean stopAtEnd, PIDGains low, PIDGains high) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.leftDistance = distance;
        this.rightDistance = distance;
        this.tolerance = tolerance;
        if (timeout > 0.0)
        	setTimeout(timeout);
        this.stopAtEnd = stopAtEnd;
        origDistance = distance;
        lowGains = low;
        highGains = high;
        maxOutput = 0;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.chassis.gyroPID.disable();
        if (!Robot.chassis.isHighGear())
        	gains = lowGains;
        else
        	gains = highGains;
        
        Robot.chassis.leftDrivePID.setPID(gains);
        Robot.chassis.rightDrivePID.setPID(gains);
        maxOutputMax = gains.getMaxOutput();
        maxOutputStep = gains.getMaxOutputStep();

        Robot.chassis.leftDrivePID.setSetpoint(leftDistance+Robot.chassis.getLeftDistance());
        Robot.chassis.rightDrivePID.setSetpoint(rightDistance+Robot.chassis.getRightDistance());
        Robot.chassis.leftDrivePID.setAbsoluteTolerance(tolerance);
        Robot.chassis.rightDrivePID.setAbsoluteTolerance(tolerance);  
        Robot.chassis.leftDrivePID.enable();
        Robot.chassis.rightDrivePID.enable();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	maxOutput += maxOutputStep;
    	if (maxOutput >= maxOutputMax) 
    		maxOutput = maxOutputMax;
    	Robot.chassis.leftDrivePID.setMaxOutput(maxOutput);
        Robot.chassis.rightDrivePID.setMaxOutput(maxOutput);
   
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.chassis.leftDrivePID.onTarget() || Robot.chassis.rightDrivePID.onTarget() || isTimedOut())
        {
                return true;            
        }
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        if (stopAtEnd)
        {
            Robot.chassis.stopDrive();
        }
        Robot.logger.println("DriveDistance Setpoint: " + leftDistance + "Left: " + Robot.chassis.getLeftDistance() + "Right: " + Robot.chassis.getRightDistance(), false);
        this.leftDistance = origDistance;
        this.rightDistance = origDistance;
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        stopAtEnd = true;
        end();
    }

}
