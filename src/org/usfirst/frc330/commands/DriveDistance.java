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
public class  DriveDistance extends BBCommand {

	double leftDistance, rightDistance, tolerance, maxOutput, maxOutputStep, maxOutputMax;
	double origDistance;
    boolean stopAtEnd = false;
    
    public DriveDistance(double distance) {
        this(distance, 0, 15, false, ChassisConst.defaultMaxOutput, ChassisConst.defaultMaxOutputStep);
    }
    
    public DriveDistance(double distance, double tolerance)
    {
        this(distance, tolerance, 15, false, ChassisConst.defaultMaxOutput, ChassisConst.defaultMaxOutputStep);
    }
    
    public DriveDistance(double distance, double tolerance, double timeout, boolean stopAtEnd)
    {
    	this(distance, tolerance, timeout, false, ChassisConst.defaultMaxOutput, ChassisConst.defaultMaxOutputStep);
    }
    
    public DriveDistance(	double distance, double tolerance,
    						double timeout, boolean stopAtEnd, double maxOutput, double maxOutputStep) {
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
        this.maxOutputMax = maxOutput;
        origDistance = distance;
        this.maxOutputStep = maxOutputStep;
        this.maxOutput = maxOutputStep;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.chassis.gyroPID.disable();
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
        Robot.chassis.leftDrivePID.setSetpoint(leftDistance+Robot.chassis.getLeftDistance());
        Robot.chassis.rightDrivePID.setSetpoint(rightDistance+Robot.chassis.getRightDistance());
        Robot.chassis.leftDrivePID.setAbsoluteTolerance(tolerance);
        Robot.chassis.rightDrivePID.setAbsoluteTolerance(tolerance);
        Robot.chassis.leftDrivePID.setOutputRange(-maxOutput, maxOutput);
        Robot.chassis.rightDrivePID.setOutputRange(-maxOutput, maxOutput);    
        Robot.chassis.leftDrivePID.enable();
        Robot.chassis.rightDrivePID.enable();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	maxOutput += maxOutputStep;
    	if (maxOutput >= maxOutputMax) 
    		maxOutput = maxOutputMax;
    	Robot.chassis.leftDrivePID.setOutputRange(-maxOutput, maxOutput);
        Robot.chassis.rightDrivePID.setOutputRange(-maxOutput, maxOutput);
   
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
